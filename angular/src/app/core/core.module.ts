import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {CarteComponent} from './carte/carte.component';
import {AdresseSearchComponent} from './adresse-search/adresse-search.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AutoCompleteModule} from 'primeng/autocomplete';
import {ChartModule} from 'primeng/chart';
import {TableModule} from 'primeng/table';
import {DropdownModule} from 'primeng/dropdown';
import {ButtonModule} from 'primeng/button';
import {FieldsetModule} from 'primeng/fieldset';
import {PanelModule} from 'primeng/panel';
import {TabViewModule} from 'primeng/tabview';
import {TabMenuModule} from 'primeng/tabmenu';
import {ToastModule} from 'primeng/toast';
import {ConfirmDialogModule} from 'primeng/confirmdialog';
import {NotificationComponent} from './notification/notification.component';
import {HomeComponent} from './home/home.component';
import {AuthentificationComponent} from './authentication/authentification.component';
import {DialogModule} from 'primeng/dialog';
import {PasswordModule} from 'primeng/password';

@NgModule({
    declarations: [
        CarteComponent,
        AdresseSearchComponent,
        NotificationComponent,
        HomeComponent,
        AuthentificationComponent
    ],
    imports: [
        CommonModule,
        FormsModule,
        ChartModule,
        TableModule,
        FormsModule,
        DropdownModule,
        AutoCompleteModule,
        ButtonModule,
        FieldsetModule,
        ToastModule,
        DialogModule,
        ReactiveFormsModule,
        PasswordModule,
        ConfirmDialogModule,
        PanelModule,
        TabViewModule,
        TabMenuModule,
        AutoCompleteModule
    ],
    exports: [
        CarteComponent,
        NotificationComponent,
        AuthentificationComponent
    ]
})
export class CoreModule {
}
