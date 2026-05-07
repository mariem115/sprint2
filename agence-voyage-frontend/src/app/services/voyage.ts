import { HttpClient, HttpHeaders } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Voyage } from '../model/voyage.model';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};

@Injectable({
  providedIn: 'root',
})
export class VoyageService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/agence/api';

  getAllVoyages(): Observable<Voyage[]> {
    return this.http.get<Voyage[]>(`${this.apiUrl}/voyages`);
  }

  getVoyageById(id: number): Observable<Voyage> {
    return this.http.get<Voyage>(`${this.apiUrl}/voyages/${id}`);
  }

  saveVoyage(voyage: Voyage): Observable<Voyage> {
    return this.http.post<Voyage>(`${this.apiUrl}/voyages`, voyage, httpOptions);
  }

  updateVoyage(id: number, voyage: Voyage): Observable<Voyage> {
    return this.http.put<Voyage>(`${this.apiUrl}/voyages/${id}`, voyage, httpOptions);
  }

  deleteVoyage(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/voyages/${id}`);
  }

  getVoyagesByCircuit(idCircuit: number): Observable<Voyage[]> {
    return this.http.get<Voyage[]>(`${this.apiUrl}/voyages/byCircuit/${idCircuit}`);
  }

  searchByDestination(keyword: string): Observable<Voyage[]> {
    return this.http.get<Voyage[]>(
      `${this.apiUrl}/voyages/byDestination?destination=${encodeURIComponent(keyword)}`,
    );
  }

  uploadImage(id: number, file: File): Observable<Voyage> {
    const formData = new FormData();
    formData.append('file', file);
    return this.http.post<Voyage>(`${this.apiUrl}/uploadImage/${id}`, formData);
  }

  deleteImage(id: number): Observable<Voyage> {
    return this.http.delete<Voyage>(`${this.apiUrl}/uploadImage/${id}`);
  }
}
