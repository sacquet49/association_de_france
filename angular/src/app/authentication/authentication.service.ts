import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable()
export class AuthenticationService {

    constructor(private http: HttpClient, public jwtHelper: JwtHelperService) {}

    authenticate(user: any): Observable<any> {
        const httpParams: HttpParams = new HttpParams()
            .set('username', user.username)
            .set('password', user.password);
        const headers = new HttpHeaders({'Content-Type': 'application/x-www-form-urlencoded'});
        return this.http.post(`api/login_check`, httpParams.toString(),{headers: headers});
    }

    addUser(user : any): Observable<any> {
        const httpParams: HttpParams = new HttpParams()
            .set('username', user.username)
            .set('password', user.password);
        const headers = new HttpHeaders({'Content-Type': 'application/x-www-form-urlencoded'})
            .set('Authorization', `Bearer ${localStorage.getItem('id_token')}`);
        return this.http.post(`api/auth/admin/users`, httpParams.toString(), { headers });
    }

    getUtilisateurs(): Observable<any> {
        const headers = new HttpHeaders().set('Authorization', `Bearer ${localStorage.getItem('id_token')}`);
        return this.http.get(`api/auth/admin/users`, { headers });
    }

    removeUtilisateurs(id: any): Observable<any> {
        const headers = new HttpHeaders().set('Authorization', `Bearer ${localStorage.getItem('id_token')}`);
        return this.http.delete(`api/auth/admin/users/${id}`, { headers });
    }

    logout() {
        localStorage.removeItem('id_token');
    }

    loggedIn() {
        if(localStorage.getItem('id_token')){
            return !this.jwtHelper.isTokenExpired(localStorage.getItem('id_token'));
        } else {
            this.logout();
            return false;
        }
    }

    decode(){
        if(this.loggedIn() && localStorage.getItem('id_token')){
            return this.jwtHelper.decodeToken(localStorage.getItem('id_token'));
        }
        return '';
    }
}
