import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Router} from "@angular/router";

@Component({
  selector: 'app-transfers',
  templateUrl: './transfers.component.html',
  styleUrls: ['./transfers.component.css']
})
export class TransfersComponent implements OnInit {
  transfers: any[] = [];

  constructor(private http: HttpClient,private router: Router) {}

  ngOnInit(): void {
    this.loadTransfers();
  }

  loadTransfers() {
    this.http.get("http://localhost:9999/TRANSFER-SERVICE/transfers")
      .subscribe({
        next: (data: any) => {
          this.transfers = data._embedded.transfers;
        },
        error: (err) => {
          console.error("Erreur lors de la récupération des transferts", err);
        }
      });
  }

  getDetails(transfer: any) {
    this.router.navigateByUrl("/transfer-detaills/"+transfer.id);
  }
}
