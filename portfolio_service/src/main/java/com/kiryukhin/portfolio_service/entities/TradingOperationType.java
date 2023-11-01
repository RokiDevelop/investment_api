package com.kiryukhin.portfolio_service.entities;

import com.kiryukhin.portfolio_service.entities.enums.EnumOperationType;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "trading_operation_type",
        uniqueConstraints= @UniqueConstraint(columnNames={"type"}))
public class TradingOperationType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "type", nullable = false)
    private String type;

    public TradingOperationType(EnumOperationType enumOperationType) {
        this.id = (long) enumOperationType.ordinal();
        this.type = enumOperationType.toString();
    }

    public EnumOperationType toOperationType() {
        return EnumOperationType.valueOf(this.type);
    }
}