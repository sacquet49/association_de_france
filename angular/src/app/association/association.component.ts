import {Component, OnInit, ViewChild} from '@angular/core';
import {AdresseService} from '../services/adresse.service';
import {SelectItem} from 'primeng/api';
import {ActivatedRoute} from '@angular/router';
import {Location} from '@angular/common';
import {ElasticSearchService} from '../services/elastic-search.service';
import {AssociationService} from '../services/association.service';
import {Association, query, queryWithNoFilter} from './association.model';
import {Departement} from '../core/core.model';

@Component({
    selector: 'ass-association',
    templateUrl: './association.component.html',
    providers: [AdresseService]
})
export class AssociationComponent implements OnInit {

    private _id;
    private _departements: Departement[] = [];
    private _departementsDrop: SelectItem[];
    private _villesDrop: SelectItem[];
    private _searcheParams: any = {ville: undefined, departement: undefined};
    private _associations: Association[];
    private _selectedAssociationTab: Association;
    private _options: any;
    @ViewChild('tableAssociation')
    private _dataTableComponent: any;
    private _latitude: number;
    private _longitude: number;
    private _subjestAssociation: string[];

    get query(): any {
        return query;
    }

    get selectedAssociationTab(): Association {
        return this._selectedAssociationTab;
    }

    set selectedAssociationTab(association: Association) {
        this._selectedAssociationTab = association;
    }

    get departementsDrop(): SelectItem[] {
        return this._departementsDrop;
    }

    get searcheParams(): any {
        return this._searcheParams;
    }

    get villesDrop(): SelectItem[] {
        return this._villesDrop;
    }

    get subjestAssociation(): string[] {
        return this._subjestAssociation;
    }

    get associations(): Association[] {
        return this._associations;
    }

    get latitude(): number {
        return this._latitude;
    }

    get longitude(): number {
        return this._longitude;
    }

    public constructor(private adresseService: AdresseService,
                       private elkSearchService: ElasticSearchService,
                       private associationComponent: AssociationService,
                       private location: Location,
                       private route: ActivatedRoute) {
        this.route.params.subscribe(params => this._id = params.id);
    }

    ngOnInit(): void {
        if (this._id) {
            this.associationComponent.getAssociation(this._id).subscribe(rep => {
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
                const ville = v.sort((a, b) => a.nom.localeCompare(b.nom));
                this._villesDrop = ville.map(vi => ({
                    label: vi.nom,
                    value: `${vi.nom} ${vi.codesPostaux.toString().split(',').join(' ')}`
                }));
            });
        }
    }

    public backTableau(): void {
        this.location.go(`association`);
        this._selectedAssociationTab = undefined;
    }

    public getMap(): void {
        this.associationComponent.getAssociation(this._selectedAssociationTab.id).subscribe(rep => {
            window.scroll(0, 0);
            this._selectedAssociationTab = rep ? rep : this._selectedAssociationTab;
            this.location.go(`association/${this._selectedAssociationTab.id}`);
            this.adresseService.getGeocodingGouv(this._selectedAssociationTab.adr1 + ', ' +
                this._selectedAssociationTab.libcom).subscribe((a: any) => {
                if (a?.features.length > 0 && a.features[0]?.geometry?.coordinates.length > 1) {
                    this._latitude = a.features[0].geometry.coordinates[1];
                    this._longitude = a.features[0].geometry.coordinates[0];
                }
            });
        });
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
        this.elkSearchService.getDocumentsWithScrollFirstPage(this.getCriteria(), 'associations').subscribe(r => {
            this.afficheAssociation(this.elkSearchService.getDocumentsContent(r));
        });
    }

    public searchSubjestAsso(): void {
        this.elkSearchService.getDocumentsWithScrollFirstPage(this.getCriteria(), 'associations', 20).subscribe(r => {
            this._subjestAssociation = [...new Set(this.elkSearchService.getDocumentsContent(r).map(a => a.titre))];
        });
    }

    private afficheAssociation(associations): void {
        if (query?.bool?.should?.multi_match?.query?.length > 0) {
            this._associations = associations;
        } else {
            this._associations = associations.sort((a, b) => {
                return a.libcom.localeCompare(b.libcom, 'fr');
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
