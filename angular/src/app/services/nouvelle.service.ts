import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable()
export class NouvelleService {

    constructor(private http: HttpClient) {
    }

    getNouvelles(): Observable<any> {
        return this.http.get(`open/api/nouvelles`);
    }

    removeNouvelle(id: any): Observable<any> {
        const headers = new HttpHeaders().set('Authorization', `Bearer ${localStorage.getItem('id_token')}`);
        return this.http.delete(`private/api/nouvelle/${id}`, {headers});
    }

    addNouvelle(news: any): Observable<any> {
        const headers = new HttpHeaders().set('Authorization', `Bearer ${localStorage.getItem('id_token')}`);
        return this.http.post(`private/api/nouvelle`, news, {headers});
    }
}
