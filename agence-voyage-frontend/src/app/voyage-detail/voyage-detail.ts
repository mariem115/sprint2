import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Voyage } from '../model/voyage.model';

@Component({
  selector: 'app-voyage-detail',
  imports: [CommonModule],
  templateUrl: './voyage-detail.html',
  styles: ``,
})
export class VoyageDetail {
  @Input() voyage!: Voyage;
  @Output() deleteEvent = new EventEmitter<Voyage>();
  @Output() editEvent = new EventEmitter<Voyage>();

  onDelete(): void {
    this.deleteEvent.emit(this.voyage);
  }

  onEdit(): void {
    this.editEvent.emit(this.voyage);
  }
}
