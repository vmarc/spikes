import { Component } from '@angular/core';

@Component({
  selector: 'grid-root',
  template: `
    <p>Grid</p>

    <ag-grid-angular
      style="width: 500px; height: 150px;"
      class="ag-theme-alpine"
      [rowData]="rowData"
      [columnDefs]="columnDefs"
    >
    </ag-grid-angular>

    <router-outlet></router-outlet>
  `,
  styles: [],
})
export class AppComponent {
  columnDefs = [{ field: 'make' }, { field: 'model' }, { field: 'price' }];

  rowData = [
    { make: 'Toyota', model: 'Celica', price: 35000 },
    { make: 'Ford', model: 'Mondeo', price: 32000 },
    { make: 'Porsche', model: 'Boxter', price: 72000 },
  ];
}
