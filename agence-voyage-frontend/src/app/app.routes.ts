import { Routes } from '@angular/router';
import { AddCircuit } from './add-circuit/add-circuit';
import { AddVoyage } from './add-voyage/add-voyage';
import { Circuits } from './circuits/circuits';
import { EditCircuit } from './edit-circuit/edit-circuit';
import { EditVoyage } from './edit-voyage/edit-voyage';
import { authGuard } from './guards/auth-guard';
import { Home } from './home/home';
import { Login } from './login/login';
import { Register } from './register/register';
import { Voyages } from './voyages/voyages';

export const routes: Routes = [
  { path: 'home', component: Home },
  { path: 'login', component: Login },
  { path: 'register', component: Register },
  { path: 'voyages', component: Voyages, canActivate: [authGuard] },
  { path: 'add-voyage', component: AddVoyage, canActivate: [authGuard] },
  { path: 'edit-voyage/:id', component: EditVoyage, canActivate: [authGuard] },
  { path: 'circuits', component: Circuits, canActivate: [authGuard] },
  { path: 'add-circuit', component: AddCircuit, canActivate: [authGuard] },
  { path: 'edit-circuit/:id', component: EditCircuit, canActivate: [authGuard] },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
];
