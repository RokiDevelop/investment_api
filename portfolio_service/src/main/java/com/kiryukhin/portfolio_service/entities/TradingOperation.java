package com.kiryukhin.portfolio_service.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "trading_operation",
        indexes = {
                @Index(name = "idx_portfolio", columnList = "portfolio_id"),
                @Index(name = "idx_portfolio_stock", columnList = "portfolio_id, stock_id")})
public class TradingOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;

    @EqualsAndHashCode.Exclude
    @Column(name = "amount", nullable = false)
    private Long amount;

    @EqualsAndHashCode.Exclude
    @Column(name = "operation_price", nullable = false)
    private Double price;

    @Column(name = "operation_date", nullable = false)
    private LocalDateTime operationDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "portfolio_id", nullable = false)
    private PortfolioEntity portfolio;

    @ManyToOne
    private TradingOperationType tradingOperationType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TradingOperation that = (TradingOperation) o;
        return Objects.equals(id, that.id) && Objects.equals(stock, that.stock) && Objects.equals(amount, that.amount) && Objects.equals(price, that.price) && Objects.equals(operationDate, that.operationDate) && Objects.equals(portfolio, that.portfolio) && Objects.equals(tradingOperationType, that.tradingOperationType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, stock, amount, price, operationDate, portfolio, tradingOperationType);
    }
}
