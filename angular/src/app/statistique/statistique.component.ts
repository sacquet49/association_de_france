import {Component, OnInit} from '@angular/core';
import {AssociationService} from '../services/association.service';

@Component({
    selector: 'app-statistique',
    templateUrl: './statistique.component.html',
    styleUrls: ['./statistique.component.css']
})
export class StatistiqueComponent implements OnInit {

    private _statistique: any = [];
    private _data: any;

    get data(): any {
        return this._data;
    }

    constructor(private associationService: AssociationService) {
    }

    public ngOnInit(): void {
        this.associationService.getStatWaldecAssociation().subscribe(res => {
            this._statistique = res.sort((a, b) => {
                return a.departement - b.departement;
            });
            this._data = {
                labels: this._statistique.map(s => s.departement),
                datasets: [
                    {
                        label: 'Nombres d\'association',
                        backgroundColor: '#42A5F5',
                        borderColor: '#1E88E5',
                        data: this._statistique.map(s => s.nombre_association)
                    },
                ]
            }
        });
    }
}
