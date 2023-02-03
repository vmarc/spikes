import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { ChatAppData, ChatService } from './chat.service';

@Component({
  selector: 'app-root',
  template: `
    <ng-container *ngIf="chatAppData$ | async as data">
      <div *ngIf="data.connected; else login">
        <div class="chat-container">
          <div class="left-pane">
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

            <div class="users">
              <h3>Users:</h3>
              <ul>
                <li *ngFor="let user of data.chatRoom.users">{{ user }}</li>
              </ul>
            </div>
            <div class="actions">
              <button type="button" (click)="disconnect()">Disconnect</button>
            </div>
          </div>

          <div class="chat-pane">
            <div class="user">
              Welcome {{ data.user }}, Room {{ data.activeRoom }}
            </div>
            <div class="messages">
              <div *ngIf="data.chatRoom.messages.length > 0; else noMessages">
                <ul>
                  <li *ngFor="let message of data.chatRoom.messages">
                    [{{ message.user }}]: {{ message.content }}
                  </li>
                </ul>
              </div>
              <ng-template #noMessages>No messages in this room</ng-template>
            </div>

            <div class="chat-box">
              <input
                type="text"
                name="message"
                [(ngModel)]="message"
                (keyup.enter)="sendMessage()"
              />
              <button type="button" (click)="sendMessage()">Send</button>
            </div>
          </div>
        </div>
      </div>

      <ng-template #login>
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
      </ng-template>
    </ng-container>
  `,
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  message = '';
  user = '';
  chatAppData$!: Observable<ChatAppData>;

  constructor(private chatService: ChatService) {
  }

  ngOnInit(): void {
    this.chatAppData$ = this.chatService.getChatAppData();
  }

  connect() {
    if (this.user) {
      this.chatService.connect(this.user);
    }
  }

  disconnect() {
    this.user = '';
    this.chatService.disconnect();
  }

  sendMessage() {
    if (this.message) {
      this.chatService.sendMessage(this.message);
      this.message = '';
    }
  }

  switchRoom(room: string) {
    this.chatService.switchRoom(room);
  }
}
