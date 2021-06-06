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

        return new UserInfoResponse(userEntity.getUid(),
                userEntity.getName(),
                userEntity.getAlias(),
                userEntity.getEmail(),
                userEntity.getMsisdn());
    }

    @Override
    public BaseResponse generateCode(GenerateCodeDto codeDto) {
        String otp = otpService.generateOtp(codeDto.getEmail());

        return new BaseResponse("Otp is generated on your id ", "012", true);
    }

    @Override
    public UserInfoResponse createUser(UserCreateDto createDto) {

        boolean isOldUser = switch (createDto.getLogMode()) {
            case EMAIL -> checkIfEmailExists(createDto.getEmail());
            case MSISDN -> checkIfMsisdnExists(createDto.getMsisdn());
        };

        if (isOldUser) {
            throw new RuntimeException("User Already Present");
        }

        UserEntity entity = UserEntity.builder()
                .acqMode(createDto.getAcqMode())
                .logMode(Set.of(createDto.getLogMode()))
                .msisdn(createDto.getMsisdn())
                .email(createDto.getEmail())
                .passwd(generateHashPassword(createDto.getPasswd()))
                .name(createDto.getName())
                .build();
        UserEntity newEntity = userRepository.insert(entity);

        return new UserInfoResponse(newEntity.getUid(),
                newEntity.getName(),
                newEntity.getAlias(),
                newEntity.getEmail(),
                newEntity.getMsisdn());
    }

    private boolean checkIfMsisdnExists(String msisdn){
        if(!StringUtils.hasLength(msisdn)) {
            return false;
        }
        UserEntity byMsisdn = userRepository.findByMsisdn(msisdn);
        return byMsisdn != null;
    }

    private boolean checkIfEmailExists(String email) {
        if(!StringUtils.hasLength(email)) {
            return false;
        }
        UserEntity byEmail = userRepository.findByEmail(email);
        return byEmail != null;
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
