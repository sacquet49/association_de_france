import {AfterViewInit, Component, Input, OnChanges, SimpleChanges} from '@angular/core';
import * as L from 'leaflet';

@Component({
    selector: 'ass-carte',
    template: '<div [id]="name" class="map-height"></div>',
})
export class CarteComponent implements AfterViewInit, OnChanges {

    @Input()
    name: string;
    @Input()
    latitude: number;
    @Input()
    longitude: number;
    private _map: any;
    private _marker: any;

    public ngAfterViewInit(): void {
        this.setInterval();
    }

    private setInterval(): void {
        if (document.getElementById(this.name)) {
            this._map = L.map(this.name, {center: [48.86, 2.35], zoom: 16});
            L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {attribution: 'OpenStreetMap'}).addTo(this._map);
            this.setMarker();
        }
    };

    private setMarker(): void {
        const myIcon = L.icon({iconUrl: 'assets/marker-icon.png'});
        if (this._map && this.latitude && this.longitude) {
            if (this._marker) {
                this._marker.setLatLng([this.latitude, this.longitude]);
            } else {
                this._marker = L.marker([this.latitude, this.longitude], {icon: myIcon});
                this._map.addLayer(this._marker);
            }
            this._map.setView([this.latitude, this.longitude], 16);
        }
    };

    public ngOnChanges(changes: SimpleChanges): void {
        this.setMarker();
    }
}
