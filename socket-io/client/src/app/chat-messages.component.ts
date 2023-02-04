import { Input } from '@angular/core';
import { Component } from '@angular/core';
import { ChatAppData } from './chat.service';

@Component({
  selector: 'app-chat-messages',
  template: `
    <div *ngIf="data.chatRoom.messages.length > 0; else noMessages">
      <ul>
        <li *ngFor="let message of data.chatRoom.messages">
          [{{ message.user }}]: {{ message.content }}
        </li>
      </ul>
    </div>
    <ng-template #noMessages>No messages in this room</ng-template>
  `,
})
export class ChatMessagesComponent {
  @Input() data!: ChatAppData;
}
