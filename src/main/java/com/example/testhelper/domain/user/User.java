package com.example.testhelper.domain.user;

import com.example.testhelper.domain.BaseTimeEntity;
import com.example.testhelper.domain.system.Code;
import com.example.testhelper.enums.UserGender;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Entity
@NoArgsConstructor
public class User extends BaseTimeEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String userPassword;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('M','F')")
    private UserGender gender;

    @Column
    private String token;

    @ManyToOne
    @JoinColumn(name = "status")
    private Code status;

    @Column
    private LocalDateTime withdrawAt;

    @Builder
    public User(String name, UserGender gender, String email, String userPassword, Code status, LocalDateTime withdrawAt) {
        this.email = email;
        this.userPassword = userPassword;
        this.name = name;
        this.gender = gender;
        this.status = status;
        this.withdrawAt = withdrawAt;

    }

    public void update(String email, String name, String gender) {
        if (email != null) {
            this.email = email;
        }
        if (name != null) {
            this.name = name;
        }
        if(gender != null){
            this.gender = UserGender.valueOf(gender);
        }
    }

    public void saveToken(String token) {
        this.token = token;
    }

    public void removeToken() {
        this.token = null;
    }

    public void updatePassword(String password) {
        this.userPassword = password;
    }

    public void withdraw(Code withdraw) {
        this.token = null;
        this.status = withdraw;
        this.withdrawAt = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
