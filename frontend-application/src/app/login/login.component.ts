import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  username: any;
  password: any;
  isUserValid = true;
  message: any;

  constructor(private authService: AuthService, private router: Router) { }

  onSubmit() {
    this.authService.login(this.username, this.password).subscribe(
      response => {
        localStorage.setItem('username', this.username);
        this.router.navigate(['/home']);
      },
      Error => {
        this.isUserValid = false;
        this.message = "Usuario o contraseña invalida";
      }
    );
  }

  

}
