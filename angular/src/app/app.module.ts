import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {LOCALE_ID, NgModule} from '@angular/core';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';

import {AssociationComponent} from './association/association.component';
import {registerLocaleData} from '@angular/common';
import localeFr from '@angular/common/locales/fr';
import {AppComponent} from './app.component';
import {RouterModule, Routes} from '@angular/router';
import {AssociationWaldecComponent} from './waldec_association/associationWaldec.component';
import {HomeComponent} from './core/home/home.component';
import {ErreurInterceptor} from './http-interceptor';
import {ConfirmationService, MessageService} from 'primeng/api';
import {AdministrationComponent} from './administration/administration.component';
import {AuthGuard} from './core/guard';
import {JwtHelperService, JwtModule} from '@auth0/angular-jwt';
import {AuthentificationService} from './core/authentication/authentification.service';
import {StatistiqueComponent} from './statistique/statistique.component';
import {AdresseService} from './services/adresse.service';
import {NouvelleService} from './services/nouvelle.service';
import {AssociationService} from './services/association.service';
import {WaldecAssociationModule} from './waldec_association/associationWaldec.module';
import {AssociationModule} from './association/association.module';
import {AdministrationModule} from './administration/administration.module';
import {StatistiqueModule} from './statistique/statistique.module';
import {TabMenuModule} from 'primeng/tabmenu';
import {CoreModule} from './core/core.module';

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
        AppComponent
    ],
    imports: [
        RouterModule.forRoot(appRoutes, {enableTracing: false, relativeLinkResolution: 'legacy'}),
        BrowserModule,
        BrowserAnimationsModule,
        WaldecAssociationModule,
        AdministrationModule,
        AssociationModule,
        StatistiqueModule,
        HttpClientModule,
        TabMenuModule,
        CoreModule,
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
