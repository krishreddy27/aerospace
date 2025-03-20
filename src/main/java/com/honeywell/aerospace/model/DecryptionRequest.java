package com.honeywell.aerospace.model;

public class DecryptionRequest {
    private String encryptedData;
    private String symmetricKey;

    // Getters and setters
    public String getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }

    public String getSymmetricKey() {
        return symmetricKey;
    }

    public void setSymmetricKey(String encodedKey) {
        this.symmetricKey = encodedKey;
    }
}