import { Routes } from '@angular/router';
import { BottomComponent } from "./bottom.component";
import { DemoService } from "../demo.service";
import { TopComponent } from "./top.component";

export const featureRoutes: Routes = [
  {
    path: '',
    providers: [
      DemoService
    ],
    children: [
      {
        path: '',
        component: TopComponent,
        outlet: 'top'
      },
      {
        path: '',
        component: BottomComponent,
        outlet: 'bottom',
      },
    ],
  },
];

