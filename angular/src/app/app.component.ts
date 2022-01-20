import {AdresseService} from './services/adresse.service';
import {AfterViewInit, Component, OnInit} from '@angular/core';
import {MenuItem} from 'primeng/api';
import {AuthentificationService} from './core/authentication/authentification.service';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    providers: [AdresseService, AuthentificationService]
})
export class AppComponent implements OnInit {

    private _tabMenuItems: MenuItem[];
    private _tabMenuItemsBase = [
        {label: '', icon: 'pi pi-home', routerLink: 'home'},
        {label: 'Associations avant 2009', icon: 'pi pi-list', routerLink: 'association'},
        {label: 'Associations après 2009', icon: 'pi pi-list', routerLink: 'association_waldec'},
    ];

    get tabMenuItems(): MenuItem[] {
        return this._tabMenuItems;
    }

    constructor(private authService: AuthentificationService) {
    }

    public ngOnInit(): void {
        this.connectionChange();
    }

    public connectionChange(): void {
        const tokenRole = this.authService.decode() ? this.authService.decode() : false;
        this._tabMenuItems = this._tabMenuItemsBase.slice();
        if (tokenRole.Roles) {
            const roles: string[] = tokenRole.Roles.map(r => r.authority);
            if (roles.includes('ROLE_SUPER_ADMIN')) {
                this._tabMenuItems.push({label: 'Administration', icon: 'pi pi-cog', routerLink: 'administration'});
            } else if (roles.includes('ROLE_USER_CLASSIC')) {
                this._tabMenuItems.push({label: 'Statistique', icon: 'pi pi-chart-bar', routerLink: 'statistique'});
            }
        }
    }
}
