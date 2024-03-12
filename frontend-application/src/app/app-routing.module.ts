import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { ClientesComponent } from './content/clientes/clientes.component';
import { MascotasComponent } from './content/mascotas/mascotas.component';
import { MedicamentosComponent } from './content/medicamentos/medicamentos.component';

const routes: Routes = [
  {path: 'login',  component: LoginComponent},
  {path: 'home', component: HomeComponent},
  {path: 'clientes', component: ClientesComponent},
  {path: 'mascotas', component: MascotasComponent},
  {path: 'medicamentos', component: MedicamentosComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
