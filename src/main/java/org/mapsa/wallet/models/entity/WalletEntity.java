package org.mapsa.wallet.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "wallet")
public class WalletEntity extends AbstractEntity {

    @Column(nullable = false)
    private Long balance;

    @Column(nullable = false)
    private String userName;

    //@JsonIgnore --> to avoid circular dependency when a wallet entity has a list of transactions
    @OneToMany(mappedBy = "walletEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<WalletTransactionEntity> walletTransactionEntities;

}
