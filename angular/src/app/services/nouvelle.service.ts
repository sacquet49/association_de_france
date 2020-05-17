import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable,} from 'rxjs';

@Injectable()
export class NouvelleService {

    constructor(private http: HttpClient) {
    }

    getNouvelles(): Observable<any> {
        return this.http.get(`api/nouvelles`);
    }

    removeNouvelle(id: any): Observable<any> {
        const headers = new HttpHeaders().set('Authorization', `Bearer ${localStorage.getItem('id_token')}`);
        return this.http.delete(`api/auth/admin/nouvelle/${id}`, {headers});
    }

    addNouvelle(news: any): Observable<any> {
        const httpParams: HttpParams = new HttpParams()
            .set('titre', news.titre)
            .set('description', news.description);
        const headers = new HttpHeaders({'Content-Type': 'application/x-www-form-urlencoded'})
            .set('Authorization', `Bearer ${localStorage.getItem('id_token')}`);
        return this.http.post(`api/auth/admin/nouvelle`, httpParams.toString(), {headers});
    }
}