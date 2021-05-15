import { Component } from '@angular/core';

@Component({
  selector: 'grid-table1',
  template: `
    <ag-grid-angular
      style="width: 500px; height: 150px;"
      class="ag-theme-alpine"
      [rowData]="rowData"
      [columnDefs]="columnDefs"
    >
    </ag-grid-angular>
  `,
})
export class Table1Component {
  readonly columnDefs = [
    { field: 'make', headerName: $localize`:@@table1.header.make:Make` },
    { field: 'model', headerName: $localize`:@@table1.header.model:Model` },
    { field: 'price', headerName: $localize`:@@table1.header.price:Price` },
  ];

  readonly rowData = [
    { make: 'Toyota', model: 'Celica', price: 35000 },
    { make: 'Ford', model: 'Mondeo', price: 32000 },
    { make: 'Porsche', model: 'Boxter', price: 72000 },
  ];
}
