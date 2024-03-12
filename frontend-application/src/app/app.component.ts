import { Component } from '@angular/core';
import { Router } from '@angular/router';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'frontend-application';

  constructor(private router: Router) { }

  isLoginPage(): boolean {
    return this.router.url === '/login';
  }

  navigateMedicamentos () {
    this.router.navigate(["/medicamentos"]);
  }

  navigateMascotas () {
    this.router.navigate(["/mascotas"]);
  }

  navigateClientes () {
    this.router.navigate(["/clientes"]);
  }
}
