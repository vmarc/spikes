import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RouteFactory} from 'ng-micro-frontend';

const routes: Routes = [
  RouteFactory.createRoute('micro-frontend-1', 'http://localhost:4210'),
  RouteFactory.createRoute('micro-frontend-2', 'http://localhost:4220'),
  RouteFactory.createRoute('micro-frontend-3', 'http://localhost:4230'),
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
