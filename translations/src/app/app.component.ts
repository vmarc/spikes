import {Component} from '@angular/core';

@Component({
  selector: 'app-root',
  template: `
    <p i18n="@@translations.one">one {{name}}</p>
    <p i18n="@@translations.two">two {{name}}</p>
    <p i18n="@@translations.three">three {{name}}:name:</p>
    <p>{{four()}}</p>
    <p>{{five()}}</p>
    <p i18n="@@translations.six">

      six

    </p>
  `,
})
export class AppComponent {

  name = "marc";

  four(): string {
    return $localize`:@@translations.four:four`;
  }

  five(): string {
    return $localize`:@@translations.five:five ${this.name}:name:`;
  }
}
