package org.mapsa.wallet.services;

import org.mapsa.wallet.models.entity.WalletTransactionEntity;
import org.mapsa.wallet.repositories.WalletTransactionRepository;
import org.mapsa.wallet.services.interfaces.WalletTransactionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletTransactionServiceImpl implements WalletTransactionService {

    private final WalletTransactionRepository walletTransactionRepository;

    public WalletTransactionServiceImpl(WalletTransactionRepository walletTransactionRepository){
        this.walletTransactionRepository = walletTransactionRepository;
    }

    @Override
    public WalletTransactionEntity createTransaction(WalletTransactionEntity walletTransactionEntity) {
        return walletTransactionRepository.save(walletTransactionEntity);
    }

    @Override
    public WalletTransactionEntity getTransactionById(String id) {
        return walletTransactionRepository.findById(id).orElse(null);
    }

    @Override
    public List<WalletTransactionEntity> getAllTransactions() {
        return walletTransactionRepository.findAll();
    }

    @Override
    public void updateTransaction(WalletTransactionEntity walletTransactionEntity) {
        walletTransactionRepository.save(walletTransactionEntity);
    }

    @Override
    public void deleteTransaction(String id) {
        walletTransactionRepository.deleteById(id);
    }
}
