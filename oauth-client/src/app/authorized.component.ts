import {Component} from '@angular/core';
import {AppService} from './app.service';
import {TestValue} from './test-value';

@Component({
  selector: 'app-authorized',
  providers: [AppService],
  template: `
    <div>Logging in</div>
  `
})
export class AuthorizedComponent {
}
