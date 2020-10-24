import {Component, OnInit} from '@angular/core';
import {MessageService} from "primeng/api";

@Component({
    selector: 'ass-notification',
    template: `
        <p-toast></p-toast>
        <p-confirmDialog icon="pi pi-warning" acceptLabel="Oui" rejectLabel="Non"></p-confirmDialog>`
})
export class NotificationComponent {
}
