import { Component } from '@angular/core';

@Component({
  selector: 'grid-menu',
  template: `
    <nav mat-tab-nav-bar>
      <a mat-tab-link routerLink="/networks" i18n="@@nav.networks">Networks</a>
      <a mat-tab-link routerLink="/paging" i18n="@@nav.paging">Paging</a>
      <a mat-tab-link routerLink="/table3" i18n="@@nav.table3">Table 3</a>
      <a mat-tab-link routerLink="/table4" i18n="@@nav.table4">Table 4</a>
      <a mat-tab-link routerLink="/table5" i18n="@@nav.table5">Table 5</a>
    </nav>
  `,
})
export class MenuComponent {}
