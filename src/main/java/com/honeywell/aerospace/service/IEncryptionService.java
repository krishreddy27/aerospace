package com.honeywell.aerospace.service;

import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

public interface IEncryptionService {
        String encrypt(String data, String key) throws Exception;
        String decrypt(String encryptedData, String key) throws Exception;
        SecretKey deriveKeyFromString(String keyString) throws NoSuchAlgorithmException;
}
