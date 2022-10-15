import { Component } from '@angular/core';
import { AppService } from './app.service';

@Component({
  selector: 'app-page1',
  template: `
    <h1>Page 1</h1>
    <div>
      <a routerLink="/page2">page 2</a>
    </div>
    <div>
      <button (click)="send()">Send message</button>
    </div>
  `,
})
export class Page1Component {
  constructor(private appService: AppService) {}

  send(): void {
    this.appService.send('message text');
  }
}
