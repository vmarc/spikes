import { Component } from '@angular/core';
import { ChatService } from './chat/chat.service';

@Component({
  selector: 'app-login',
  template: `
    <div>
      <label
      >Name
        <input
          type="text"
          name="name"
          [(ngModel)]="user"
          (keyup.enter)="connect()"
        />
      </label>
      <button type="button" (click)="connect()">Connect</button>
    </div>
  `,
})
export class LoginComponent {
  user = '';

  constructor(private chatService: ChatService) {
  }

  connect() {
    if (this.user) {
      this.chatService.connect(this.user);
    }
  }
}
