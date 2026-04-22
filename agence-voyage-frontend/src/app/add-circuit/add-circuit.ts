import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { Circuit } from '../model/circuit.model';
import { CircuitService } from '../services/circuit';

@Component({
  selector: 'app-add-circuit',
  imports: [CommonModule, FormsModule],
  templateUrl: './add-circuit.html',
  styles: ``,
})
export class AddCircuit {
  private circuitService = inject(CircuitService);
  private router = inject(Router);

  circuit: Circuit = new Circuit();

  onSave(): void {
    this.circuitService.saveCircuit(this.circuit).subscribe({
      next: () => this.router.navigate(['/circuits']),
      error: (err) => console.error('Erreur ajout circuit', err),
    });
  }
}
