package zerheri.fatima.walletservice.entities;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Wallet {
    @Id
    private String id;

    private Double balance;
    private String currency;
    private Date creationDate;

    @ManyToOne
    private Client client;
}