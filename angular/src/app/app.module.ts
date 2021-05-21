import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {LOCALE_ID, NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {InputTextModule} from 'primeng/inputtext';
import {ButtonModule} from 'primeng/button';
import {TableModule} from 'primeng/table';
import {ToastModule} from 'primeng/toast';
import {DialogModule} from 'primeng/dialog';
import {MessagesModule} from 'primeng/messages';
import {MessageModule} from 'primeng/message';

import {AssociationComponent} from './association/association.component';
import {AutoCompleteModule} from 'primeng/autocomplete';
import {ConfirmDialogModule} from 'primeng/confirmdialog';
import {ContextMenuModule} from 'primeng/contextmenu';
import {DropdownModule} from 'primeng/dropdown';
import {FieldsetModule} from 'primeng/fieldset';
import {PasswordModule} from 'primeng/password';
import {TabMenuModule} from 'primeng/tabmenu';
import {TabViewModule} from 'primeng/tabview';
import {CalendarModule} from 'primeng/calendar';
import {registerLocaleData} from '@angular/common';
import localeFr from '@angular/common/locales/fr';
import {AppComponent} from './app.component';
import {RouterModule, Routes} from '@angular/router';
import {AssociationWaldecComponent} from './waldec_association/associationWaldec.component';
import {MenuModule} from 'primeng/menu';
import {MenubarModule} from 'primeng/menubar';
import {HomeComponent} from './home/home.component';
import {ErreurInterceptor} from './http-interceptor';
import {NotificationComponent} from './notification/notification.component';
import {ConfirmationService, MessageService} from 'primeng/api';
import {PanelModule} from 'primeng/panel';
import {AuthenticationComponent} from './authentication/authentication.component';
import {AdministrationComponent} from './administration/administration.component';
import {AuthGuard} from './guard';
import {JwtHelperService, JwtModule} from '@auth0/angular-jwt';
import {AuthenticationService} from './authentication/authentication.service';
import {StatistiqueComponent} from './statistique/statistique.component';
import {ChartModule} from 'primeng/chart';
import {CarteComponent} from './services/carte.component';
import {AdresseSearchComponent} from './services/adresse-search.component';
import {AdresseService} from './services/adresse.service';
import {NouvelleService} from './services/nouvelle.service';
import {AssociationService} from './services/association.service';

// the second parameter 'fr' is optional
registerLocaleData(localeFr, 'fr');

const appRoutes: Routes = [
    {path: '', redirectTo: 'home', pathMatch: 'full'},
    {path: 'home', component: HomeComponent},
    {path: 'association', component: AssociationComponent},
    {path: 'association/:id', component: AssociationComponent},
    {path: 'association_waldec', component: AssociationWaldecComponent},
    {path: 'association_waldec/:id', component: AssociationWaldecComponent},
    {path: 'administration', component: AdministrationComponent, canActivate: [AuthGuard]},
    {path: 'statistique', component: StatistiqueComponent, canActivate: [AuthGuard]}
];

export function tokenGetter() {
    return localStorage.getItem('id_token');
}

@NgModule({
    declarations: [
        AppComponent,
        AssociationComponent,
        AssociationWaldecComponent,
        HomeComponent,
        NotificationComponent,
        AuthenticationComponent,
        AdministrationComponent,
        StatistiqueComponent,
        CarteComponent,
        AdresseSearchComponent
    ],
    imports: [
        RouterModule.forRoot(appRoutes, { enableTracing: false, relativeLinkResolution: 'legacy' }),
        BrowserModule,
        BrowserAnimationsModule,
        FormsModule,
        TableModule,
        HttpClientModule,
        InputTextModule,
        DialogModule,
        MessageModule,
        MessagesModule,
        ButtonModule,
        ToastModule,
        CalendarModule,
        DropdownModule,
        MenuModule,
        MenubarModule,
        FieldsetModule,
        TabMenuModule,
        ContextMenuModule,
        ConfirmDialogModule,
        PanelModule,
        PasswordModule,
        ReactiveFormsModule,
        TabViewModule,
        AutoCompleteModule,
        JwtModule.forRoot({
            config: {
                tokenGetter: tokenGetter
            }
        }),
        ChartModule,
    ],
    providers: [
        ConfirmationService,
        JwtHelperService,
        AuthGuard,
        MessageService,
        AuthenticationService,
        AdresseService,
        NouvelleService,
        AssociationService,
        {provide: LOCALE_ID, useValue: 'fr'},
        {provide: HTTP_INTERCEPTORS, useClass: ErreurInterceptor, multi: true},
    ],
    bootstrap: [
        AppComponent
    ]
})
export class AppModule { }
