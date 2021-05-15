import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Table1Component } from './table1/table1.component';
import { Table2Component } from './table2/table2.component';
import { Table3Component } from './table3/table3.component';
import { Table4Component } from './table4/table4.component';
import { Table5Component } from './table5/table5.component';

const routes: Routes = [
  { path: 'table1', component: Table1Component },
  { path: 'table2', component: Table2Component },
  { path: 'table3', component: Table3Component },
  { path: 'table4', component: Table4Component },
  { path: 'table5', component: Table5Component },
  { path: '', component: Table1Component },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
