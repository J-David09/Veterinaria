import { Component } from '@angular/core';

@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html',
  styleUrls: ['./clientes.component.scss']
})
export class ClientesComponent {
  isModal : boolean = false;

  openModal(){
    this.isModal = true;
  }

  closeModal(){
    this.isModal = false;
  }  
}
