import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { CircuitDetail } from '../circuit-detail/circuit-detail';
import { Circuit } from '../model/circuit.model';
import { CircuitService } from '../services/circuit';

@Component({
  selector: 'app-circuits',
  imports: [CommonModule, FormsModule, RouterLink, CircuitDetail],
  templateUrl: './circuits.html',
  styles: ``,
})
export class Circuits implements OnInit {
  private circuitService = inject(CircuitService);
  private router = inject(Router);

  circuits: Circuit[] = [];

  ngOnInit(): void {
    this.loadCircuits();
  }

  loadCircuits(): void {
    this.circuitService.getAllCircuits().subscribe({
      next: (data) => (this.circuits = data),
      error: (err) => console.error('Erreur chargement circuits', err),
    });
  }

  deleteCircuit(circuit: Circuit): void {
    if (!circuit.idCircuit) {
      return;
    }
    if (!confirm(`Supprimer le circuit "${circuit.nomCircuit}" ?`)) {
      return;
    }
    this.circuitService.deleteCircuit(circuit.idCircuit).subscribe({
      next: () => this.loadCircuits(),
      error: (err) => console.error('Erreur suppression circuit', err),
    });
  }

  onEditCircuit(circuit: Circuit): void {
    this.router.navigate(['/edit-circuit', circuit.idCircuit]);
  }
}
