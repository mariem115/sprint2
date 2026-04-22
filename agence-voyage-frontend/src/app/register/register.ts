import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { User } from '../model/user.model';
import { AuthService } from '../services/auth';

@Component({
  selector: 'app-register',
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './register.html',
  styles: ``,
})
export class Register {
  private authService = inject(AuthService);
  private router = inject(Router);

  user: User = new User();
  confirmPassword = '';
  verificationStep = false;
  verificationCode = '';
  errorMessage = '';
  successMessage = '';

  onRegister(): void {
    this.errorMessage = '';
    if (this.user.password !== this.confirmPassword) {
      this.errorMessage = 'Les mots de passe ne correspondent pas.';
      return;
    }
    this.authService.register(this.user).subscribe({
      next: () => {
        this.verificationStep = true;
        this.successMessage = 'Inscription réussie. Vérifiez votre email.';
      },
      error: (err) => {
        this.errorMessage = 'Erreur lors de l\'inscription. ' + (err?.error?.message || '');
      },
    });
  }

  onVerify(): void {
    this.errorMessage = '';
    this.authService.verifyEmail(this.user.username, this.verificationCode).subscribe({
      next: (msg) => {
        if (msg && msg.toLowerCase().includes('success')) {
          this.router.navigate(['/login']);
        } else {
          this.errorMessage = msg || 'Code invalide.';
        }
      },
      error: () => {
        this.errorMessage = 'Erreur lors de la vérification.';
      },
    });
  }
}
