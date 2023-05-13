import { Component } from '@angular/core';
import { RouterLink } from "@angular/router";
import { RouterOutlet } from '@angular/router';
import { DemoService } from "./demo.service";

@Component({
  selector: 'app-root',
  template: `
    <a routerLink="feature">feature</a>
    <router-outlet name="top" />
    <router-outlet name="bottom" />
  `,
  standalone: true,
  providers: [
    DemoService
  ],
  imports: [RouterOutlet, RouterLink]
})
export class AppComponent {
  constructor(private demoService: DemoService) {
    console.log('AppComponent.constructor() instanceId=' + demoService.instanceId);
  }
}
