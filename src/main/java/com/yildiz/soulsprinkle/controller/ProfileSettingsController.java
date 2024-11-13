package com.yildiz.soulsprinkle.controller;

import com.yildiz.soulsprinkle.model.ProfileSettings;
import com.yildiz.soulsprinkle.service.ProfileSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile-settings")
public class ProfileSettingsController {

    private final ProfileSettingsService profileSettingsService;

    @Autowired
    public ProfileSettingsController(ProfileSettingsService profileSettingsService) {
        this.profileSettingsService = profileSettingsService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileSettings> getProfileSettingsById(@PathVariable Long id) {
        return profileSettingsService.getProfileSettingsById(id)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ProfileSettings> getProfileSettingsByUserId(@PathVariable Long userId) {
        return profileSettingsService.getProfileSettingsByUserId(userId)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<ProfileSettings> createProfileSettings(@RequestBody ProfileSettings profileSettings) {
       ProfileSettings savedProfileSettings = profileSettingsService.saveProfileSettings(profileSettings);
       return new ResponseEntity<>(savedProfileSettings, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfileSettings> updateProfileSettings(@PathVariable Long id, @RequestBody ProfileSettings updatedProfileSettings) {
        try {
            ProfileSettings profileSettings = profileSettingsService.updateProfileSettings(id, updatedProfileSettings);
            return ResponseEntity.ok(profileSettings);
        } catch(RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfileSettingsById(@PathVariable Long id){
        try{
            profileSettingsService.deleteProfileSettingsById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}