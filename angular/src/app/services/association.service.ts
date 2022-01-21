import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Association} from '../association/association.model';
import {AssociationWaldec} from '../waldec_association/associationWaldec.model';
import {WaldecAssociationStat} from '../statistique/statistique.model';

@Injectable()
export class AssociationService {

    constructor(private http: HttpClient) {
    }

    getAssociation(id: string): Observable<Association> {
        return this.http.get<Association>(`open/api/association/${id}`);
    }

    getAssociationWaldec(id: string): Observable<AssociationWaldec> {
        return this.http.get<AssociationWaldec>(`open/api/association_waldec/${id}`);
    }

    getStatWaldecAssociation(): Observable<WaldecAssociationStat[]> {
        const headers = new HttpHeaders().set('Authorization', `Bearer ${localStorage.getItem('id_token')}`);
        return this.http.get<WaldecAssociationStat[]>(`private/api/association_waldec/statistique`, {headers});
    }
}
