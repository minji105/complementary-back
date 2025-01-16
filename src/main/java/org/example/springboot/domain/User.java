package org.example.springboot.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Builder
    public  User(String email, String password, String auth){
        this.email = email;
        this.password = password;
    }

    @Override // 권한 목록 반환
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override // id 반환 (고유값, 유니크 속성이 적용된 이메일을 반환)
    public String getUsername() {
        return "";
    }

    @Override // 패스워드 반환
    public String getPassword() {
        return "";
    }

    @Override // 계정 만료 여부 반환
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override // 계정 잠금 여부 반환
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override // 패스워드 만료 여부 반환
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override // 계정 사용 가능 여부 반환
    public boolean isEnabled() {
        return false;
    }
}
