package com.kiryukhin.portfolio_service.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "assets_stock",
        uniqueConstraints= @UniqueConstraint(columnNames={"stock_id", "portfolio_id"}),
        indexes = {
                @Index(name = "idx_portfolio", columnList = "portfolio_id"),
                @Index(name = "idx_stock_portfolio", columnList = "stock_id, portfolio_id")
        })
public class AssetStock {

    public AssetStock(Stock stock, Long amount, PortfolioEntity portfolio) {
        this.stock = stock;
        this.amount = amount;
        this.portfolio = portfolio;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @ManyToOne
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;

    @ManyToOne
    @JoinColumn(name = "portfolio_id", nullable = false)
    private PortfolioEntity portfolio;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssetStock that = (AssetStock) o;
        return Objects.equals(stock, that.stock) && Objects.equals(portfolio, that.portfolio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stock, portfolio);
    }

    @Override
    public String toString() {
        return "AssetStock{" +
                "amount=" + amount +
                ", stock=" + stock +
                '}';
    }
}
