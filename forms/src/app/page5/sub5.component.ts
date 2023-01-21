import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { ControlContainer } from '@angular/forms';
import { UntypedFormGroup } from '@angular/forms';
import { UntypedFormControl } from '@angular/forms';

@Component({
  selector: 'app-sub-5',
  template: `
    <div class="fields">
      <label>Field 3</label>
      <input appInput [formControl]="field3" />
      <app-field-errors [control]="field3"></app-field-errors>

      <label>Field 4</label>
      <input appInput #last [formControl]="field4" />
      <app-field-errors [control]="field4"></app-field-errors>
    </div>
  `,
})
export class Sub5Component implements OnInit {
  field3: UntypedFormControl;
  field4: UntypedFormControl;

  constructor(private controlContainer: ControlContainer) {}

  ngOnInit(): void {
    const formGroup = this.controlContainer.control as UntypedFormGroup;
    this.field3 = formGroup.get('field3') as UntypedFormControl;
    this.field4 = formGroup.get('field4') as UntypedFormControl;
  }
}
