package fatima.zerheri.transferservice.model;

import lombok.Data;

import java.util.Date;

@Data
public class Wallet {
    private String id;
    private Double balance;
    private String currency;
    private Date creationDate;
    private Client client;
}
