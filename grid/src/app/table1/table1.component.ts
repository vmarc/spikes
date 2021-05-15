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
  readonly columnDefs = [{ field: 'make' }, { field: 'model' }, { field: 'price' }];

  readonly rowData = [
    { make: 'Toyota', model: 'Celica', price: 35000 },
    { make: 'Ford', model: 'Mondeo', price: 32000 },
    { make: 'Porsche', model: 'Boxter', price: 72000 },
  ];
}
