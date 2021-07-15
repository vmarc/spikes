import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {NullComponent} from 'ng-micro-frontend';

import {Page1Component} from './page-1/page-1.component';
import {Page2Component} from './page-2/page-2.component';
import {Page3Component} from './page-3/page-3.component';

const mfeRoutes: Routes = [
  {path: 'page-1', component: Page1Component},
  {path: 'page-2', component: Page2Component},
  {path: 'page-3', component: Page3Component},
];

const routes: Routes = [
  {path: 'micro-frontend-1', children: mfeRoutes},
  {path: '**', component: NullComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
