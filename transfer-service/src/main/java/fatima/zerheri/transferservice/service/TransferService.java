package fatima.zerheri.transferservice.service;

import fatima.zerheri.transferservice.entities.Transfer;
import fatima.zerheri.transferservice.entities.TransferEtat;
import fatima.zerheri.transferservice.model.TransferRequest;
import fatima.zerheri.transferservice.model.Wallet;
import fatima.zerheri.transferservice.repo.TransferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

@Service
public class TransferService {
    @Autowired
    private TransferRepo transferRepo;

    @Autowired
    private WalletRestClient walletRestClient;




    @Transactional
    public Transfer processTransfer(TransferRequest transferRequest) { // Utiliser TransferRequest
        String senderWalletId = transferRequest.getSenderWalletId();
        String receiverWalletId = transferRequest.getReceiverWalletId();
        double amount = transferRequest.getAmount();

        // Récupérer les portefeuilles source et destination
        Wallet walletSender = walletRestClient.findWalletById(senderWalletId);
        Wallet walletReceiver = walletRestClient.findWalletById(receiverWalletId);

        // Vérifier si les portefeuilles existent
        if (walletSender == null || walletReceiver == null) {
            throw new IllegalArgumentException("Un des portefeuilles est introuvable.");
        }

        // Vérifier si le solde est suffisant dans le portefeuille source
        if (walletSender.getBalance() < amount) {
            throw new IllegalArgumentException("Solde insuffisant.");
        }

        // Créer l'objet Transfer
        Transfer transfer = new Transfer();
        transfer.setWalletSenderID(senderWalletId);
        transfer.setWalletReceiverID(receiverWalletId);
        transfer.setWalletSender(walletSender);
        transfer.setWalletReceiver(walletReceiver);
        transfer.setAmount(amount);
        transfer.setTransferDate(new Date());
        transfer.setEtat(TransferEtat.PENDIND); // Corrigé de PENDIND à PENDING

        Map<String, Double> payload = Map.of("amount", amount);

        // Débiter le portefeuille source et créditer le portefeuille destination
        walletRestClient.debitWallet(senderWalletId, payload);
        walletRestClient.creditWallet(receiverWalletId, payload);

        // Changer l'état du transfert à VALIDATED après la réussite de l'opération
        transfer.setEtat(TransferEtat.VALIDATED);

        // Enregistrer le transfert dans la base de données
        return transferRepo.save(transfer);
    }

}
