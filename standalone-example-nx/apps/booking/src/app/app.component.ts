import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { map, shareReplay } from 'rxjs';
import { Component, Inject } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { SHELL } from '@nx-example/shared/ui-shell';
import { MATERIAL } from './material';

@Component({
  standalone: true,
  selector: 'app-root',
  imports: [
    ...MATERIAL,
    ...SHELL,
    RouterOutlet,
  ],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  isHandset$ = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  constructor(
    @Inject(BreakpointObserver) private breakpointObserver: BreakpointObserver) {
  }

}
