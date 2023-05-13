import { Injectable } from "@angular/core";

@Injectable()
export class DemoService {
  private static instanceCount = 0;

  readonly instanceId: number;

  constructor() {
    DemoService.instanceCount++;
    this.instanceId = DemoService.instanceCount;
    console.log('DemoService.constructor() instanceId=' + this.instanceId);
  }
}
