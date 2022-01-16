import {Component, OnInit, ViewChild} from '@angular/core';
import {Location} from '@angular/common';
import {AdresseService} from '../services/adresse.service';
import {SelectItem} from 'primeng/api';
import {ActivatedRoute} from '@angular/router';
import {Subject} from 'rxjs';
import {ElasticSearchService} from '../services/elastic-search.service';
import {AssociationService} from '../services/association.service';
import {query, queryWithNoFilter} from './associationWaldec.model';

@Component({
    selector: 'ass-association-waldec',
    templateUrl: './associationWaldec.component.html',
    providers: [AdresseService]
})
export class AssociationWaldecComponent implements OnInit {

    id;
    departements: any = [];
    ville: any = [];
    departementSelected: string;
    departementsDrop: SelectItem[];
    villesDrop: SelectItem[];
    associations: any;
    selectedAssociationTab: any;
    options: any;
    @ViewChild('tableAssociation')
    dataTableComponent: any;
    searcheParams: any = {ville: undefined};
    search = new Subject<any>();
    latitude: number;
    longitude: number;
    subjestAssociation: string[];

    get query() {
        return query;
    }

    constructor(private adresseService: AdresseService,
                private associationService: AssociationService,
                private location: Location,
                private elkSearchService: ElasticSearchService,
                private route: ActivatedRoute) {
        this.route.params.subscribe(params => this.id = params.id);
    }

    public ngOnInit(): void {
        if (this.id) {
            this.associationService.getAssociationWaldec(this.id).subscribe(rep => {
                this.selectedAssociationTab = rep;
                this.getMap();
            })
        }
        this.options = {
            center: {lat: 48.866667, lng: 2.333333},
            zoom: 12
        };
        this.adresseService.getAllDepartement().subscribe(rep => {
            this.departements = rep;
            this.departementsDrop = this.departements.map(dep => ({label: dep.nom, value: dep.code}));
        });
    }

    public getVille(): void {
        if (this.departementSelected) {
            this.adresseService.getVilleByRegion(this.departementSelected).subscribe(v => {
                this.ville = v.sort((a, b) => a.nom.localeCompare(b.nom));
                this.villesDrop = this.ville.map(vi => ({
                    label: vi.nom,
                    value: `${vi.nom} ${vi.codesPostaux.toString().split(',').join(' ')}`
                }));
            });
        }
    }

    public backTableau(): void {
        this.location.go(`association_waldec`);
        this.selectedAssociationTab = undefined;
    }

    public getMap(): void {
        this.associationService.getAssociationWaldec(this.selectedAssociationTab.id).subscribe(rep => {
            window.scroll(0, 0);
            this.selectedAssociationTab = rep ? rep : this.selectedAssociationTab;
            this.location.go(`association_waldec/${this.selectedAssociationTab.id}`);

            const adresse = `${this.selectedAssociationTab.adrsNumvoie} ${this.selectedAssociationTab.adrsTypevoie} ${this.selectedAssociationTab.adrsLibvoie}`;
            this.adresseService.getGeocodingGouv(adresse + ', ' + this.selectedAssociationTab.adrs_libcommune).subscribe((a: any) => {
                if (a?.features?.length > 0 && a.features[0]?.geometry?.coordinates?.length > 1) {
                    this.latitude = a.features[0].geometry.coordinates[1];
                    this.longitude = a.features[0].geometry.coordinates[0];
                }
            });
        })
    }

    private getCriteria(): any {
        if (this.searcheParams.ville) {
            const criteria = JSON.parse(JSON.stringify(query));
            criteria.bool.must.multi_match.query = this.searcheParams.ville;
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
            this.subjestAssociation = [...new Set(this.elkSearchService.getDocumentsContent(r).map(a => a.titre))];
        });
    }

    private afficheAssociation(associations): void {
        if (query?.bool?.should?.multi_match?.query.length > 0) {
            this.associations = associations;
        } else {
            this.associations = associations.sort((a, b) => {
                return a.adrs_libcommune.localeCompare(b.adrs_libcommune, 'fr');
            });
        }
        this.dataTableComponent.reset();
    }

    public resetFilter(): void {
        this.departementSelected = undefined;
        this.searcheParams.ville = undefined;
        this.associations = [];
        this.dataTableComponent.reset();
        query.bool.should.multi_match.query = '';
    }
}
