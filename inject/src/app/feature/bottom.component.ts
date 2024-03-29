import { Component } from '@angular/core';
import { DemoService } from "../demo.service";
import { BottomSubComponent } from "./bottom-sub.component";

@Component({
  selector: 'app-bottom',
  template: `
    <p>bottom instanceId = {{instanceId}}</p>
    <app-bottom-sub />
  `,
  standalone: true,
  imports: [
    BottomSubComponent
  ]
})
export class BottomComponent {
  readonly instanceId = this.demoService.instanceId;
  constructor(private demoService: DemoService) {
      console.log('BottomComponent.constructor() instanceId=' + demoService.instanceId);
  }
}
