package zerheri.fatima.walletservice.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zerheri.fatima.walletservice.entities.Client;
import zerheri.fatima.walletservice.entities.Wallet;
@RestResource

public interface WalletRepo extends JpaRepository<Wallet,String> {


}
