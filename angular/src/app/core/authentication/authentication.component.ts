import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {AuthenticationService} from './authentication.service';
import {Location} from '@angular/common';
import {HttpErrorResponse} from '@angular/common/http';
import {MessageService} from 'primeng/api';

@Component({
    selector: 'app-authentication',
    templateUrl: './authentication.component.html'
})
export class AuthenticationComponent implements OnInit {

    display: boolean = false;
    loginForm: FormGroup;
    authError: string = '';
    @Output() isConnect = new EventEmitter<any>();

    constructor(private formBuilder: FormBuilder,
                private location: Location,
                private authenticationService: AuthenticationService,
                private router: Router,
                private messageService: MessageService) {
    }

    ngOnInit() {
        this.loginForm = this.formBuilder.group({
            'username': ['', Validators.required],
            'password': ['', Validators.required]
        });
    }

    showDialog() {
        this.loginForm.reset();
        this.display = true;
    }

    onSubmit() {
        this.authenticationService.authenticate(this.loginForm.value).subscribe(data => {
            this.authError = '';
            localStorage.setItem('id_token', data.token);
            this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Vous êtes maintenant connecter'});
            this.isConnect.emit(true);
            this.display = false;
        }, (error: HttpErrorResponse) => {
            this.authError = 'Login ou mot de passe incorrect';
            this.isConnect.emit(false);
        });
    }

    isConnected() {
        return this.authenticationService.loggedIn();
    }

    deconnection() {
        this.authenticationService.logout();
        this.isConnect.emit(false);
        this.router.navigate(['']);
    }
}
