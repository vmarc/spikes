import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NetworksComponent } from './networks/networks.component';
import { PagingComponent } from './paging/paging.component';
import { Table3Component } from './table3/table3.component';
import { Table4Component } from './table4/table4.component';
import { Table5Component } from './table5/table5.component';

const routes: Routes = [
  { path: 'networks', component: NetworksComponent },
  { path: 'paging', component: PagingComponent },
  { path: 'table3', component: Table3Component },
  { path: 'table4', component: Table4Component },
  { path: 'table5', component: Table5Component },
  { path: '', component: NetworksComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
