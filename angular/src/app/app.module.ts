import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {LOCALE_ID, NgModule} from '@angular/core';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {registerLocaleData} from '@angular/common';
import localeFr from '@angular/common/locales/fr';
import {AppComponent} from './app.component';
import {ErreurInterceptor} from './core/http-interceptor';
import {ConfirmationService, MessageService} from 'primeng/api';
import {AuthGuard} from './core/guard';
import {JwtHelperService, JwtModule} from '@auth0/angular-jwt';
import {AuthentificationService} from './core/authentication/authentification.service';
import {AdresseService} from './services/adresse.service';
import {NouvelleService} from './services/nouvelle.service';
import {AssociationService} from './services/association.service';
import {WaldecAssociationModule} from './waldec_association/associationWaldec.module';
import {AssociationModule} from './association/association.module';
import {AdministrationModule} from './administration/administration.module';
import {StatistiqueModule} from './statistique/statistique.module';
import {TabMenuModule} from 'primeng/tabmenu';
import {CoreModule} from './core/core.module';
import {AppRoutingModule} from './app-routing.module';

// the second parameter 'fr' is optional
registerLocaleData(localeFr, 'fr');

export function tokenGetter() {
    return localStorage.getItem('id_token');
}

@NgModule({
    declarations: [
        AppComponent
    ],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        WaldecAssociationModule,
        AdministrationModule,
        AssociationModule,
        StatistiqueModule,
        HttpClientModule,
        TabMenuModule,
        CoreModule,
        AppRoutingModule,
        JwtModule.forRoot({
            config: {
                tokenGetter: tokenGetter
            }
        })
    ],
    providers: [
        ConfirmationService,
        JwtHelperService,
        AuthGuard,
        MessageService,
        AuthentificationService,
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
export class AppModule {
}
