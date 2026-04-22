import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Circuit } from '../model/circuit.model';
import { CircuitService } from '../services/circuit';

@Component({
  selector: 'app-edit-circuit',
  imports: [CommonModule, FormsModule],
  templateUrl: './edit-circuit.html',
  styles: ``,
})
export class EditCircuit implements OnInit {
  private route = inject(ActivatedRoute);
  private router = inject(Router);
  private circuitService = inject(CircuitService);

  circuit: Circuit = new Circuit();
  id!: number;

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.circuitService.getCircuitById(this.id).subscribe({
      next: (c) => (this.circuit = c),
      error: (err) => console.error('Erreur chargement circuit', err),
    });
  }

  onUpdate(): void {
    this.circuitService.updateCircuit(this.id, this.circuit).subscribe({
      next: () => this.router.navigate(['/circuits']),
      error: (err) => console.error('Erreur modification circuit', err),
    });
  }
}
