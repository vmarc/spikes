import {Component} from '@angular/core';

@Component({
  selector: 'app-root',
  template: `
    <div style="background-color: aliceblue; padding: 10px;">
      <h1>Microfrontend shell</h1>
      <p>Served via port 4200</p>
      <nav>
        <ul>
          <li><a routerLink="/micro-frontend-1">Micro frontend 1</a></li>
          <li><a routerLink="/micro-frontend-2">Micro frontend 2</a></li>
          <li><a routerLink="/micro-frontend-3">Micro frontend 3</a></li>
        </ul>
      </nav>
      <router-outlet></router-outlet>
    </div>
  `
})
export class AppComponent {
}
