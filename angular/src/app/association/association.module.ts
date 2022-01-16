import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {CoreModule} from '../core/core.module';
import {TableModule} from 'primeng/table';
import {FormsModule} from '@angular/forms';
import {DropdownModule} from 'primeng/dropdown';
import {AutoCompleteModule} from 'primeng/autocomplete';
import {ButtonModule} from 'primeng/button';
import {FieldsetModule} from 'primeng/fieldset';
import {AssociationComponent} from './association.component';

@NgModule({
    declarations: [AssociationComponent],
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
export class AssociationModule {
}
