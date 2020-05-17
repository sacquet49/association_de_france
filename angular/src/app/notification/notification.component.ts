import {Component, OnInit} from '@angular/core';
import {MessageService} from "primeng/api";

@Component({
    selector: 'ass-notification',
    template: `
        <p-toast></p-toast>
        <p-confirmDialog icon="ui-icon-warning" acceptLabel="Oui" rejectLabel="Non"></p-confirmDialog>`
})
export class NotificationComponent {
}
