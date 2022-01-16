import {Component, OnInit} from '@angular/core';
import {AdresseService} from '../../services/adresse.service';
import {NouvelleService} from '../../services/nouvelle.service';

@Component({
    selector: 'ass-home',
    templateUrl: './home.component.html',
    providers: [AdresseService]
})
export class HomeComponent implements OnInit {

    nouvelles = [];

    constructor(private newsService: NouvelleService) {

    }

    public ngOnInit(): void {
        this.newsService.getNouvelles().subscribe(rep => {
            this.nouvelles = rep;
        });
    }
}
