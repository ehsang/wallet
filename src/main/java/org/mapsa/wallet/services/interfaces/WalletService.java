package org.mapsa.wallet.services.interfaces;

import org.mapsa.wallet.models.dto.WalletDto;
import org.mapsa.wallet.models.entity.WalletEntity;

import java.util.List;

public interface WalletService {
    WalletEntity createWallet(WalletEntity walletEntity);
    WalletEntity getWalletById(String id);
    List<WalletEntity> getAllWallets ();
    void updateWallet(WalletEntity walletEntity);
    void deleteWallet(String id);

    //void depositToWallet(String walletId, Long amount);

    WalletDto depositToWallet(String walletId, Long amount);

    WalletDto withdrawFromWallet(String walletId, Long amount);
}
