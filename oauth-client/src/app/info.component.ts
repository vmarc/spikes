import {Component} from '@angular/core';
import {AppService} from './app.service';

@Component({
  selector: 'app-details',
  providers: [AppService],
  template: `
    <div>Info</div>
    <div>{{info}}</div>
  `
})
export class InfoComponent {
  info = '';

  constructor(private appService: AppService) {
  }

  getTestValue() {
    this.appService.getInfo().subscribe((data) => this.info = data);
  }
}
