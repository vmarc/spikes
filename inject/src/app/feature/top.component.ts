import { Component } from '@angular/core';
import { DemoService } from "../demo.service";

@Component({
  selector: 'app-top',
  template: `
    <p>top instanceId = {{instanceId}}</p>
  `,
  standalone: true,
})
export class TopComponent {
  readonly instanceId = this.demoService.instanceId;
  constructor(private demoService: DemoService) {
    console.log('TopComponent.constructor() instanceId=' + demoService.instanceId);
  }
}
