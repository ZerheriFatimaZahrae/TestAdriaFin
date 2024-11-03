package fatima.zerheri.transferservice;

import fatima.zerheri.transferservice.entities.Transfer;
import fatima.zerheri.transferservice.entities.TransferEtat;
import fatima.zerheri.transferservice.model.Wallet;
import fatima.zerheri.transferservice.repo.TransferRepo;
import fatima.zerheri.transferservice.service.WalletRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class TransferServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransferServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(WalletRestClient walletRestClient,
                            TransferRepo transferRepo,
                            RepositoryRestConfiguration repositoryRestConfiguration) {
        return args -> {
            //pour afficher les ids
            repositoryRestConfiguration.exposeIdsFor(Transfer.class);
            List<Wallet> wallets = walletRestClient.allWallets().getContent().stream().toList();

            for (int i = 1; i <= 5; i++) {
                int indexSender = new Random().nextInt(wallets.size());
                int indexReceiver;
                do {
                    indexReceiver = new Random().nextInt(wallets.size());
                } while (indexReceiver == indexSender);

                Wallet walletSender = wallets.get(indexSender);
                Wallet walletReceiver = wallets.get(indexReceiver);

                double amount = 50 + (100 * Math.random());
                if (walletSender.getBalance() < amount) {
                    continue;
                }

                Transfer transfer = Transfer.builder()
                        .walletSender(walletSender)
                        .walletSenderID(walletSender.getId())
                        .walletReceiver(walletReceiver)
                        .walletReceiverID(walletReceiver.getId())
                        .amount(amount)
                        .transferDate(new Date())
                        .etat(TransferEtat.VALIDATED)
                        .build();

                transferRepo.save(transfer);

                // Créer un Map pour envoyer le montant en JSON
                Map<String, Double> payload = Map.of("amount", amount);

                walletRestClient.debitWallet(walletSender.getId(), payload);
                walletRestClient.creditWallet(walletReceiver.getId(), payload);
            }

            System.out.println("Transfers initialized in the database.");
            transferRepo.findAll().forEach(System.out::println);
        };
    }

}
