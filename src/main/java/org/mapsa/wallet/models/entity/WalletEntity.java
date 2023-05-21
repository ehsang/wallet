package org.mapsa.wallet.models.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "wallet")
public class WalletEntity {

    @Column(nullable = false)
    private Long balance;

    @Column(nullable = false)
    private String userName;

    @OneToMany(mappedBy = "walletEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<WalletTransactionEntity> walletTransactionEntities;

}
