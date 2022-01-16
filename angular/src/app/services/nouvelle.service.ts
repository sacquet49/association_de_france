import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Nouvelle} from '../administration/administration.model';

@Injectable()
export class NouvelleService {

    constructor(private http: HttpClient) {
    }

    getNouvelles(): Observable<Nouvelle[]> {
        return this.http.get<Nouvelle[]>(`open/api/nouvelles`);
    }

    removeNouvelle(id: any): Observable<any> {
        const headers = new HttpHeaders().set('Authorization', `Bearer ${localStorage.getItem('id_token')}`);
        return this.http.delete(`private/api/nouvelle/${id}`, {headers});
    }

    addNouvelle(news: any): Observable<Nouvelle> {
        const headers = new HttpHeaders().set('Authorization', `Bearer ${localStorage.getItem('id_token')}`);
        return this.http.post<Nouvelle>(`private/api/nouvelle`, news, {headers});
    }
}
