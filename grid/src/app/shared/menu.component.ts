import { Component } from '@angular/core';

@Component({
  selector: 'grid-menu',
  template: `
    <a routerLink="/table1">table 1</a> | <a routerLink="/table2">table 2</a> |
    <a routerLink="/table3">table 3</a> | <a routerLink="/table4">table 4</a> |
    <a routerLink="/table5">table 5</a>
  `,
})
export class MenuComponent {}
