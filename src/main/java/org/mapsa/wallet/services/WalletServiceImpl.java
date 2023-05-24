package org.mapsa.wallet.services;

import org.mapsa.wallet.models.entity.WalletEntity;
import org.mapsa.wallet.repositories.WalletRepository;
import org.mapsa.wallet.services.interfaces.WalletService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public WalletEntity createWallet(WalletEntity walletEntity) {
        return walletRepository.save(walletEntity);
    }

    @Override
    public WalletEntity getWalletById(String id) {
        return walletRepository.findById(id).orElse(null);
    }

    @Override
    public List<WalletEntity> getAllWallets() {
        return walletRepository.findAll();
    }

    @Override
    public void updateWallet(WalletEntity walletEntity) {
        if (walletRepository.existsById(walletEntity.getId())) {
            walletRepository.save(walletEntity);
        } else {
            throw new IllegalArgumentException("Wallet not found with ID: " + walletEntity.getId());
        }
    }

    @Override
    public void deleteWallet(String id) {
        walletRepository.deleteById(id);
    }
}
