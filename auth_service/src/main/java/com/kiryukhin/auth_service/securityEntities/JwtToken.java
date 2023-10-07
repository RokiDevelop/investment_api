package com.kiryukhin.auth_service.securityEntities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "jwt_tokens")
public class JwtToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
}
