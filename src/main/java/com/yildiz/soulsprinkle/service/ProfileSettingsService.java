package com.yildiz.soulsprinkle.service;

import com.yildiz.soulsprinkle.model.ProfileSettings;
import com.yildiz.soulsprinkle.repository.ProfileSettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ProfileSettingsService {

    private final ProfileSettingsRepository profileSettingsRepository;

    @Autowired
    public ProfileSettingsService(ProfileSettingsRepository profileSettingsRepository) {
        this.profileSettingsRepository = profileSettingsRepository;
    }

    public Optional<ProfileSettings> getProfileSettingsById(Long id) {
        return profileSettingsRepository.findById(id);
    }

    public Optional<ProfileSettings> getProfileSettingsByUserId(Long userId) {
        return Optional.ofNullable(profileSettingsRepository.findByUserId(userId));
    }

    public ProfileSettings saveProfileSettings(ProfileSettings profileSettings) {
        return profileSettingsRepository.save(profileSettings);
    }

    public ProfileSettings updateProfileSettings(Long id , ProfileSettings updatedProfileSettings) {
    return profileSettingsRepository.findById(id)
            . map(profileSettings -> {
            profileSettings.setLanguagePreference(updatedProfileSettings.getLanguagePreference());
            profileSettings.setThemePreference(updatedProfileSettings.getThemePreference());
            profileSettings.setNotificationPreference(updatedProfileSettings.getNotificationPreference());
            profileSettings.setReminderSettings(updatedProfileSettings.isReminderSettings());
            return profileSettingsRepository.save(profileSettings);
        })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void deleteProfileSettingsById(Long id) {
        profileSettingsRepository.deleteById(id);
    }
}