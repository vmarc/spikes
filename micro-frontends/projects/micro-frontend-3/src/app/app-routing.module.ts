import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {NullComponent} from 'ng-micro-frontend';

const mfeRoutes: Routes = [];

const routes: Routes = [
  {children: mfeRoutes, path: 'micro-frontend-3'},
  {component: NullComponent, path: '**'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
