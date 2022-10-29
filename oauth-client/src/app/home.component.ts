import {Component} from '@angular/core';
import {AppService} from './app.service';

@Component({
  selector: 'home-header',
  providers: [AppService],
  template: `
    <button *ngIf="!isLoggedIn" (click)="login()" type="submit">
      Login
    </button>
    <div *ngIf="isLoggedIn">
      <div>Logged in</div>
      <div>
        <a (click)="logout()" href="#">Logout</a>
      </div>
      <div>
        <app-details></app-details>
      </div>
    </div>
  `
})
export class HomeComponent {
  public isLoggedIn = false;

  constructor(private appService: AppService) {
  }

  ngOnInit() {
    // this.isLoggedIn = this.appService.checkCredentials();
    // let i = window.location.href.indexOf('code');
    // if (!this.isLoggedIn && i != -1) {
    //   this.appService.retrieveToken(window.location.href.substring(i + 5));
    // }
  }

  login() {
    const authorizationUrl = 'http://127.0.0.1:4200/resource-server/oauth2/authorization/osm';
    const successUrl = window.location.href;
    const failureUrl = window.location.origin + '/login-failure';
    window.location.href = `${authorizationUrl}?successUrl=${successUrl}&failureUrl=${failureUrl}`;
  }

  logout() {
    this.appService.logout();
  }
}
