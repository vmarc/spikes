import { Component } from '@angular/core';
import { DemoService } from "../demo.service";

@Component({
  selector: 'app-top-sub',
  template: `
    <p>top sub instanceId = {{instanceId}}</p>
  `,
  standalone: true,
})
export class TopSubComponent {
  readonly instanceId = this.demoService.instanceId;
  constructor(private demoService: DemoService) {
    console.log('TopSubComponent.constructor() instanceId=' + demoService.instanceId);
  }
}
