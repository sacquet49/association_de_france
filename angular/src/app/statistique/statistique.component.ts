import {Component, OnInit} from '@angular/core';
import {AssociationService} from '../services/association.service';

@Component({
    selector: 'app-statistique',
    templateUrl: './statistique.component.html',
    styleUrls: ['./statistique.component.css']
})
export class StatistiqueComponent implements OnInit {

    statistique: any = [];
    data: any;

    constructor(private associationService: AssociationService) {
    }

    public ngOnInit(): void {
        this.associationService.getStatWaldecAssociation().subscribe(res => {
            this.statistique = res.sort((a, b) => {
                return a.departement - b.departement;
            });
            this.data = {
                labels: this.statistique.map(s => s.departement),
                datasets: [
                    {
                        label: 'Nombres d\'association',
                        backgroundColor: '#42A5F5',
                        borderColor: '#1E88E5',
                        data: this.statistique.map(s => s.nombre_association)
                    },
                ]
            }
        });
    }
}
