import { Circuit } from './circuit.model';

export class Voyage {
  idVoyage?: number;
  destination?: string;
  prix?: number;
  dateDepart?: Date;
  dateRetour?: Date;
  circuit?: Circuit;
  idCircuit?: number;
  nomCircuit?: string;
  imageUrl?: string;
}
