import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Departement, Ville} from "../core/core.model";

@Injectable()
export class AdresseService {

    constructor(private http: HttpClient) {
    }

    getAllDepartement(): Observable<Departement[]> {
        return this.http.get<Departement[]>('https://geo.api.gouv.fr/departements?fields=nom,code,codeRegion');
    }

    getVilleByRegion(region: string): Observable<Ville[]> {
        return this.http.get<Ville[]>(`https://geo.api.gouv.fr/departements/${region}/communes?fields=nom,code,codesPostaux,codeDepartement,codeRegion,population`);
    }

    getGeocodingGouv(adresse: string): Observable<any> {
        return this.http.get(`https://api-adresse.data.gouv.fr/search/?q=${adresse}&autocomplete=0`);
    }

    getAdresseGouv(adresse: string): Observable<any> {
        return this.http.get(`https://api-adresse.data.gouv.fr/search/?q=${adresse}&limit=5`);
    }
}
