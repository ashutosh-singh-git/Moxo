package com.moxo.app.service;

public interface OtpService {

    public String generateOtp(String key);

    public String fetchOtp(String key);

}
