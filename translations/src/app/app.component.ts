import {Component} from '@angular/core';

@Component({
  selector: 'app-root',
  template: `
    <p i18n="@@translations.one">one {{firstField}} {{secondField}}</p>
    <p i18n="@@translations.two">two {{secondField}} {{firstField}}</p>
    <p i18n="@@translations.three">three</p>
    <p>{{four()}}</p>
    <p>{{five()}}</p>
    <p i18n="@@translations.six">

      six

    </p>
  `,
})
export class AppComponent {

  firstField = "first";
  secondField = "second";

  four(): string {
    return $localize`:@@translations.four:four`;
  }

  five(): string {
    return $localize`:@@translations.five:five ${this.firstField}:first: ${this.secondField}:second:`;
  }
}
