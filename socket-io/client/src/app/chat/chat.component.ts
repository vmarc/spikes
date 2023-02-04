import { Input } from '@angular/core';
import { Component } from '@angular/core';
import { ChatService } from './chat.service';
import { ChatAppData } from '../domain/chat-app-data';

@Component({
  selector: 'app-chat',
  template: `
    <div class="chat-container">
      <div class="left-pane">
        <app-chat-rooms [data]="data"/>
        <app-chat-users [data]="data"/>
        <div class="actions">
          <button type="button" (click)="disconnect()">Disconnect</button>
        </div>
      </div>
      <div class="chat-pane">
        <div class="user">
          Welcome {{ data.user }}, Room {{ data.activeRoom }}
        </div>
        <div class="messages">
          <app-chat-messages [data]="data"/>
        </div>
        <app-chat-box/>
      </div>
    </div>
  `,
})
export class ChatComponent {
  @Input() data!: ChatAppData;

  constructor(private chatService: ChatService) {
  }

  disconnect() {
    this.chatService.disconnect();
  }
}
