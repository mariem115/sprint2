import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Circuit } from '../model/circuit.model';
import { Voyage } from '../model/voyage.model';
import { CircuitService } from '../services/circuit';
import { VoyageService } from '../services/voyage';

@Component({
  selector: 'app-edit-voyage',
  imports: [CommonModule, FormsModule],
  templateUrl: './edit-voyage.html',
  styles: ``,
})
export class EditVoyage implements OnInit {
  private route = inject(ActivatedRoute);
  private router = inject(Router);
  private voyageService = inject(VoyageService);
  private circuitService = inject(CircuitService);

  voyage: Voyage = new Voyage();
  circuits: Circuit[] = [];
  id!: number;

  selectedFile: File | null = null;
  uploadMessage = '';
  readonly backendOrigin = 'http://localhost:8080';

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.circuitService.getAllCircuits().subscribe({
      next: (data) => (this.circuits = data),
      error: (err) => console.error('Erreur chargement circuits', err),
    });
    this.voyageService.getVoyageById(this.id).subscribe({
      next: (v) => (this.voyage = v),
      error: (err) => console.error('Erreur chargement voyage', err),
    });
  }

  onUpdate(): void {
    this.voyageService.updateVoyage(this.id, this.voyage).subscribe({
      next: () => this.router.navigate(['/voyages']),
      error: (err) => console.error('Erreur modification voyage', err),
    });
  }

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    this.selectedFile = input.files && input.files.length > 0 ? input.files[0] : null;
  }

  uploadImage(): void {
    if (!this.selectedFile || !this.voyage.idVoyage) {
      return;
    }
    this.uploadMessage = '';
    this.voyageService.uploadImage(this.voyage.idVoyage, this.selectedFile).subscribe({
      next: (updated) => {
        this.voyage = updated;
        this.uploadMessage = 'Image uploadée avec succès.';
      },
      error: (err) => {
        console.error('Erreur upload image', err);
        this.uploadMessage = 'Échec de l\'upload.';
      },
    });
  }

  deleteImage(): void {
    if (!this.voyage.idVoyage) {
      return;
    }
    if (!confirm('Supprimer l\'image de ce voyage ?')) {
      return;
    }
    this.uploadMessage = '';
    this.voyageService.deleteImage(this.voyage.idVoyage).subscribe({
      next: (updated) => {
        this.voyage = updated;
        this.selectedFile = null;
        this.uploadMessage = 'Image supprimée.';
      },
      error: (err) => {
        console.error('Erreur suppression image', err);
        this.uploadMessage = 'Échec de la suppression.';
      },
    });
  }
}
