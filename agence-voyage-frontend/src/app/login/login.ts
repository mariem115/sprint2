import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { User } from '../model/user.model';
import { AuthService } from '../services/auth';

@Component({
  selector: 'app-login',
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './login.html',
  styles: ``,
})
export class Login {
  private authService = inject(AuthService);
  private router = inject(Router);

  user: User = new User();
  errorMessage = '';

  onLoggedIn(): void {
    this.errorMessage = '';
    this.authService.login(this.user.username, this.user.password).subscribe({
      next: (response) => {
        const authHeader = response.headers.get('Authorization');
        if (!authHeader) {
          this.errorMessage = 'Réponse du serveur invalide (pas de token).';
          return;
        }
        const jwt = authHeader.replace('Bearer ', '');
        this.authService.saveToken(jwt);
        this.authService.saveUsername(this.user.username);
        this.router.navigate(['/voyages']);
      },
      error: () => {
        this.errorMessage = 'Identifiants incorrects. Veuillez réessayer.';
      },
    });
  }
}
