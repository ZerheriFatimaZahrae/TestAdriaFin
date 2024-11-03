package fatima.zerheri.transferservice.model;

import lombok.Data;

@Data
public class TransferRequest {
    private String senderWalletId;
    private String receiverWalletId;
    private double amount;

    // Constructeur
    public TransferRequest(String senderWalletId, String receiverWalletId, double amount) {
        this.senderWalletId = senderWalletId;
        this.receiverWalletId = receiverWalletId;
        this.amount = amount;
    }
}
