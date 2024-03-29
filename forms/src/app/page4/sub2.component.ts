import { Component } from '@angular/core';
import { OnDestroy } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Validators } from '@angular/forms';
import { ControlContainer } from '@angular/forms';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-sub-2',
  template: `
    <div class="fields">
      <label>Field 21</label>
      <input appInput [formControl]="field21" />
      <app-field-errors [control]="field21"/>

      <label>Field 22</label>
      <input appInput #last [formControl]="field22" />
      <app-field-errors [control]="field22"/>
    </div>
  `,
})
export class Sub2Component implements OnDestroy {
  readonly field21 = new FormControl<string>('', Validators.required);
  readonly field22 = new FormControl<string>('', Validators.required);
  private readonly formGroup: FormGroup;

  constructor(private controlContainer: ControlContainer) {
    this.formGroup = controlContainer.control as FormGroup;
    this.formGroup.registerControl('field21', this.field21);
    this.formGroup.registerControl('field22', this.field22);
  }

  ngOnDestroy(): void {
    this.formGroup.removeControl('field21');
    this.formGroup.removeControl('field22');
  }
}
