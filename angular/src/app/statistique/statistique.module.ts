import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {ChartModule} from 'primeng/chart';
import {TableModule} from 'primeng/table';
import {DropdownModule} from 'primeng/dropdown';
import {AutoCompleteModule} from 'primeng/autocomplete';
import {ButtonModule} from 'primeng/button';
import {FieldsetModule} from 'primeng/fieldset';
import {ToastModule} from 'primeng/toast';
import {ConfirmDialogModule} from 'primeng/confirmdialog';
import {PanelModule} from 'primeng/panel';
import {TabViewModule} from 'primeng/tabview';
import {TabMenuModule} from 'primeng/tabmenu';
import {StatistiqueComponent} from './statistique.component';


@NgModule({
    declarations: [StatistiqueComponent],
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
        ConfirmDialogModule,
        PanelModule,
        TabViewModule,
        TabMenuModule
    ]
})
export class StatistiqueModule {
}
