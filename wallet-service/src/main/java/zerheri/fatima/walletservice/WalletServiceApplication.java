package zerheri.fatima.walletservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import zerheri.fatima.walletservice.entities.Client;
import zerheri.fatima.walletservice.entities.Wallet;
import zerheri.fatima.walletservice.repos.ClientRepo;
import zerheri.fatima.walletservice.repos.WalletRepo;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class WalletServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WalletServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ClientRepo customerRepository,
                            RepositoryRestConfiguration repositoryRestConfiguration,
                            //ds spring data rest id ne sont pas affiche c est pour cela on va utiliser repo
                            WalletRepo walletRepo){
        return args -> {
            //pour afficher les ids
            repositoryRestConfiguration.exposeIdsFor(Client.class);
            repositoryRestConfiguration.exposeIdsFor(Wallet.class);
            //ajouter des clients saveAll prend une liste customer
            customerRepository.saveAll(List.of(
                    Client.builder().name("Mohamed").email("med@gmail.com").build(),
                    Client.builder().name("Hajar").email("hajar@gmail.com").build(),
                    Client.builder().name("Fatima zahrae").email("fatimazahrae@gmail.com").build()
            ));
            //pour chaque customer appliquer la methode println
            customerRepository.findAll().forEach(System.out::println);

            walletRepo.saveAll(List.of(
                    Wallet.builder()
                            .id(UUID.randomUUID().toString())
                           .balance(Double.valueOf(1000000))
                            .currency("DH")
                            .creationDate(new Date())
                           .client(customerRepository.findById(1L).get())
                           .build(),
                    Wallet.builder()
                            .id(UUID.randomUUID().toString())
                           .balance(5000000.0)
                            .currency("DH")
                            .creationDate(new Date())

                           .client(customerRepository.findById(2L).orElse(null))
                           .build(),
                    Wallet.builder()
                            .id(UUID.randomUUID().toString())
                            .currency("DH")
                            .creationDate(new Date())
                           .balance(20000000.0)
                           .client(customerRepository.findById(3L).orElse(null))
                           .build()
            ));

        };
    }
}
