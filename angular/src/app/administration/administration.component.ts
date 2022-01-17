import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {ConfirmationService, MessageService} from 'primeng/api';
import {AuthentificationService} from '../core/authentication/authentification.service';
import {NouvelleService} from '../services/nouvelle.service';
import {Nouvelle, User} from './administration.model';

@Component({
    selector: 'app-administration',
    templateUrl: './administration.component.html',
    styleUrls: ['./administration.component.css']
})
export class AdministrationComponent implements OnInit {

    private _listNouvelle: Nouvelle[] = [];
    private _createNews = false;
    private _newsForm: FormGroup;
    private _listUtilisateurs: User[] = [];
    private _createUtilisateur = false;
    private _newsUtilisateur: FormGroup;

    get createNews(): boolean {
        return this._createNews;
    }

    set createNews(news) {
        this._createNews = news;
    }

    get listNouvelle(): Nouvelle[] {
        return this._listNouvelle;
    }

    get newsForm(): FormGroup {
        return this._newsForm;
    }

    get createUtilisateur(): boolean {
        return this._createUtilisateur;
    }

    get listUtilisateurs(): User[] {
        return this._listUtilisateurs;
    }

    get newsUtilisateur(): FormGroup {
        return this._newsUtilisateur;
    }

    set createUtilisateur(news) {
        this._createUtilisateur = news;
    }

    constructor(private newsService: NouvelleService,
                private confirmationService: ConfirmationService,
                private authService: AuthentificationService,
                private fb: FormBuilder,
                private messageService: MessageService) {
    }

    public ngOnInit(): void {
        this.newsService.getNouvelles().subscribe(data => {
            this._listNouvelle = data;
        });

        this.authService.getUtilisateurs().subscribe(data => {
            this._listUtilisateurs = data;
        });

        this._newsForm = this.fb.group({
            'titre': new FormControl('', Validators.required),
            'description': new FormControl('', Validators.required)
        });

        this._newsUtilisateur = this.fb.group({
            'username': new FormControl('', Validators.required),
            'password': new FormControl('', Validators.required),
            'passwordrepeat': new FormControl('', Validators.required)
        });
    }

    public supprimerNouvelle(news: any): void {
        this.confirmationService.confirm({
            message: 'Etes vous sur de supprimer cette nouvelle ?',
            accept: () => {
                this.newsService.removeNouvelle(news.id).subscribe(data => {
                    this._listNouvelle = this._listNouvelle.filter(n => n.id !== news.id);
                    this.messageService.add({
                        severity: 'success',
                        summary: 'Succès',
                        detail: 'Suppression effectuer avec succèes'
                    });
                });
            }
        });
    }

    public ajouterNouvelle(news: any): void {
        this.newsService.addNouvelle(news).subscribe(data => {
            this._createNews = false;
            this._listNouvelle.push(data);
            this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Nouvelle ajouter avec succèes'});
            this._newsForm.reset();
        });
    }

    public ajouterUtilisateur(user: any): void {
        this.authService.addUser(user).subscribe(data => {
            this._createUtilisateur = false;
            this._listUtilisateurs.push(data);
            this.messageService.add({
                severity: 'success',
                summary: 'Succès',
                detail: 'Utilisateur ajouter avec succèes'
            });
            this._newsUtilisateur.reset();
        });
    }

    public supprimerUtilisateur(user: any): void {
        this.confirmationService.confirm({
            message: 'Etes vous sur de supprimer cette utilisateur ?',
            accept: () => {
                this.authService.removeUtilisateurs(user.id).subscribe(data => {
                    this._listUtilisateurs = this._listUtilisateurs.filter(n => n.id !== user.id);
                    this.messageService.add({
                        severity: 'success',
                        summary: 'Succès',
                        detail: 'Suppression effectuer avec succèes'
                    });
                });
            }
        });
    }

    public confirmPassword(form): any {
        return form.password === form.passwordrepeat;
    }
}
