package org.mapsa.wallet.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "wallet_transaction")
public class WalletTransactionEntity extends AbstractEntity {
    private Date date;

    private Long amount;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private WalletEntity walletEntity;

    @Column(unique = true)
    private String trackingId;
}
