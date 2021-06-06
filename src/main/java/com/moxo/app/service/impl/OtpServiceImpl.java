package com.moxo.app.service.impl;

import com.moxo.app.config.MoxoCacheConfig;
import com.moxo.app.service.OtpService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@CacheConfig(cacheNames = MoxoCacheConfig.OTP)
public class OtpServiceImpl implements OtpService {


    @Override
    @CachePut
    public String generateOtp(String key) {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return otp + "";
    }

    @Override
    @Cacheable
    public String fetchOtp(String key) {
        return null;
    }
}
