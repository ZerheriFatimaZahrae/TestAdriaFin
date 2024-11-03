package fatima.zerheri.transferservice.model;

import lombok.Data;

import java.util.List;
@Data
public class Client {
    private Long id;
    private String name;
    private String email;
    private List<Wallet> walletList;
}
