import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'frontend-app';
  // Dans votre composant Angular
  logout() {
    // Logique de déconnexion
    console.log("Utilisateur déconnecté");
    // Redirigez l'utilisateur ou nettoyez les données d'authentification
  }

}
