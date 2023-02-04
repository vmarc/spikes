import { Component } from '@angular/core';
import { ChatService } from './chat.service';

@Component({
  selector: 'app-chat-box',
  template: `
    <div class="chat-box">
      <input
        type="text"
        name="message"
        [(ngModel)]="message"
        (keyup.enter)="sendMessage()"
      />
      <button type="button" (click)="sendMessage()">Send</button>
    </div>
  `,
})
export class ChatBoxComponent {
  message = '';

  constructor(private chatService: ChatService) {
  }

  sendMessage() {
    if (this.message) {
      this.chatService.sendMessage(this.message);
      this.message = '';
    }
  }
}
