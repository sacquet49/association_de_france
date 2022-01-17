import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {AuthentificationService} from './authentification.service';
import {Location} from '@angular/common';
import {HttpErrorResponse} from '@angular/common/http';
import {MessageService} from 'primeng/api';

@Component({
    selector: 'app-authentication',
    templateUrl: './authentification.component.html',
    styleUrls: ['./authentification.component.css']
})
export class AuthentificationComponent implements OnInit {

    @Output()
    isConnect = new EventEmitter<any>();

    private _display = false;
    private _loginForm: FormGroup;
    private _authError = '';

    get authError(): string {
        return this._authError;
    }

    get loginForm(): FormGroup {
        return this._loginForm;
    }

    get display(): boolean {
        return this._display;
    }

    set display(disp) {
        this._display = disp;
    }

    constructor(private formBuilder: FormBuilder,
                private location: Location,
                private authenticationService: AuthentificationService,
                private router: Router,
                private messageService: MessageService) {
    }

    public ngOnInit(): void {
        this._loginForm = this.formBuilder.group({
            'username': ['', Validators.required],
            'password': ['', Validators.required]
        });
    }

    public showDialog(): void {
        this._loginForm.reset();
        this._display = true;
    }

    public onSubmit(): void {
        this.authenticationService.authenticate(this._loginForm.value).subscribe(data => {
            this._authError = '';
            localStorage.setItem('id_token', data.token);
            this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Vous êtes maintenant connecter'});
            this.isConnect.emit(true);
            this._display = false;
        }, (error: HttpErrorResponse) => {
            this._authError = 'Login ou mot de passe incorrect';
            this.isConnect.emit(false);
        });
    }

    public isConnected(): any {
        return this.authenticationService.loggedIn();
    }

    public deconnection(): void {
        this.authenticationService.logout();
        this.isConnect.emit(false);
        this.router.navigate(['']);
    }
}
