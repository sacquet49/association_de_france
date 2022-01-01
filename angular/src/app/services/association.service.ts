import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable()
export class AssociationService {

    constructor(private http: HttpClient) {
    }

    getCategories(): Observable<any> {
        return this.http.get(`open/api/categories`);
    }

    getAssociation(id: string): Observable<any> {
        return this.http.get(`open/api/association/${id}`);
    }

    getAssociationWaldec(id: string): Observable<any> {
        return this.http.get(`open/api/association_waldec/${id}`);
    }

    getStatWaldecAssociation(): Observable<any> {
        const headers = new HttpHeaders().set('Authorization', `Bearer ${localStorage.getItem('id_token')}`);
        return this.http.get(`private/api/association_waldecs/stat`, {headers});
    }
}
