import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html',
  styleUrl: './clients.component.css'
})
export class ClientsComponent implements OnInit {
  client: any;
  id!: number;
  totalAmount: number = 0;

  constructor(private http: HttpClient, private router: Router, private route: ActivatedRoute) {
    this.id = route.snapshot.params['id'];
  }

  ngOnInit(): void {
    this.http.get("http://localhost:9999/WALLET-SERVICE/wallets/" + this.id + "/client")
      .subscribe({
        next: (data) => {
          this.client = data;

        },
        error: (err) => {
        }
      });
  }
}
