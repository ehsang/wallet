package org.mapsa.wallet.models.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class WalletTransactionEntity {
    private Date date;

    private Long amount;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private WalletEntity walletEntity;

    @Column(unique = true)
    private String trackingId;
}