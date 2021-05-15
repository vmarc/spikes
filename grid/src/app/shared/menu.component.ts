import { Component } from '@angular/core';

@Component({
  selector: 'grid-menu',
  template: `
    <nav mat-tab-nav-bar>
      <a mat-tab-link routerLink="/table1" i18n="@@nav.table1">Table 1</a>
      <a mat-tab-link routerLink="/table2" i18n="@@nav.table2">Table 2</a>
      <a mat-tab-link routerLink="/table3" i18n="@@nav.table3">Table 3</a>
      <a mat-tab-link routerLink="/table4" i18n="@@nav.table4">Table 4</a>
      <a mat-tab-link routerLink="/table5" i18n="@@nav.table5">Table 5</a>
    </nav>
  `,
})
export class MenuComponent {}
