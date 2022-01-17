import {Component, OnInit} from '@angular/core';
import {AdresseService} from '../../services/adresse.service';
import {NouvelleService} from '../../services/nouvelle.service';
import {Nouvelle} from '../../administration/administration.model';

@Component({
    selector: 'ass-home',
    templateUrl: './home.component.html',
    providers: [AdresseService]
})
export class HomeComponent implements OnInit {

    private _nouvelles: Nouvelle[] = [];

    get nouvelles(): Nouvelle[] {
       return this._nouvelles;
    }

    constructor(private newsService: NouvelleService) {

    }

    public ngOnInit(): void {
        this.newsService.getNouvelles().subscribe(rep => {
            this._nouvelles = rep;
        });
    }
}
