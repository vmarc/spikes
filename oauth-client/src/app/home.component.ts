import {Component} from '@angular/core';
import {AppService} from './app.service';

@Component({
  selector: 'home-header',
  providers: [AppService],
  template: `
    <div>
      <button (click)="login()" type="submit">
        Login
      </button>
    </div>
    <div>
      <button (click)="logout()" type="submit">Logout</button>
    </div>
  `
})
export class HomeComponent {

  constructor(private appService: AppService) {
  }

  login() {
    this.appService.login();
  }

  logout() {
    this.appService.logout();
  }
}
