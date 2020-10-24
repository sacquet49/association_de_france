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
        {label: '', icon: 'pi pi-home', routerLink: ['home']},
        {label: 'Associations avant 2009', icon: 'pi pi-list', routerLink: ['association']},
        {label: 'Associations après 2009', icon: 'pi pi-list', routerLink: ['association_waldec']},
    ];

    constructor(private authService: AuthenticationService) {}

    ngOnInit(): void {
        this.connectionChange();
    }

    connectionChange() {
        const tokenRole = this.authService.decode() ? this.authService.decode() : false;
        this.tabMenuItems = this.tabMenuItemsBase.slice();

        if(tokenRole && tokenRole.roles.includes('ROLE_SUPER_ADMIN')) {
            this.tabMenuItems.push({label: 'Administration', icon: 'pi pi-cog', routerLink: ['administration']});
        } else if(tokenRole && tokenRole.roles.includes('ROLE_USER_CLASSIC')) {
            this.tabMenuItems.push({label: 'Statistique', icon: 'pi pi-chart-bar', routerLink: ['statistique']});
        }
    }
}