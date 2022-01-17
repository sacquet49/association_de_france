import {Injectable} from '@angular/core';
import {CanActivate, Router} from '@angular/router';
import {AuthentificationService} from '../authentication/authentification.service';

@Injectable()
export class AuthGuard implements CanActivate {

    constructor(private authentication: AuthentificationService, private router: Router) {
    }

    canActivate() {
        if (this.authentication.loggedIn()) {
            return true;
        } else {
            this.router.navigate(['']);
            return false;
        }
    }
}
