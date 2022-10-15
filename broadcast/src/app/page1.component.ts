import { Component } from '@angular/core';

@Component({
  selector: 'app-page1',
  template: `
    <h1>Page 1</h1>

    <a routerLink="/page2">page 2</a>
  `,
  styles: [],
})
export class Page1Component {}
