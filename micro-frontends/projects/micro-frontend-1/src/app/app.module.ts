import {ApplicationRef, DoBootstrap, Injector, NgModule} from '@angular/core';
import {createCustomElement} from '@angular/elements';
import {BrowserModule} from '@angular/platform-browser';
import {MicroFrontendModule} from 'ng-micro-frontend';
import {environment} from '../environments/environment';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {Page1Component} from './page-1/page-1.component';
import {Page2Component} from './page-2/page-2.component';
import {Page3Component} from './page-3/page-3.component';

@NgModule({
  declarations: [
    AppComponent,
    Page1Component,
    Page2Component,
    Page3Component
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MicroFrontendModule
  ],
  providers: [],
})
export class AppModule implements DoBootstrap {
  public constructor(
    private readonly injector: Injector
  ) {
  }

  public ngDoBootstrap(appRef: ApplicationRef): void {
    if (environment.production) {
      const customElement = createCustomElement(AppComponent, {injector: this.injector});
      customElements.define('custom-micro-frontend-1', customElement);
    } else {
      appRef.bootstrap(AppComponent);
    }
  }
}
