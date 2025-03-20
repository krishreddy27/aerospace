package com.honeywell.aerospace.service;

import javax.crypto.SecretKey;

public interface IEncryptionService {
        SecretKey generateKey() throws Exception;
        String encrypt(String data, SecretKey key) throws Exception;
        String encodeKey(SecretKey key) throws Exception;
        SecretKey decodeKey(String encodedKey) throws Exception;
        String decrypt(String encryptedData, SecretKey key) throws Exception;
}
