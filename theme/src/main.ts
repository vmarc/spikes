import {importProvidersFrom} from '@angular/core';
import {AppComponent} from './app/app.component';
import {bootstrapApplication, BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

bootstrapApplication(AppComponent, {
    providers: [importProvidersFrom(BrowserModule, BrowserAnimationsModule)]
})
  .catch(err => console.error(err));
