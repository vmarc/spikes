import { Component } from '@angular/core';

@Component({
  selector: 'grid-root',
  template: `
    <grid-menu></grid-menu>
    <div class="page">
      <router-outlet></router-outlet>
    </div>
  `,
  styles: [
    `
      .page {
        margin: 2em;
      }
    `,
  ],
})
export class AppComponent {}
