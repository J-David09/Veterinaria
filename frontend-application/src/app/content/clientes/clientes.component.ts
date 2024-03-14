import { Component } from '@angular/core';
import { Cliente } from 'src/app/interfaces/cliente';
import { ClienteService } from 'src/app/services/cliente.service';

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

  clientes: Cliente[] = [];

  constructor(private clienteService: ClienteService) { }

  ngOnInit() {
    this.consultarUsuarios();
  }

  consultarUsuarios () {
    this.clienteService.getAll().subscribe(response => {
      this.clientes = response.data;
      console.log(this.clientes);
    },
    error => {
      alert("Error al consultar usuarios");
    })
  }

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
