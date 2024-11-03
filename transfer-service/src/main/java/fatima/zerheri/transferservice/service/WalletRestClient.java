package fatima.zerheri.transferservice.service;

import fatima.zerheri.transferservice.model.Client;
import fatima.zerheri.transferservice.model.Wallet;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "WALLET-SERVICE") // va etre base sur openFeign
//va envoyer la request au costumerservice pour savoir le id de client
public interface WalletRestClient {
        @GetMapping(path = "/clients/{id}")
        public Client findCustomerById(@PathVariable Long id);
        @GetMapping(path = "/wallets/{id}/client")
        public Client findWalletClientById(@PathVariable String id);
        @GetMapping(path = "/clients")
        public PagedModel<Client> allCustomers();

        @GetMapping(path = "/wallets/{id}")
        public Wallet findWalletById(@PathVariable String id);


        @GetMapping(path = "/wallets")
        public PagedModel<Wallet> allWallets();
        // New endpoint for debiting a wallet
        // Endpoint pour débiter un portefeuille
        @PostMapping("/debit/{walletId}")
        @Headers("Content-Type: application/json")
        void debitWallet(@PathVariable("walletId") String walletId, @RequestBody Map<String, Double> payload);

        // Endpoint pour créditer un portefeuille
        @PostMapping("/credit/{walletId}")
        @Headers("Content-Type: application/json")
        void creditWallet(@PathVariable("walletId") String walletId, @RequestBody Map<String, Double> payload);

}
