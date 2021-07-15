import {ApplicationRef, DoBootstrap, Injector, NgModule} from '@angular/core';
import {createCustomElement} from '@angular/elements';
import {BrowserModule} from '@angular/platform-browser';
import {MicroFrontendModule} from 'ng-micro-frontend';
import {environment} from '../environments/environment';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';

@NgModule({
  declarations: [
    AppComponent
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
      customElements.define('custom-micro-frontend-2', customElement);
    } else {
      appRef.bootstrap(AppComponent);
    }
  }
}
