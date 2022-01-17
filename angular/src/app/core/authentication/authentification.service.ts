import {Injectable} from '@angular/core';
import {JwtHelperService} from '@auth0/angular-jwt';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from '../../administration/administration.model';

@Injectable()
export class AuthentificationService {

    constructor(private http: HttpClient, public jwtHelper: JwtHelperService) {
    }

    authenticate(user: any): Observable<any> {
        return this.http.post(`open/api/authenticate`, user);
    }

    addUser(user: any): Observable<User> {
        const headers = new HttpHeaders().set('Authorization', `Bearer ${localStorage.getItem('id_token')}`);
        return this.http.post<User>(`private/api/user`, user, {headers});
    }

    getUtilisateurs(): Observable<User[]> {
        const headers = new HttpHeaders().set('Authorization', `Bearer ${localStorage.getItem('id_token')}`);
        return this.http.get<User[]>(`private/api/users`, {headers});
    }

    removeUtilisateurs(id: any): Observable<any> {
        const headers = new HttpHeaders().set('Authorization', `Bearer ${localStorage.getItem('id_token')}`);
        return this.http.delete(`private/api/user/${id}`, {headers});
    }

    logout() {
        localStorage.removeItem('id_token');
    }

    loggedIn() {
        if (localStorage.getItem('id_token')) {
            return !this.jwtHelper.isTokenExpired(localStorage.getItem('id_token'));
        } else {
            this.logout();
            return false;
        }
    }

    decode() {
        if (this.loggedIn() && localStorage.getItem('id_token')) {
            return this.jwtHelper.decodeToken(localStorage.getItem('id_token'));
        }
        return '';
    }
}
