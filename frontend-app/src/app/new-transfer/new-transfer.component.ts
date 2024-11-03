import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-new-transfer',
  templateUrl: './new-transfer.component.html',
  styleUrl: './new-transfer.component.css'
})
export class NewTransferComponent implements OnInit{
  transferForm!: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private http: HttpClient,
              private snackBar: MatSnackBar) {}

  ngOnInit(): void {
    this.transferForm = this.formBuilder.group({
      walletSourceId: this.formBuilder.control('', [Validators.required]),
      walletDestinationId: this.formBuilder.control('', [Validators.required]),
      amount: this.formBuilder.control('', [Validators.required]),
    });
  }

  onSubmit() {
    if (this.transferForm.valid) {
      let transferRequest = {
        senderWalletId: this.transferForm.value.walletSourceId,   // Correspond à senderWalletId
        receiverWalletId: this.transferForm.value.walletDestinationId, // Correspond à receiverWalletId
        amount: this.transferForm.value.amount                       // Correspond à amount
      };

      // Faire un POST vers le backend
      this.http.post('http://localhost:9999/TRANSFER-SERVICE/process', transferRequest).subscribe(
        (response) => {
          console.log('Transfert réussi:', response);
          this.snackBar.open('Transfert effectué avec succès!', 'Fermer', {
            duration: 5000,
            panelClass: ['success-snackbar']
          });
          this.transferForm.reset();
        },
        (error) => {
          console.error('Erreur lors du transfert:', error);
          this.snackBar.open('Échec du transfert. Veuillez réessayer.', 'Fermer', {
            duration: 5000,
            panelClass: ['error-snackbar']
          });
        }
      );
    } else {
      console.log("Le formulaire n'est pas valide");
      this.snackBar.open("Veuillez remplir correctement le formulaire.", "Fermer", {
        duration: 5000,
        panelClass: ['error-snackbar']
      });
    }
  }


}
