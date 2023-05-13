import { Routes } from '@angular/router';
import { AppComponent } from "./app.component";

export const appRoutes: Routes = [
  {
    path: '',
    component: AppComponent
  },
  {
    path: 'feature',
    loadChildren: () =>
      import('./feature/feature.routes').then((m) => m.featureRoutes),
  },
];
