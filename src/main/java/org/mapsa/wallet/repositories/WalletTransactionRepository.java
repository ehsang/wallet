package org.mapsa.wallet.repositories;

import org.mapsa.wallet.models.entity.WalletTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletTransactionRepository extends JpaRepository<WalletTransactionEntity, String> {

}
