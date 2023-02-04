import { Input } from '@angular/core';
import { Component } from '@angular/core';
import { ChatAppData } from '../domain/chat-app-data';

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
