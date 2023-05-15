import { Component } from '@angular/core';
import { DemoService } from "../demo.service";

@Component({
  selector: 'app-bottom-sub',
  template: `
    <p>bottom sub instanceId = {{instanceId}}</p>
  `,
  standalone: true,
})
export class BottomSubComponent {
  readonly instanceId = this.demoService.instanceId;
  constructor(private demoService: DemoService) {
      console.log('BottomComponent.constructor() instanceId=' + demoService.instanceId);
  }
}
