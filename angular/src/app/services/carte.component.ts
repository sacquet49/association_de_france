import {AfterViewInit, Component, Input, OnChanges, SimpleChanges} from '@angular/core';
import * as L from 'leaflet';

@Component({
    selector: 'ass-carte',
    template: '<div [id]="name" style="height: 400px;"></div>'
})
export class CarteComponent implements AfterViewInit, OnChanges {

    @Input() name: string;
    @Input() latitude: number;
    @Input() longitude: number;
    map: any;
    marker: any;

    ngAfterViewInit(): void {
        this.setInterval();
    }

    setInterval() {
        if (document.getElementById(this.name)) {
            this.map = L.map(this.name, {center: [48.86, 2.35], zoom: 16});
            L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {attribution: 'OpenStreetMap'}).addTo(this.map);
            this.setMarker();
        }
    };

    setMarker() {
        const myIcon = L.icon({iconUrl: 'assets/marker-icon.png'});
        if (this.map && this.latitude && this.longitude) {
            if (this.marker) {
                this.marker.setLatLng([this.latitude, this.longitude]);
            } else {
                this.marker = L.marker([this.latitude, this.longitude], {icon: myIcon});
                this.map.addLayer(this.marker);
            }
            this.map.setView([this.latitude, this.longitude], 16);
        }
    };

    ngOnChanges(changes: SimpleChanges): void {
        this.setMarker();
    }
}
