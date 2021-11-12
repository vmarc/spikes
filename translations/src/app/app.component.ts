import {Component, Inject, LOCALE_ID} from '@angular/core';

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
    <p>
      now: {{now | date}}
    </p>
    <p>
      now(en): {{now | date:'short':undefined:'en'}}
    </p>
    <p>
      now(fr): {{now | date:'short':undefined:'fr'}}
    </p>
    <p>
      now(nl): {{now | date:'short':undefined:'nl'}}
    </p>
    <p>
      now(de): {{now | date:'short':undefined:'de'}}
    </p>
    <p>
      value: {{value | number}}
    </p>
    <p>
      value (fr): {{value | number:'0.0':'fr'}}
    </p>
    <p>
      value (nl): {{value | number:'0.0':'nl'}}
    </p>
    <p>
      value (de): {{value | number:'0.0':'de'}}
    </p>
    <p>
      locale: {{locale}}
    </p>

    <p>
      languages: <span *ngFor="let language of languages()">{{language}} </span>
    </p>
  `,
})
export class AppComponent {

  firstField = "first";
  secondField = "second";
  now = new Date();
  value = 1234567;

  constructor(
    @Inject(LOCALE_ID) public locale: string
  ) {
  }

  four(): string {
    return $localize`:@@translations.four:four`;
  }

  five(): string {
    return $localize`:@@translations.five:five ${this.firstField}:first: ${this.secondField}:second:`;
  }

  languages(): ReadonlyArray<string> {
    return navigator.languages;
  }

}
