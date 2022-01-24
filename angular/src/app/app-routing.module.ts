import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from './core/home/home.component';
import {AssociationComponent} from './association/association.component';
import {AssociationWaldecComponent} from './waldec_association/associationWaldec.component';
import {AdministrationComponent} from './administration/administration.component';
import {AuthGuard} from './core/guard';
import {StatistiqueComponent} from './statistique/statistique.component';

const appRoutes: Routes = [
    {path: '', redirectTo: 'home', pathMatch: 'full'},
    {path: 'home', component: HomeComponent},
    {path: 'association', component: AssociationComponent},
    {path: 'association/:id', component: AssociationComponent},
    {path: 'association_waldec', component: AssociationWaldecComponent},
    {path: 'association_waldec/:id', component: AssociationWaldecComponent},
    {path: 'administration', component: AdministrationComponent, canActivate: [AuthGuard]},
    {path: 'statistique', component: StatistiqueComponent, canActivate: [AuthGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes, {enableTracing: false, relativeLinkResolution: 'legacy' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
