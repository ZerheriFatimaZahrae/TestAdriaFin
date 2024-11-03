package fatima.zerheri.transferservice.entities;

import fatima.zerheri.transferservice.model.Wallet;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transfer {
    @Id
    @GeneratedValue
            (strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;
    private TransferEtat etat;

    @Transient
    private Wallet walletSender;

    @Transient
    private Wallet walletReceiver;

    private String walletSenderID;
    private String walletReceiverID;


    private Date transferDate;
}
