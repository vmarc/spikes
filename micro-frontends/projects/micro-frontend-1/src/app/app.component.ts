import {Location} from '@angular/common';
import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {environment} from '../environments/environment';

@Component({
  selector: 'app-root',
  template: `
    <div style="background-color: antiquewhite; padding: 20px;">
      <h1>Micro frontend 1</h1>
      <p>Served from port 4210</p>
      <nav>
        <ul>
          <li><a routerLink="/micro-frontend-1/page-1">Page 1</a></li>
          <li><a routerLink="/micro-frontend-1/page-2">Page 2</a></li>
          <li><a routerLink="/micro-frontend-1/page-3">Page 3</a></li>
          <li><a routerLink="/micro-frontend-1">Main</a></li>
        </ul>
      </nav>
      <router-outlet></router-outlet>
      <mfe-fragment baseUrl="http://localhost:4240"></mfe-fragment>
    </div>
  `
})
export class AppComponent implements OnInit {

  public constructor(
    private readonly location: Location,
    private readonly router: Router
  ) {
  }

  public ngOnInit(): void {
    if (environment.production) {
      this.router.initialNavigation();
      this.router.navigate([this.location.path()]);
    }
  }
}
