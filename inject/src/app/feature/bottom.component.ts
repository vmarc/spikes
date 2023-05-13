import { Component } from '@angular/core';
import { DemoService } from "../demo.service";

@Component({
  selector: 'app-bottom',
  template: `
    <p>bottom instanceId = {{instanceId}}</p>
  `,
  standalone: true,
})
export class BottomComponent {
  readonly instanceId = this.demoService.instanceId;
  constructor(private demoService: DemoService) {
      console.log('BottomComponent.constructor() instanceId=' + demoService.instanceId);
  }
}
