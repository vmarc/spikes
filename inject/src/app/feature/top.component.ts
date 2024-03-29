import { Component } from '@angular/core';
import { DemoService } from "../demo.service";
import { TopSubComponent } from "./top-sub.component";

@Component({
  selector: 'app-top',
  template: `
    <p>top instanceId = {{instanceId}}</p>
    <app-top-sub/>
  `,
  standalone: true,
  imports: [
    TopSubComponent
  ]
})
export class TopComponent {
  readonly instanceId = this.demoService.instanceId;
  constructor(private demoService: DemoService) {
    console.log('TopComponent.constructor() instanceId=' + demoService.instanceId);
  }
}
