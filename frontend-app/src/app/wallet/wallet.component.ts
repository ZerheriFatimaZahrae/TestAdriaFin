import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";

@Component({
  selector: 'app-wallet',
  templateUrl: './wallet.component.html',
  styleUrl: './wallet.component.css'
})
export class WalletComponent implements OnInit {
  wallets :any;
  constructor(private http:HttpClient, private router: Router) { }

  ngOnInit(): void {
    this.http.get("http://localhost:9999/WALLET-SERVICE/wallets")
      .subscribe({
        next : (data)=>{
          this.wallets=data;
        },
        error : (err)=>{}
      });
  }




  getClient(c: any) {
    this.router.navigateByUrl("/clients/"+c.id);
  }
}
