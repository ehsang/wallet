package org.mapsa.wallet.services;

import org.mapsa.wallet.configuration.redis.RedisCacheConfig;
import org.mapsa.wallet.models.dto.WalletDto;
import org.mapsa.wallet.models.entity.WalletEntity;
import org.mapsa.wallet.models.entity.WalletTransactionEntity;
import org.mapsa.wallet.repositories.WalletRepository;
import org.mapsa.wallet.repositories.WalletTransactionRepository;
import org.mapsa.wallet.services.interfaces.WalletService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final WalletTransactionRepository walletTransactionRepository;

    //it's not used yet, supposed to make the application understand that using
    // Redis as Caching technique we don't have to explicitly tell our entities they
    // should be serializable to enable caching, but so far it's not working
    private final RedisCacheConfig redisCacheConfig;

    public WalletServiceImpl(WalletRepository walletRepository, WalletTransactionRepository walletTransactionRepository
            , RedisCacheConfig redisCacheConfig) {
        this.walletRepository = walletRepository;
        this.walletTransactionRepository = walletTransactionRepository;
        this.redisCacheConfig = redisCacheConfig;
    }

    @Override
    public WalletEntity createWallet(WalletEntity walletEntity) {
        return walletRepository.save(walletEntity);
    }

    @Override
    @Cacheable(value = "walletInfoCache", key = "#id")
    public WalletEntity getWalletById(String id) {
        if (walletRepository.existsById(id)) {
            return walletRepository.findById(id).orElse(null);
        } else {
            throw new IllegalArgumentException("Wallet not found with ID: " + id);
        }
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

    /*@Override
    public void depositToWallet(String walletId, Long amount) {
        if (amount <= 0){
            throw new IllegalArgumentException("Amount must be a positive value");
        }
        Optional<WalletEntity> wallet = walletRepository.findById(walletId);
        if (wallet.isEmpty()){
            throw new IllegalArgumentException("Wallet not found with ID: " + walletId);
        }
        WalletEntity walletEntity = wallet.get();
        walletEntity.setBalance(walletEntity.getBalance() + amount);
        walletRepository.save(walletEntity);

    }*/

    @Override
    public WalletDto depositToWallet(String walletId, Long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be a positive value");
        }
        Optional<WalletEntity> wallet = walletRepository.findById(walletId);
        if (wallet.isEmpty()) {
            throw new IllegalArgumentException("Wallet not found with ID: " + walletId);
        }

        //perform the deposit operation
        WalletEntity walletEntity = wallet.get();
        walletEntity.setBalance(walletEntity.getBalance() + amount);
        //WalletEntity updatedWallet = walletRepository.save(walletEntity);


        //Record the transaction
        WalletTransactionEntity transaction = new WalletTransactionEntity();
        transaction.setAmount(amount);
        transaction.setWalletEntity(walletEntity);

        //transaction.setWalletEntity(updateWallet);
        walletTransactionRepository.save(transaction);

        //Convert the updated wallet entity to DTO
        WalletDto walletDto = new WalletDto();
        walletDto.setId(walletEntity.getId());
        walletDto.setBalance(walletEntity.getBalance());

        return walletDto;

    }


    @Override
    public WalletDto withdrawFromWallet(String walletId, Long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive value");
        }
        Optional<WalletEntity> optionalWallet = walletRepository.findById(walletId);
        if (optionalWallet.isEmpty()) {
            throw new IllegalArgumentException("Wallet not found with ID:" + walletId);
        }
        WalletEntity walletEntity = optionalWallet.get();
        if (walletEntity.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient funds in the wallet");
        }
        //perform the withdrawal operation
        // WalletEntity walletEntity = wallet.get();
        walletEntity.setBalance(walletEntity.getBalance() - amount);
        //WalletEntity updatedWallet = walletRepository.save(walletEntity);


        //Record the transaction
        WalletTransactionEntity transaction = new WalletTransactionEntity();
        transaction.setAmount(amount);
        transaction.setWalletEntity(walletEntity);

        //transaction.setWalletEntity(updateWallet);
        walletTransactionRepository.save(transaction);

        //Convert the updated wallet entity to DTO
        WalletDto walletDto = new WalletDto();
        walletDto.setId(walletEntity.getId());
        walletDto.setBalance(walletEntity.getBalance());

        return walletDto;
    }

    @Override
    public void deleteWallet(String id) {
        walletRepository.deleteById(id);
    }
}
