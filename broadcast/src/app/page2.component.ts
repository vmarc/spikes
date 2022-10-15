import { Component } from '@angular/core';
import { AppService } from './app.service';

@Component({
  selector: 'app-page2',
  template: `<h1>Page 2</h1>`,
})
export class Page2Component {
  constructor(private appService: AppService) {}
}
