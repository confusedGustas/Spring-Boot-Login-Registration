package com.gustas.springboot.login.modules;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@DynamicUpdate
public class VerificationToken {
    @SequenceGenerator(name = "confirmation_sequence", sequenceName = "confirmation_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "confirmation_sequence")
    @Id
    private Long id;
    private String verificationToken;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;

    public VerificationToken(
            String verificationToken, LocalDateTime createdAt, LocalDateTime expiresAt, AppUser appUser) {
        this.verificationToken = verificationToken;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.appUser = appUser;
    }
}
