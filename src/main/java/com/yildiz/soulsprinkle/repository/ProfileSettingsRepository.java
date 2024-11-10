package com.yildiz.soulsprinkle.repository;

import com.yildiz.soulsprinkle.model.ProfileSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileSettingsRepository extends JpaRepository <ProfileSettings,Long> {

    ProfileSettings findByUserId(Long userId);
}