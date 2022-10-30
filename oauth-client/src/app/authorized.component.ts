import {Component} from '@angular/core';
import {AppService} from './app.service';

@Component({
  selector: 'app-authorized',
  providers: [AppService],
  template: `
    <div>Logging in</div>
  `
})
export class AuthorizedComponent {
}
