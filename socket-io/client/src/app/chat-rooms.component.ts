import { Input } from '@angular/core';
import { Component } from '@angular/core';
import { ChatAppData, ChatService } from './chat.service';

@Component({
  selector: 'app-chat-rooms',
  template: `
    <div class="rooms">
      <h3>Rooms:</h3>
      <ul>
        <li
          [ngClass]="{ active: data.activeRoom === room }"
          (click)="switchRoom(room)"
          *ngFor="let room of data.rooms"
        >
          {{ room }}
        </li>
      </ul>
    </div>
  `,
})
export class ChatRoomsComponent {
  @Input() data!: ChatAppData;

  constructor(private chatService: ChatService) {
  }

  switchRoom(room: string) {
    this.chatService.switchRoom(room);
  }
}
