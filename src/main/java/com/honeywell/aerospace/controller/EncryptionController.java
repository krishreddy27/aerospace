package com.honeywell.aerospace.controller;

import com.honeywell.aerospace.model.DecryptionRequest;
import com.honeywell.aerospace.service.EncryptionService;
import com.honeywell.aerospace.service.IEncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/rest/honeywell")
public class EncryptionController {
    private final IEncryptionService encryptionService;

    @Autowired
    EncryptionController(IEncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @GetMapping("/encrypt/{name}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Map<String, String>> encrypt(@PathVariable String name) {
        try {
            SecretKey key = encryptionService.generateKey();
            String encryptedData = encryptionService.encrypt(name, key);
            String encodedKey = encryptionService.encodeKey(key);

            Map<String, String> response = new HashMap<>();
            response.put("encryptedData", encryptedData);
            response.put("key", encodedKey);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/decrypt")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> decrypt(@RequestBody DecryptionRequest request) {
        try {
            SecretKey key = encryptionService.decodeKey(request.getEncodedKey());
            String decryptedData = encryptionService.decrypt(request.getEncryptedData(), key);
            return ResponseEntity.ok(decryptedData);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Decryption failed: " + e.getMessage());
        }
    }
}