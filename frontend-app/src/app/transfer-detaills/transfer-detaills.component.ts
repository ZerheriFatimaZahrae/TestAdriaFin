import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-transfer-detaills',
  templateUrl: './transfer-detaills.component.html',
  styleUrl: './transfer-detaills.component.css'
})
export class TransferDetaillsComponent implements OnInit {
  detaills: any;
  id!: number;


  constructor(private http: HttpClient, private router: Router, private route: ActivatedRoute) {
    this.id = route.snapshot.params['id'];
  }

  ngOnInit(): void {
    this.http.get("http://localhost:9999/TRANSFER-SERVICE/fullTransfer/" + this.id )
      .subscribe({
        next: (data) => {
          this.detaills = data;

        },
        error: (err) => {
        }
      });
  }
}
