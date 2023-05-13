import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';


import { importProvidersFrom } from '@angular/core';
import { provideRouter } from "@angular/router";
import { AppComponent } from './app/app.component';
import { appRoutes } from './app/app-routes';
import { BrowserModule, bootstrapApplication } from '@angular/platform-browser';


bootstrapApplication(AppComponent, {
    providers: [
      provideRouter(appRoutes),
      importProvidersFrom(BrowserModule)
    ]
})
  .catch(err => console.error(err));
