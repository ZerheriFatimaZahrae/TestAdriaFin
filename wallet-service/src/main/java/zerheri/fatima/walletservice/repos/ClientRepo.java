package zerheri.fatima.walletservice.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import zerheri.fatima.walletservice.entities.Client;
@RestResource
public interface ClientRepo extends JpaRepository<Client,Long> {
}
