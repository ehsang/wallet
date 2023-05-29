package org.mapsa.wallet.controllers;

import org.mapsa.wallet.models.dto.WalletDto;
import org.mapsa.wallet.models.dto.WalletTransactionDto;
import org.mapsa.wallet.models.entity.WalletEntity;
import org.mapsa.wallet.services.interfaces.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wallets")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping
    public ResponseEntity<WalletEntity> createWallet(@RequestBody WalletEntity wallet) {
        WalletEntity createdWallet = walletService.createWallet(wallet);
        return ResponseEntity.ok(createdWallet);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WalletEntity> getWalletById(@PathVariable("id") String id) {
        WalletEntity wallet = walletService.getWalletById(id);
        return ResponseEntity.ok(wallet);
    }

    @GetMapping
    public ResponseEntity<List<WalletEntity>> getAllWallets() {
        List<WalletEntity> walletEntityList = walletService.getAllWallets();
        return ResponseEntity.ok(walletEntityList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WalletEntity> updateWallet(@PathVariable("id") String id,
                                                     @RequestBody WalletEntity wallet) {
        WalletEntity existingWallet = walletService.getWalletById(id);
        if (existingWallet != null) {
            wallet.setId(id);
            walletService.updateWallet(wallet);
            return ResponseEntity.ok(wallet);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWallet(@PathVariable("id") String id) {
        WalletEntity existingWallet = walletService.getWalletById(id);
        if (existingWallet != null) {
            walletService.deleteWallet(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //End point for depositing  to a wallet
    @PostMapping("/deposit")
    public ResponseEntity<WalletDto> depositToWallet(@RequestBody WalletTransactionDto walletTransactionDto) {
        //walletService.depositToWallet(walletId, amount);
        //return ResponseEntity.ok().build();
        WalletDto updatedWallet = walletService.depositToWallet(walletTransactionDto.getId(), walletTransactionDto.getAmount());
        return ResponseEntity.ok(updatedWallet);
    }

    //End point for withdrawing from a wallet
    @PostMapping("/withdraw")
    public ResponseEntity<WalletDto> withdrawFromWallet(@RequestBody WalletTransactionDto walletTransactionDto) {
        WalletDto updatedWallet = walletService.withdrawFromWallet(walletTransactionDto.getId(), walletTransactionDto.getAmount());
        return ResponseEntity.ok(updatedWallet);
    }
}
