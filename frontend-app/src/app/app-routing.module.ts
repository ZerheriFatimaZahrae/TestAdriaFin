import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ClientsComponent} from "./clients/clients.component";
import {WalletComponent} from "./wallet/wallet.component";
import {TransfersComponent} from "./transfers/transfers.component";
import {TransferDetaillsComponent} from "./transfer-detaills/transfer-detaills.component";
import {NewTransferComponent} from "./new-transfer/new-transfer.component";

const routes: Routes = [
  {
    path : "wallets", component : WalletComponent
  },
  {
    path : "clients/:id", component : ClientsComponent
  },
  {
    path : "transfers", component : TransfersComponent
  },
  {
    path : "transfer-detaills/:id", component : TransferDetaillsComponent
  },{
    path : "newTransfer", component : NewTransferComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
