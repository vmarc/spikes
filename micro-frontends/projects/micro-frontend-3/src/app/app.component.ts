import {Location} from '@angular/common';
import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {environment} from '../environments/environment';

@Component({
  selector: 'app-root',
  template: `
    <div style="background-color: beige; padding: 20px;">
      <h1>Micro frontend 3</h1>
      <p>Served from port 4220</p>
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
