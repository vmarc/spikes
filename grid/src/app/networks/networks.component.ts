import { Component } from '@angular/core';
import { NetworksService } from './networks.service';

@Component({
  selector: 'grid-networks',
  template: `
    <h1>Networks</h1>
    <div *ngIf="response$ | async as response">
      <ag-grid-angular
        class="ag-theme-alpine"
        [rowData]="response.result?.networks"
        [columnDefs]="columnDefs"
        domLayout="autoHeight"
      >
      </ag-grid-angular>
    </div>
  `,
})
export class NetworksComponent {
  readonly columnDefs = [
    { field: 'name', headerName: $localize`:@@networks.header.name:Name` },
    {
      field: 'nodeCount',
      headerName: $localize`:@@networks.header.node-count:Nodes`,
    },
    {
      field: 'routeCount',
      headerName: $localize`:@@networks.header.route-count:Routes`,
    },
    {
      field: 'lastUpdated',
      headerName: $localize`:@@networks.header.last-updated:Updated`,
    },
  ];

  readonly response$ = this.networksService.networks();

  constructor(private networksService: NetworksService) {}
}
