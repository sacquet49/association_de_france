import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {CoreModule} from '../core/core.module';
import {TableModule} from 'primeng/table';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {DropdownModule} from 'primeng/dropdown';
import {AutoCompleteModule} from 'primeng/autocomplete';
import {ButtonModule} from 'primeng/button';
import {FieldsetModule} from 'primeng/fieldset';
import {PanelModule} from 'primeng/panel';
import {TabViewModule} from 'primeng/tabview';
import {TabMenuModule} from 'primeng/tabmenu';
import {AdministrationComponent} from './administration.component';
import {MessageModule} from 'primeng/message';

@NgModule({
    declarations: [AdministrationComponent],
    imports: [
        CoreModule,
        TableModule,
        FormsModule,
        ReactiveFormsModule,
        MessageModule,
        DropdownModule,
        AutoCompleteModule,
        ButtonModule,
        FieldsetModule,
        PanelModule,
        TabViewModule,
        TabMenuModule,
        CommonModule
    ]
})
export class AdministrationModule {
}
