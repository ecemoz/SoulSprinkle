package com.yildiz.soulsprinkle.model;

import com.yildiz.soulsprinkle.model.preferences.LanguagePreference;
import com.yildiz.soulsprinkle.model.preferences.NotificationPreference;
import com.yildiz.soulsprinkle.model.preferences.ThemePreference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "profile_settings")
public class ProfileSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LanguagePreference languagePreference = LanguagePreference.EN;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ThemePreference themePreference = ThemePreference.Dark;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NotificationPreference notificationPreference = NotificationPreference.All;

    private boolean reminderSettings = false;

    @OneToOne(mappedBy = "profileSettings")
    private User user;
}
