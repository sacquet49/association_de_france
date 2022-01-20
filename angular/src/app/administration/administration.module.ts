import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {CoreModule} from '../core/core.module';
import {TableModule} from 'primeng/table';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {DropdownModule} from 'primeng/dropdown';
import {ButtonModule} from 'primeng/button';
import {FieldsetModule} from 'primeng/fieldset';
import {PanelModule} from 'primeng/panel';
import {TabViewModule} from 'primeng/tabview';
import {AdministrationComponent} from './administration.component';
import {MessageModule} from 'primeng/message';
import {InputTextModule} from 'primeng/inputtext';
import {PasswordModule} from 'primeng/password';

@NgModule({
    declarations: [AdministrationComponent],
    imports: [
        CoreModule,
        TableModule,
        FormsModule,
        ReactiveFormsModule,
        MessageModule,
        DropdownModule,
        ButtonModule,
        FieldsetModule,
        PanelModule,
        TabViewModule,
        InputTextModule,
        PasswordModule,
        CommonModule
    ]
})
export class AdministrationModule {
}
