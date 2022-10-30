import {HttpClientModule} from '@angular/common/http';
import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {RouterModule} from '@angular/router';

import {AppComponent} from './app.component';
import {AuthorizedComponent} from './authorized.component';
import {HomeComponent} from './home.component';
import {InfoComponent} from './info.component';
import {LoginFailedComponent} from './login-failed.component';
import {LoginSuccessComponent} from './login-success.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    InfoComponent,
    AuthorizedComponent,
    LoginFailedComponent,
    LoginSuccessComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot([
      {path: 'login-failure', component: LoginFailedComponent},
      {path: 'login-success', component: LoginSuccessComponent},
      {path: '', component: HomeComponent, pathMatch: 'full'}

    ], {onSameUrlNavigation: 'reload'})
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
