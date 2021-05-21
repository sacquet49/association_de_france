import { TestBed, waitForAsync } from '@angular/core/testing';

import { AssociationComponent } from './association.component';

describe('AssociationWaldecComponent', () => {
    beforeEach(waitForAsync(() => {
        TestBed.configureTestingModule({
            declarations: [
                AssociationComponent
            ],
        }).compileComponents();
    }));
    
    it('should create the app', waitForAsync(() => {
        const fixture = TestBed.createComponent(AssociationComponent);
        const app = fixture.debugElement.componentInstance;
        expect(app).toBeTruthy();
    }));
    
    it(`should have as title 'app works!'`, waitForAsync(() => {
        const fixture = TestBed.createComponent(AssociationComponent);
        const app = fixture.debugElement.componentInstance;
        expect(app.title).toEqual('app works!');
    }));
    
    it('should render title in a h1 tag', waitForAsync(() => {
        const fixture = TestBed.createComponent(AssociationComponent);
        fixture.detectChanges();
        const compiled = fixture.debugElement.nativeElement;
        expect(compiled.querySelector('h1').textContent).toContain('app works!');
    }));
});
