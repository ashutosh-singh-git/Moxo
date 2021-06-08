package com.moxo.app.service.impl;

import com.moxo.app.dto.BaseResponse;
import com.moxo.app.dto.GenerateCodeDto;
import com.moxo.app.dto.UserCreateDto;
import com.moxo.app.dto.UserInfoResponse;
import com.moxo.app.dto.UserLoginDto;
import com.moxo.app.entity.UserEntity;
import com.moxo.app.repository.UserRepository;
import com.moxo.app.service.OtpService;
import com.moxo.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.SignatureException;
import java.util.Base64;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private static final String SALT = "dakv345";

    private final UserRepository userRepository;
    private final OtpService otpService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, OtpService otpService) {
        this.userRepository = userRepository;
        this.otpService = otpService;
    }

    @Override
    public UserInfoResponse login(UserLoginDto loginDto) {

        UserEntity userEntity = null;

        switch (loginDto.getLogMode()) {
            case EMAIL -> userEntity = userRepository.findByEmail(loginDto.getEmail());
            case MSISDN -> userEntity = userRepository.findByMsisdn(loginDto.getMsisdn());
        }

        if(userEntity == null) {
            throw new RuntimeException("User doesn't exists");
        }

        if(userEntity.getPasswd().equals(generateHashPassword(loginDto.getPasswd()))) {
            throw new RuntimeException("Password doesn't match!!");
        }

        return new UserInfoResponse(userEntity);
    }

    @Override
    public BaseResponse generateCode(GenerateCodeDto codeDto) {
        String otp = otpService.generateOtp(codeDto.getEmail());
        LOGGER.info("Otp generated : " + otp);
        return BaseResponse.builder()
                .msg("Otp is generated on your id " + codeDto.getEmail())
                .code("012")
                .status(true)
                .build();
    }

    @Override
    public UserInfoResponse createUser(UserCreateDto createDto) {

        switch (createDto.getLogMode()) {
            case EMAIL -> {
                checkIfEmailExists(createDto.getEmail());
                verifyOtp(createDto.getEmail(), createDto.getOtp());
            }
            case MSISDN -> {
                checkIfMsisdnExists(createDto.getMsisdn());
                verifyOtp(createDto.getMsisdn(), createDto.getOtp());
            }
        }

        LOGGER.info("Creating user from : " + createDto);
        UserEntity entity = UserEntity.builder()
                .acqMode(createDto.getAcqMode())
                .logMode(Set.of(createDto.getLogMode()))
                .msisdn(createDto.getMsisdn())
                .email(createDto.getEmail())
                .passwd(generateHashPassword(createDto.getPasswd()))
                .name(createDto.getName())
                .build();
        UserEntity newEntity = userRepository.insert(entity);

        return new UserInfoResponse(newEntity);
    }

    private void verifyOtp(String key, String otp) {
        String systemOtp = otpService.fetchOtp(key);

        if(!otp.equals(systemOtp)) {
            throw new RuntimeException("Otp is invalid/expired!");
        }
    }

    private void checkIfMsisdnExists(String msisdn){
        if (!StringUtils.hasLength(msisdn)) {
            throw new RuntimeException("Msisdn Id is empty");
        }
        UserEntity byMsisdn = userRepository.findByMsisdn(msisdn);
        if (byMsisdn != null) {
            throw new RuntimeException("User Already Present");
        }
    }

    private void checkIfEmailExists(String email) {
        if(!StringUtils.hasLength(email)) {
            throw new RuntimeException("Email Id is empty");
        }
        UserEntity byEmail = userRepository.findByEmail(email);
        if(byEmail != null) {
            throw new RuntimeException("User Already Present");
        }
    }

    private String generateHashPassword(String pwd) {
        try {
            return calculateRFC2104HMAC(pwd);
        } catch (SignatureException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String calculateRFC2104HMAC(String data) throws java.security.SignatureException {
        try {
            Mac mac = Mac.getInstance("HmacSHA1");
            SecretKeySpec key = new SecretKeySpec(SALT.getBytes(), "HmacSHA1");
            mac.init(key);
            byte[] authentication = mac.doFinal(data.getBytes());
            return new String(Base64.getEncoder().encode(authentication));
        } catch (Exception e) {
            throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
        }
    }

}
