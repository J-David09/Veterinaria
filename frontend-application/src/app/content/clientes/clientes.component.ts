import { Component } from '@angular/core';

@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html',
  styleUrls: ['./clientes.component.scss']
})
export class ClientesComponent {  
  isAdd:boolean = false;;
  isInfo:boolean = false;
  isEdit:boolean = false;
  isDelete:boolean = false;

  constructor() { }

  openAddForm() {
    this.isAdd = true;
  }

  openInfoForm() {
    this.isInfo = true;
  }

  openEditForm() {
    this.isEdit = true;
  }

  openDeleteForm() {
    this.isDelete = true;
  }

  closeModal() {    
    this.isAdd = false;
    this.isInfo = false;
    this.isEdit = false;
    this.isDelete = false;
  }
}
