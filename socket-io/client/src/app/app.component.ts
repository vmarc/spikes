import { Component } from '@angular/core';
import { ChatService } from './chat.service';

@Component({
  selector: 'app-root',
  template: `
    <ng-container *ngIf="chatAppData$ | async as data">
      <div *ngIf="data.connected; else login">
        <app-chat [data]="data"/>
      </div>
      <ng-template #login>
        <app-login/>
      </ng-template>
    </ng-container>
  `,
})
export class AppComponent {
  chatAppData$ = this.chatService.chatAppData$;

  constructor(private chatService: ChatService) {
  }
}
