import { Component } from '@angular/core';
import { OnDestroy } from '@angular/core';
import { UntypedFormControl } from '@angular/forms';
import { Validators } from '@angular/forms';
import { ControlContainer } from '@angular/forms';
import { UntypedFormGroup } from '@angular/forms';

@Component({
  selector: 'app-sub-2',
  template: `
    <div class="fields">
      <label>Field 21</label>
      <input appInput [formControl]="field21" />
      <app-field-errors [control]="field21"></app-field-errors>

      <label>Field 22</label>
      <input appInput #last [formControl]="field22" />
      <app-field-errors [control]="field22"></app-field-errors>
    </div>
  `,
})
export class Sub2Component implements OnDestroy {
  readonly field21 = new UntypedFormControl('', Validators.required);
  readonly field22 = new UntypedFormControl('', Validators.required);
  private readonly formGroup: UntypedFormGroup;

  constructor(private controlContainer: ControlContainer) {
    this.formGroup = controlContainer.control as UntypedFormGroup;
    this.formGroup.registerControl('field21', this.field21);
    this.formGroup.registerControl('field22', this.field22);
  }

  ngOnDestroy(): void {
    this.formGroup.removeControl('field21');
    this.formGroup.removeControl('field22');
  }
}
