import {Component} from '@angular/core';
import {AppService} from './app.service';
import {TestValue} from './test-value';

@Component({
  selector: 'app-details',
  providers: [AppService],
  template: `
    <div>Details</div>
    <div>
      <label>ID</label> <span>{{testValue.id}}</span>
    </div>
    <div>
      <label>Name</label> <span>{{testValue.name}}</span>
    </div>
    <div>
      <button (click)="getTestValue()" type="submit">Get test value</button>
    </div>
  `
})
export class DetailsComponent {
  public testValue = new TestValue(1, 'een');
  private valuesUrl = 'http://localhost:8081/resource-server/api/values/';

  constructor(private appService: AppService) {
  }

  getTestValue() {
    this.appService.getResource(this.valuesUrl + this.testValue.id)
      .subscribe(
        data => this.testValue = data,
        error => this.testValue.name = 'Error');
  }
}
