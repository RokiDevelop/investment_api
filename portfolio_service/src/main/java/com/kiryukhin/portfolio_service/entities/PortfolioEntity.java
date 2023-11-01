package com.kiryukhin.portfolio_service.entities;

import com.kiryukhin.portfolio_service.security.securityEntities.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "portfolio_entities",
        uniqueConstraints= @UniqueConstraint(columnNames={"user_id"})
)
public class PortfolioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "portfolio")
    private Set<AssetStock> assetStocks;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "portfolio")
    private Set<TradingOperation> tradingOperations;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PortfolioEntity that = (PortfolioEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user);
    }
}
