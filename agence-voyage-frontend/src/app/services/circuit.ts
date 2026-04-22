import { HttpClient, HttpHeaders } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Circuit } from '../model/circuit.model';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};

@Injectable({
  providedIn: 'root',
})
export class CircuitService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/agence/api';

  getAllCircuits(): Observable<Circuit[]> {
    return this.http.get<Circuit[]>(`${this.apiUrl}/circuits`);
  }

  getCircuitById(id: number): Observable<Circuit> {
    return this.http.get<Circuit>(`${this.apiUrl}/circuits/${id}`);
  }

  saveCircuit(circuit: Circuit): Observable<Circuit> {
    return this.http.post<Circuit>(`${this.apiUrl}/circuits`, circuit, httpOptions);
  }

  updateCircuit(id: number, circuit: Circuit): Observable<Circuit> {
    return this.http.put<Circuit>(`${this.apiUrl}/circuits/${id}`, circuit, httpOptions);
  }

  deleteCircuit(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/circuits/${id}`);
  }
}
