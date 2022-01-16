import {Component, EventEmitter, Output} from '@angular/core';
import {AdresseService} from '../../services/adresse.service';

@Component({
    selector: 'adresse-search',
    template: `
        <p-autoComplete [minLength]="3" placeholder="Voie, ville, code postal..."
                        (onSelect)="adresseSelected.emit($event)" [styleClass]="'w-100'" [inputStyleClass]="'w-100'"
                        [field]="'properties.label'" [suggestions]="suggestions"
                        (completeMethod)="getAdresses($event)"></p-autoComplete>   `,
})
export class AdresseSearchComponent {

    @Output() adresseSelected = new EventEmitter<any>();
    suggestions: any[] = [];

    constructor(private departement: AdresseService) {
    }

    public getAdresses(event): void {
        this.departement.getAdresseGouv(event.query).subscribe((data: any) => {
            this.suggestions = data.features;
        });
    }
}

