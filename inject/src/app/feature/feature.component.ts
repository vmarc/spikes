import { Component } from '@angular/core';
import { DemoService } from "../demo.service";

@Component({
  selector: 'app-feature',
  template: `
    <p>feature component instanceId = {{instanceId}}</p>
  `,
  standalone: true,
  imports: [
  ]
})
export class FeatureComponent {
  readonly instanceId = this.demoService.instanceId;
  constructor(private demoService: DemoService) {
      console.log('FeatureComponent.constructor() instanceId=' + demoService.instanceId);
  }
}
