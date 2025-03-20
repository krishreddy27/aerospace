package com.honeywell.aerospace.service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.springframework.stereotype.Service;

@Service
public class EncryptionService implements IEncryptionService {

    private static final String ALGORITHM = "AES";

    public SecretKey deriveKeyFromString(String keyString) throws NoSuchAlgorithmException {
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        byte[] key = sha.digest(keyString.getBytes(java.nio.charset.StandardCharsets.UTF_8));
        return new SecretKeySpec(key, 0, 16, ALGORITHM); // Use first 128 bits for AES-128
    }
    public String encrypt(String data, String keyString) throws Exception {
        SecretKey key = deriveKeyFromString(keyString);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
    public String decrypt(String encryptedData, String keyString) throws Exception {
        SecretKey key = deriveKeyFromString(keyString);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }
}