import {Component, OnInit, ViewChild} from '@angular/core';
import {Location} from '@angular/common';
import {AdresseService} from '../services/adresse.service';
import {SelectItem} from 'primeng/api';
import {ActivatedRoute} from '@angular/router';
import {ElasticSearchService} from '../services/elastic-search.service';
import {AssociationService} from '../services/association.service';
import {AssociationWaldec, query, queryWithNoFilter} from './associationWaldec.model';
import {Departement, Ville} from '../core/core.model';

@Component({
    selector: 'ass-association-waldec',
    templateUrl: './associationWaldec.component.html',
    providers: [AdresseService]
})
export class AssociationWaldecComponent implements OnInit {

    private _id;
    private _departements: Departement[] = [];
    private _ville: Ville[] = [];
    private _departementsDrop: SelectItem[];
    private _villesDrop: SelectItem[];
    private _associations: AssociationWaldec[];
    private _selectedAssociationTab: AssociationWaldec;
    private _options: any;
    @ViewChild('tableAssociation')
    private _dataTableComponent: any;
    private _searcheParams: any = {ville: undefined, departement: undefined};
    private _latitude: number;
    private _longitude: number;
    private _subjestAssociation: string[];

    get query(): any {
        return query;
    }

    get selectedAssociationTab(): AssociationWaldec {
        return this._selectedAssociationTab;
    }

    set selectedAssociationTab(association: AssociationWaldec) {
        this._selectedAssociationTab = association;
    }

    get departementsDrop(): SelectItem[] {
        return this._departementsDrop;
    }

    get villesDrop(): SelectItem[] {
        return this._villesDrop;
    }

    get searcheParams(): any {
        return this._searcheParams;
    }

    get subjestAssociation(): string[] {
        return this._subjestAssociation;
    }

    get associations(): AssociationWaldec[] {
        return this._associations;
    }

    get latitude(): number {
        return this._latitude;
    }

    get longitude(): number {
        return this._longitude;
    }

    constructor(private adresseService: AdresseService,
                private associationService: AssociationService,
                private location: Location,
                private elkSearchService: ElasticSearchService,
                private route: ActivatedRoute) {
        this.route.params.subscribe(params => this._id = params.id);
    }

    public ngOnInit(): void {
        if (this._id) {
            this.associationService.getAssociationWaldec(this._id).subscribe(rep => {
                this._selectedAssociationTab = rep;
                this.getMap();
            })
        }
        this._options = {
            center: {lat: 48.866667, lng: 2.333333},
            zoom: 12
        };
        this.adresseService.getAllDepartement().subscribe(rep => {
            this._departements = rep;
            this._departementsDrop = this._departements.map(dep => ({label: dep.nom, value: dep.code}));
        });
    }

    public getVille(): void {
        if (this._searcheParams.departement) {
            this.adresseService.getVilleByRegion(this._searcheParams.departement).subscribe(v => {
                this._ville = v.sort((a, b) => a.nom.localeCompare(b.nom));
                this._villesDrop = this._ville.map(vi => ({
                    label: vi.nom,
                    value: `${vi.nom} ${vi.codesPostaux.toString().split(',').join(' ')}`
                }));
            });
        }
    }

    public backTableau(): void {
        this.location.go(`association_waldec`);
        this._selectedAssociationTab = undefined;
    }

    public getMap(): void {
        this.associationService.getAssociationWaldec(this.selectedAssociationTab.id).subscribe(rep => {
            window.scroll(0, 0);
            this._selectedAssociationTab = rep ? rep : this._selectedAssociationTab;
            this.location.go(`association_waldec/${this._selectedAssociationTab.id}`);

            const adresse = `${this._selectedAssociationTab.adrsNumvoie} ${this._selectedAssociationTab.adrsTypevoie} ${this._selectedAssociationTab.adrsLibvoie}`;
            this.adresseService.getGeocodingGouv(adresse + ', ' + this._selectedAssociationTab.adrs_libcommune).subscribe((a: any) => {
                if (a?.features?.length > 0 && a.features[0]?.geometry?.coordinates?.length > 1) {
                    this._latitude = a.features[0].geometry.coordinates[1];
                    this._longitude = a.features[0].geometry.coordinates[0];
                }
            });
        })
    }

    private getCriteria(): any {
        if (this._searcheParams.ville) {
            const criteria = JSON.parse(JSON.stringify(query));
            criteria.bool.must.multi_match.query = this._searcheParams.ville;
            return criteria;
        } else {
            const criteria = JSON.parse(JSON.stringify(queryWithNoFilter));
            criteria.multi_match.query = query.bool.should.multi_match.query;
            return criteria;
        }
    }

    public rechercher(): void {
        this.elkSearchService.getDocumentsWithScrollFirstPage(this.getCriteria(), 'waldec_association').subscribe(r => {
            this.afficheAssociation(this.elkSearchService.getDocumentsContent(r));
        });
    }

    public searchSubjestAsso(): void {
        this.elkSearchService.getDocumentsWithScrollFirstPage(this.getCriteria(), 'waldec_association', 20).subscribe(r => {
            this._subjestAssociation = [...new Set(this.elkSearchService.getDocumentsContent(r).map(a => a.titre))];
        });
    }

    private afficheAssociation(associations): void {
        if (query?.bool?.should?.multi_match?.query.length > 0) {
            this._associations = associations;
        } else {
            this._associations = associations.sort((a, b) => {
                return a.adrs_libcommune.localeCompare(b.adrs_libcommune, 'fr');
            });
        }
        this._dataTableComponent.reset();
    }

    public resetFilter(): void {
        this._searcheParams.departement = undefined;
        this._searcheParams.ville = undefined;
        this._associations = [];
        this._dataTableComponent.reset();
        query.bool.should.multi_match.query = '';
    }
}
