import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { Voyage } from '../model/voyage.model';
import { VoyageService } from '../services/voyage';
import { VoyageDetail } from '../voyage-detail/voyage-detail';

@Component({
  selector: 'app-voyages',
  imports: [CommonModule, FormsModule, RouterLink, VoyageDetail],
  templateUrl: './voyages.html',
  styles: ``,
})
export class Voyages implements OnInit {
  private voyageService = inject(VoyageService);
  private router = inject(Router);

  voyages: Voyage[] = [];
  allVoyages: Voyage[] = [];
  searchKeyword = '';

  ngOnInit(): void {
    this.loadVoyages();
  }

  loadVoyages(): void {
    this.voyageService.getAllVoyages().subscribe({
      next: (data) => {
        this.allVoyages = data;
        this.voyages = data;
      },
      error: (err) => console.error('Erreur chargement voyages', err),
    });
  }

  deleteVoyage(voyage: Voyage): void {
    if (!voyage.idVoyage) {
      return;
    }
    if (!confirm(`Supprimer le voyage vers ${voyage.destination} ?`)) {
      return;
    }
    this.voyageService.deleteVoyage(voyage.idVoyage).subscribe({
      next: () => this.loadVoyages(),
      error: (err) => console.error('Erreur suppression voyage', err),
    });
  }

  onEditVoyage(voyage: Voyage): void {
    this.router.navigate(['/edit-voyage', voyage.idVoyage]);
  }

  searchVoyages(): void {
    const kw = this.searchKeyword.trim();
    if (kw === '') {
      this.voyages = this.allVoyages;
      return;
    }
    this.voyages = this.allVoyages.filter((v) =>
      v.destination?.toLowerCase().includes(kw.toLowerCase()),
    );
  }

  resetSearch(): void {
    this.searchKeyword = '';
    this.voyages = this.allVoyages;
  }
}
