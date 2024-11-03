package fatima.zerheri.transferservice.web;

import fatima.zerheri.transferservice.entities.Transfer;
import fatima.zerheri.transferservice.model.Client;
import fatima.zerheri.transferservice.model.TransferRequest;
import fatima.zerheri.transferservice.model.Wallet;
import fatima.zerheri.transferservice.repo.TransferRepo;
import fatima.zerheri.transferservice.service.TransferService;
import fatima.zerheri.transferservice.service.WalletRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransferRestontroller {

    private TransferRepo transferRepo;

    private WalletRestClient walletRestClient;

    private TransferService transferService;

    public TransferRestontroller(TransferRepo transferRepo, WalletRestClient walletRestClient, TransferService transferService) {
        this.transferRepo = transferRepo;
        this.walletRestClient = walletRestClient;
        this.transferService = transferService;
    }

    @GetMapping("/fullTransfer/{id}")
    public Transfer getTransfer(@PathVariable Long id){
        Transfer transfer=transferRepo.findById(id).get();
        //customerRestClient qui va envoyer la requete au service CUSTOMERSERVICE POUR savoir le client associe avec la facture
        Wallet walletSender=walletRestClient.findWalletById(transfer.getWalletSenderID());
        Wallet walletRecevier=walletRestClient.findWalletById(transfer.getWalletReceiverID());
       Client clientSender = walletRestClient.findWalletClientById(transfer.getWalletSenderID());
        Client clientReceiver = walletRestClient.findWalletClientById(transfer.getWalletReceiverID());
//
      walletSender.setClient(clientSender);
      walletRecevier.setClient(clientReceiver);
        transfer.setWalletSender(walletSender);
        transfer.setWalletReceiver(walletRecevier);

        return transfer;
    }

    @PostMapping("/process")
    public Transfer processTransfer(@RequestBody TransferRequest transferRequest) {
        return transferService.processTransfer(transferRequest);
    }


}
