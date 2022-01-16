import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {ConfirmationService, MessageService} from 'primeng/api';
import {AuthenticationService} from '../core/authentication/authentication.service';
import {NouvelleService} from '../services/nouvelle.service';

@Component({
    selector: 'app-administration',
    templateUrl: './administration.component.html',
})
export class AdministrationComponent implements OnInit {

    listNouvelle: any = [];
    createNews = false;
    newsForm: FormGroup;
    listUtilisateurs: any = [];
    createUtilisateur = false;
    newsUtilisateur: FormGroup;

    constructor(private newsService: NouvelleService,
                private confirmationService: ConfirmationService,
                private authService: AuthenticationService,
                private fb: FormBuilder,
                private messageService: MessageService) {
    }

    ngOnInit() {
        this.newsService.getNouvelles().subscribe(data => {
            this.listNouvelle = data;
        });

        this.authService.getUtilisateurs().subscribe(data => {
            this.listUtilisateurs = data;
        });

        this.newsForm = this.fb.group({
            'titre': new FormControl('', Validators.required),
            'description': new FormControl('', Validators.required)
        });

        this.newsUtilisateur = this.fb.group({
            'username': new FormControl('', Validators.required),
            'password': new FormControl('', Validators.required),
            'passwordrepeat': new FormControl('', Validators.required)
        });
    }

    supprimerNouvelle(news: any) {
        this.confirmationService.confirm({
            message: 'Etes vous sur de supprimer cette nouvelle ?',
            accept: () => {
                this.newsService.removeNouvelle(news.id).subscribe(data => {
                    this.listNouvelle = this.listNouvelle.filter(n => n.id !== news.id);
                    this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Suppression effectuer avec succèes'});
                });
            }
        });
    }

    ajouterNouvelle(news: any) {
        this.newsService.addNouvelle(news).subscribe(data => {
            this.createNews = false;
            this.listNouvelle.push(data);
            this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Nouvelle ajouter avec succèes'});
            this.newsForm.reset();
        });
    }

    ajouterUtilisateur(user: any) {
        this.authService.addUser(user).subscribe(data => {
            this.createUtilisateur = false;
            this.listUtilisateurs.push(data);
            this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Utilisateur ajouter avec succèes'});
            this.newsUtilisateur.reset();
        });
    }

    supprimerUtilisateur(user: any) {
        this.confirmationService.confirm({
            message: 'Etes vous sur de supprimer cette utilisateur ?',
            accept: () => {
                this.authService.removeUtilisateurs(user.id).subscribe(data => {
                    this.listUtilisateurs = this.listUtilisateurs.filter(n => n.id !== user.id);
                    this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Suppression effectuer avec succèes'});
                });
            }
        });
    }

    confirmPassword(form) {
       return form.password === form.passwordrepeat;
    }
}
