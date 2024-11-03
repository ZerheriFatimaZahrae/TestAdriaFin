package zerheri.fatima.walletservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zerheri.fatima.walletservice.entities.Wallet;
import zerheri.fatima.walletservice.repos.WalletRepo;

import java.util.Map;

@RestController

public class WalletRestController {
    @Autowired
    private WalletRepo walletRepo;
    @PostMapping("/debit/{id}")
    public Wallet debitWallet(@PathVariable String id, @RequestBody Map<String, Double> payload) {
        double amount = payload.get("amount");
        Wallet wallet = walletRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Wallet not found"));

        if (wallet.getBalance() < amount) {
            throw new IllegalArgumentException("Solde insuffisant pour le débit.");
        }

        wallet.setBalance(wallet.getBalance() - amount);
        return walletRepo.save(wallet);
    }

    @PostMapping("/credit/{id}")
    public Wallet creditWallet(@PathVariable String id, @RequestBody Map<String, Double> payload) {
        double amount = payload.get("amount");
        Wallet wallet = walletRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Wallet not found"));

        wallet.setBalance(wallet.getBalance() + amount);
        return walletRepo.save(wallet);
    }

}
