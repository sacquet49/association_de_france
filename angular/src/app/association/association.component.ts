import {Component, OnInit, ViewChild} from '@angular/core';
import {AdresseService} from '../services/adresse.service';
import {SelectItem} from "primeng/api";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {ElasticSearchService} from "../services/elastic-search.service";
import {AssociationService} from "../services/association.service";

@Component({
    selector: 'ass-association',
    templateUrl: './association.component.html',
    providers: [AdresseService]
})
export class AssociationComponent implements OnInit {

    id;
    departements: any = [];
    departementSelected: string;
    departementsDrop: SelectItem[];
    villesDrop: SelectItem[];
    villeSelected: any;
    associations: any;
    selectedAssociationTab: any;
    options: any;
    @ViewChild('tableAssociation')
    dataTableComponent: any;
    latitude: number;
    longitude: number;
    subjestAssociation: string[];
    query = {
        multi_match: {
            query: "",
            fields: ["objet", "titre^2", "libcom^3"]
        }
    };

    constructor(private adresseService: AdresseService,
                private elkSearchService: ElasticSearchService,
                private associationComponent: AssociationService,
                private location: Location,
                private route: ActivatedRoute) {
        this.route.params.subscribe(params => this.id = params.id);
    }

    ngOnInit() {
        if (this.id) {
            this.associationComponent.getAssociation(this.id).subscribe(rep => {
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

    getVille() {
        if(this.departementSelected) {
            this.adresseService.getVilleByRegion(this.departementSelected).subscribe(v => {
                let ville = v.sort((a, b) => a.nom.localeCompare(b.nom));
                this.villesDrop = ville.map(vi => ({label: vi.nom, value: vi.nom}));
            });
        }
    }

    backTableau() {
        this.location.go(`association`);
        this.selectedAssociationTab = undefined;
    }

    getMap() {
        this.associationComponent.getAssociation(this.selectedAssociationTab.id).subscribe(rep => {
            window.scroll(0,0);
            this.selectedAssociationTab = rep;
            this.location.go(`association/${this.selectedAssociationTab.id}`);
            this.adresseService.getGeocodingGouv(this.selectedAssociationTab.adr1 + ', ' + this.selectedAssociationTab.libcom).subscribe((a: any) => {
                if (a && a.features && a.features.length > 0 && a.features[0].geometry && a.features[0].geometry.coordinates.length > 1) {
                    this.latitude = a.features[0].geometry.coordinates[1];
                    this.longitude = a.features[0].geometry.coordinates[0];
                }
            });
        });
    }

    getCriteria() {
        console.log(this.villeSelected);
        let criteria = JSON.parse(JSON.stringify(this.query));
        if(criteria?.multi_match?.query?.length > 0) {
            criteria.multi_match.fields = ["objet", "titre^3", "libcom^2"];
        }
        criteria.multi_match.query += ` ${this.villeSelected ? this.villeSelected : (this.departementSelected ? (this.departementSelected + '000')  : '')}`;
        return criteria;
    }

    rechercher() {
        this.elkSearchService.getDocumentsWithScrollFirstPage(this.getCriteria(), "associations").subscribe(r => {
            this.afficheAssociation(this.elkSearchService.getDocumentsContent(r));
        });
    }

    searchSubjestAsso() {
        let criteria = this.getCriteria();
        criteria.multi_match.fields = ["titre", "libcom"];
        this.elkSearchService.getDocumentsWithScrollFirstPage(criteria, "associations", 20).subscribe(r => {
            this.subjestAssociation = this.elkSearchService.getDocumentsContent(r).map(a => a.titre);
        });
    }

    afficheAssociation(associations) {
        if(this.query?.multi_match?.query?.length > 0) {
            this.associations = associations;
        } else {
            this.associations = associations.sort((a, b) => {
                return a.libcom.localeCompare(b.libcom, 'fr');
            });
        }
        this.dataTableComponent.reset();
    }

    resetFilter() {
        this.departementSelected = undefined;
        this.villeSelected = undefined;
        this.associations = [];
        this.dataTableComponent.reset();
        this.query.multi_match.query = '';
    }
}
