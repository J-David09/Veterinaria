import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'frontend-application';
  isClients: boolean = false;
  isPets: boolean = false;
  isFarmacy: boolean = false;  

  constructor(private router: Router) {}

  isLoginPage(): boolean {    
    return this.router.url === '/login';
  }

  navigateMedicamentos() {
    this.router.navigate(["/medicamentos"]);
    this.isClients = false;
    this.isPets = false;
    this.isFarmacy = true; 
  }

  navigateMascotas() {
    this.router.navigate(["/mascotas"]);
    this.isClients = false;
    this.isPets = true;
    this.isFarmacy = false; 
  }

  navigateClientes() {
    this.router.navigate(["/clientes"]);
    this.isClients = true;
    this.isPets = false;
    this.isFarmacy = false; 
  }

  navigateHome() {
    this.router.navigate(["/home"]);
  }
}
