import { Routes } from '@angular/router';
import { BottomComponent } from "./bottom.component";
import { DemoService } from "../demo.service";
import { FeatureComponent } from "./feature.component";
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
        component: FeatureComponent,
      },
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

