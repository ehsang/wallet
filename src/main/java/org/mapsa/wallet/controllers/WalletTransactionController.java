package org.mapsa.wallet.controllers;

import org.mapsa.wallet.models.entity.WalletTransactionEntity;
import org.mapsa.wallet.services.interfaces.WalletTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/walletTransactions")
public class WalletTransactionController {

    private final WalletTransactionService walletTransactionService;

    public WalletTransactionController(WalletTransactionService walletTransactionService) {
        this.walletTransactionService = walletTransactionService;
    }

    @PostMapping
    public ResponseEntity<WalletTransactionEntity> createWalletTransaction(@RequestBody WalletTransactionEntity walletTransactionEntity) {
        WalletTransactionEntity createdTransaction = walletTransactionService.createTransaction(walletTransactionEntity);
        return ResponseEntity.ok(createdTransaction);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WalletTransactionEntity> getWalletTransactionById(@PathVariable("id")
                                                                            String id) {
        WalletTransactionEntity existingWalletTransaction = walletTransactionService.getTransactionById(id);
        if (existingWalletTransaction != null) {
            return ResponseEntity.ok(existingWalletTransaction);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<WalletTransactionEntity>> getAllWalletTransactions() {
        List<WalletTransactionEntity> walletTransactionEntityList = walletTransactionService.getAllTransactions();
        return ResponseEntity.ok(walletTransactionEntityList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WalletTransactionEntity> updateWalletTransaction(@PathVariable("id") String id,
                                                                           @RequestBody WalletTransactionEntity walletTransactionEntity) {
        WalletTransactionEntity existingWalletTransaction = walletTransactionService.getTransactionById(id);
        if (existingWalletTransaction != null) {
            walletTransactionEntity.setId(id);
            walletTransactionService.updateTransaction(walletTransactionEntity);
            return ResponseEntity.ok(walletTransactionEntity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWalletTransaction(@PathVariable("id") String id) {
        WalletTransactionEntity existingTransaction = walletTransactionService.getTransactionById(id);
        if (existingTransaction != null) {
            walletTransactionService.deleteTransaction(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
