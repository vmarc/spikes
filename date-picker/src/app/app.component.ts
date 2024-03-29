import {Component} from '@angular/core';

@Component({
  selector: 'app-root',
  template: `
    <mat-form-field appearance="fill">
      <input matInput [matDatepicker]="picker">
      <mat-datepicker-toggle matSuffix [for]="picker"></mat-datepicker-toggle>
      <mat-datepicker #picker></mat-datepicker>
    </mat-form-field>
  `,
})
export class AppComponent {
}
