import { Component } from '@angular/core';

@Component({
  selector: 'grid-paging',
  template: `
    <h1>Paging</h1>
    <mat-paginator
      [pageSizeOptions]="[10, 25, 50, 100, 250, 500, 1000]"
    ></mat-paginator>
  `,
})
export class PagingComponent {}
