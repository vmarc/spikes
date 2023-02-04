import { Input } from '@angular/core';
import { Component } from '@angular/core';
import { ChatAppData, ChatService } from './chat.service';

@Component({
  selector: 'app-chat-users',
  template: `
    <div class="users">
      <h3>Users:</h3>
      <ul>
        <li *ngFor="let user of data.chatRoom.users">{{ user }}</li>
      </ul>
    </div>
  `,
})
export class ChatUsersComponent {
  @Input() data!: ChatAppData;
}
