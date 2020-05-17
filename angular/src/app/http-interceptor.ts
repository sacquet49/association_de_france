import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import {MessageService} from "primeng";

@Injectable({ providedIn: 'root' })
export class ErreurInterceptor implements HttpInterceptor {

    constructor(private messageService: MessageService) {
    }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(req).pipe(
            catchError((error) => {
                if (error.message) {
                    this.messageService.add({severity: 'error', summary: 'Erreur', detail: error?.message});
                }
                return throwError(error);
            }),
        );
    }
}
