import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { Circuit } from '../model/circuit.model';
import { Voyage } from '../model/voyage.model';
import { CircuitService } from '../services/circuit';
import { VoyageService } from '../services/voyage';

@Component({
  selector: 'app-add-voyage',
  imports: [CommonModule, FormsModule],
  templateUrl: './add-voyage.html',
  styles: ``,
})
export class AddVoyage implements OnInit {
  private voyageService = inject(VoyageService);
  private circuitService = inject(CircuitService);
  private router = inject(Router);

  voyage: Voyage = new Voyage();
  circuits: Circuit[] = [];

  ngOnInit(): void {
    this.circuitService.getAllCircuits().subscribe({
      next: (data) => (this.circuits = data),
      error: (err) => console.error('Erreur chargement circuits', err),
    });
  }

  onSave(): void {
    this.voyageService.saveVoyage(this.voyage).subscribe({
      next: () => this.router.navigate(['/voyages']),
      error: (err) => console.error('Erreur ajout voyage', err),
    });
  }
}
