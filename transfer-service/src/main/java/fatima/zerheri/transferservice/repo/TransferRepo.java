package fatima.zerheri.transferservice.repo;

import fatima.zerheri.transferservice.entities.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource
public interface TransferRepo extends JpaRepository<Transfer,Long> {

}
