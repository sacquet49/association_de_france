import {AdresseService} from "./services/adresse.service";
import {Component, OnInit} from "@angular/core";
import {MenuItem} from "primeng/api";
import {AuthenticationService} from "./authentication/authentication.service";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    providers: [AdresseService, AuthenticationService]
})
export class AppComponent implements OnInit {

    tabMenuItems: MenuItem[];
    tabMenuItemsBase = [
        {label: '', icon: 'ui-icon-home', routerLink: ['home']},
        {label: 'Associations avant 2009', icon: 'ui-icon-library-books', routerLink: ['association']},
        {label: 'Associations après 2009', icon: 'ui-icon-library-books', routerLink: ['association_waldec']},
    ];

    constructor(private authService: AuthenticationService) {}

    ngOnInit(): void {
        this.connectionChange();
    }

    connectionChange() {
        const tokenRole = this.authService.decode() ? this.authService.decode() : false;
        this.tabMenuItems = this.tabMenuItemsBase.slice();

        if(tokenRole && tokenRole.roles.includes('ROLE_SUPER_ADMIN')) {
            this.tabMenuItems.push({label: 'Administration', icon: 'ui-icon-settings', routerLink: ['administration']});
        } else if(tokenRole && tokenRole.roles.includes('ROLE_USER_CLASSIC')) {
            this.tabMenuItems.push({label: 'Statistique', icon: 'ui-icon-pie-chart-outlined', routerLink: ['statistique']});
        }
    }
}