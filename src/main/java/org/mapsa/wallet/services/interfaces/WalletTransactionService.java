package org.mapsa.wallet.services.interfaces;

import org.mapsa.wallet.models.entity.WalletEntity;
import org.mapsa.wallet.models.entity.WalletTransactionEntity;

import java.util.List;

public interface WalletTransactionService {
    WalletTransactionEntity createTransaction(WalletTransactionEntity walletTransactionEntity);
    WalletTransactionEntity getTransactionById(String id);
    List<WalletTransactionEntity> getAllTransactions ();
    void updateTransaction(WalletTransactionEntity walletTransactionEntity);
    void deleteTransaction(String id);
}
