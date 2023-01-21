import { Component } from '@angular/core';
import { OnDestroy } from '@angular/core';
import { UntypedFormControl } from '@angular/forms';
import { Validators } from '@angular/forms';
import { ControlContainer } from '@angular/forms';
import { UntypedFormGroup } from '@angular/forms';

@Component({
  selector: 'app-sub-1',
  template: `
    <div class="fields">
      <label>Field 11</label>
      <input appInput [formControl]="field11" />
      <app-field-errors [control]="field11"></app-field-errors>

      <label>Field 12</label>
      <input appInput #last [formControl]="field12" />
      <app-field-errors [control]="field12"></app-field-errors>
    </div>
  `,
})
export class Sub1Component implements OnDestroy {
  readonly field11 = new UntypedFormControl('', Validators.required);
  readonly field12 = new UntypedFormControl('', Validators.required);
  private readonly formGroup: UntypedFormGroup;

  constructor(private controlContainer: ControlContainer) {
    this.formGroup = controlContainer.control as UntypedFormGroup;
    this.formGroup.registerControl('field11', this.field11);
    this.formGroup.registerControl('field12', this.field12);
  }

  ngOnDestroy(): void {
    this.formGroup.removeControl('field11');
    this.formGroup.removeControl('field12');
  }
}
