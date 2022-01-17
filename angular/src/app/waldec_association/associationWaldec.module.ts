import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {AssociationWaldecComponent} from './associationWaldec.component';
import {TableModule} from 'primeng/table';
import {DropdownModule} from 'primeng/dropdown';
import {AutoCompleteModule} from 'primeng/autocomplete';
import {FieldsetModule} from 'primeng/fieldset';
import {ButtonModule} from 'primeng/button';
import {FormsModule} from '@angular/forms';
import {CoreModule} from '../core/core.module';

@NgModule({
    declarations: [AssociationWaldecComponent],
    imports: [
        CoreModule,
        TableModule,
        FormsModule,
        DropdownModule,
        AutoCompleteModule,
        ButtonModule,
        FieldsetModule,
        CommonModule
    ]
})
export class WaldecAssociationModule {
}
