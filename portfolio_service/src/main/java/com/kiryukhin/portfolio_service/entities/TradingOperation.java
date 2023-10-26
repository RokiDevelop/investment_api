package com.kiryukhin.portfolio_service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "trading_operation")
public class TradingOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @Column(name = "operation_price", nullable = false)
    private Double price;

    @Column(name = "operation_date", nullable = false)
    private LocalDateTime operationDate;

    @ManyToOne
    @JoinColumn(name = "portfolio_id", nullable = false)
    private PortfolioEntity portfolio;

    @Override
    public String toString() {
        return "TradingOperation{" +
                "id=" + id +
                ", stock=" + stock +
                ", amount=" + amount +
                ", price=" + price +
                ", operationDate=" + operationDate +
                ", portfolio=" + portfolio +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TradingOperation that = (TradingOperation) o;
        return Objects.equals(id, that.id) && Objects.equals(amount, that.amount) && Objects.equals(price, that.price) && Objects.equals(operationDate, that.operationDate) && Objects.equals(portfolio, that.portfolio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, price, operationDate, portfolio);
    }
}
