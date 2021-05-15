import { Component } from '@angular/core';

@Component({
  selector: 'grid-root',
  template: `
    <nav mat-tab-nav-bar>
      <a mat-tab-link routerLink="/table1">Table 1</a>
      <a mat-tab-link routerLink="/table2">Table 2</a>
      <a mat-tab-link routerLink="/table3">Table 3</a>
      <a mat-tab-link routerLink="/table4">Table 4</a>
      <a mat-tab-link routerLink="/table5">Table 5</a>
    </nav>
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
