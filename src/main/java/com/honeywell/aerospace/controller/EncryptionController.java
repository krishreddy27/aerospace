package com.honeywell.aerospace.controller;

import com.honeywell.aerospace.model.DecryptionRequest;
import com.honeywell.aerospace.service.IEncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        String symmetricKey = "KASA";
        try {
            String encryptedData = encryptionService.encrypt(name, symmetricKey);

            Map<String, String> response = new HashMap<>();
            response.put("encryptedData", encryptedData);
            response.put("symmetricKey", symmetricKey);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/decrypt")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> decrypt(@RequestBody DecryptionRequest request) {
        try {
            String decryptedData = encryptionService.decrypt(request.getEncryptedData(), request.getSymmetricKey());
            return ResponseEntity.ok(decryptedData);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Decryption failed: " + e.getMessage());
        }
    }
}