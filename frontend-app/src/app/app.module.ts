import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { WalletComponent } from './wallet/wallet.component';
import {HttpClientModule} from "@angular/common/http";
import { ClientsComponent } from './clients/clients.component';
import { TransfersComponent } from './transfers/transfers.component';
import { TransferDetaillsComponent } from './transfer-detaills/transfer-detaills.component';

@NgModule({
  declarations: [
    AppComponent,
    WalletComponent,
    ClientsComponent,
    TransfersComponent,
    TransferDetaillsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [
    provideClientHydration()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
