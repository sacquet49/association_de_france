import {Component, OnInit} from '@angular/core';
import {AssociationService} from '../services/association.service';
import {DataChartBar, WaldecAssociationStat} from './statistique.model';

@Component({
    selector: 'app-statistique',
    templateUrl: './statistique.component.html'
})
export class StatistiqueComponent implements OnInit {

    private _statistique: WaldecAssociationStat[] = [];
    private _data: DataChartBar;

    get data(): any {
        return this._data;
    }

    constructor(private associationService: AssociationService) {
    }

    public ngOnInit(): void {
        this.associationService.getStatWaldecAssociation().subscribe(res => {
            this._statistique = res.sort((a, b) => {
                return a.departement.localeCompare(b.departement, 'fr');
            });
            this._data = {
                labels: this._statistique.map(s => s.departement),
                datasets: [
                    {
                        label: 'Nombre d\'associations',
                        backgroundColor: '#42A5F5',
                        borderColor: '#1E88E5',
                        data: this._statistique.map(s => s.count)
                    },
                ]
            }
        });
    }
}
