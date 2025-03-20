package com.honeywell.aerospace.model;

public class DecryptionRequest {
    private String encryptedData;
    private String encodedKey;

    // Getters and setters
    public String getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }

    public String getEncodedKey() {
        return encodedKey;
    }

    public void setEncodedKey(String encodedKey) {
        this.encodedKey = encodedKey;
    }
}