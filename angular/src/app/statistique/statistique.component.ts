import {Component, OnInit} from '@angular/core';
import {AssociationService} from '../services/association.service';
import {AssociationStat, DataChartBar} from './statistique.model';
import {forkJoin} from "rxjs";

@Component({
    selector: 'app-statistique',
    templateUrl: './statistique.component.html'
})
export class StatistiqueComponent implements OnInit {

    private _statistiqueWa: AssociationStat[] = [];
    private _statistiqueAssocation: AssociationStat[] = [];
    private _data: DataChartBar;

    get data(): DataChartBar {
        return this._data;
    }

    constructor(private associationService: AssociationService) {
    }

    public ngOnInit(): void {
        forkJoin([this.associationService.getStatWaldecAssociation(),
            this.associationService.getStatAssociation()])
            .subscribe(res => {
                this._statistiqueWa = res[0].sort((a, b) => {
                    return a.departement.localeCompare(b.departement, 'fr');
                });
                this._statistiqueAssocation = res[1].sort((a, b) => {
                    return a.departement.localeCompare(b.departement, 'fr');
                });
                console.log(this._statistiqueAssocation);
                this._data = {
                    labels: this._statistiqueWa.map(s => s.departement),
                    datasets: [
                        {
                            label: 'Nombre d\'associations après 2009',
                            backgroundColor: '#42A5F5',
                            borderColor: '#1E88E5',
                            data: this._statistiqueWa.map(s => s.count)
                        },
                        {
                            label: 'Nombre d\'associations avant 2009',
                            backgroundColor: '#69f542',
                            borderColor: '#69f542',
                            data: this._statistiqueAssocation.map(s => s.count)
                        }
                    ]
                }
            });
    }
}
