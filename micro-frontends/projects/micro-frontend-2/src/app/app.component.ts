import {Location} from '@angular/common';
import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {environment} from '../environments/environment';

@Component({
  selector: 'app-root',
  template: `
    <div style="background-color: lavender; padding: 20px;">
      <h1>Micro frontend 2</h1>
      <p>Served from port 4220</p>
      <router-outlet></router-outlet>
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
