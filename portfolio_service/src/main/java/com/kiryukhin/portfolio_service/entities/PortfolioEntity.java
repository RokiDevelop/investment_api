package com.kiryukhin.portfolio_service.entities;

import com.kiryukhin.portfolio_service.security.securityEntities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "portfolio_entities")
public class PortfolioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany
    @JoinTable(name = "portfolio_entity_2_asset_stock",
            joinColumns = @JoinColumn(name = "portfolio_id"),
            inverseJoinColumns = @JoinColumn(name = "asset_stock_id")
    )
    private Set<AssetStock> assetStocks;

    @OneToMany(mappedBy = "portfolio")
    private Set<TradingOperation> tradingOperations;

    @Override
    public String toString() {
        return "PortfolioEntity{" +
                "id=" + id +
                ", user=" + user +
                ", assetStocks=" + assetStocks +
                ", tradingOperations=" + tradingOperations +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PortfolioEntity that = (PortfolioEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(user, that.user) && Objects.equals(assetStocks, that.assetStocks) && Objects.equals(tradingOperations, that.tradingOperations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, assetStocks, tradingOperations);
    }
}
