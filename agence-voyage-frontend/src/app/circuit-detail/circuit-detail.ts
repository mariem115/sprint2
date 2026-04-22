import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Circuit } from '../model/circuit.model';

@Component({
  selector: 'app-circuit-detail',
  imports: [CommonModule],
  templateUrl: './circuit-detail.html',
  styles: ``,
})
export class CircuitDetail {
  @Input() circuit!: Circuit;
  @Output() deleteEvent = new EventEmitter<Circuit>();
  @Output() editEvent = new EventEmitter<Circuit>();

  onDelete(): void {
    this.deleteEvent.emit(this.circuit);
  }

  onEdit(): void {
    this.editEvent.emit(this.circuit);
  }
}
