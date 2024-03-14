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

  currentClient : Cliente = {
    id : 0,
    nombre : "",
    apellido: "",
    direccion: "",
    telefono: ""
  }

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

  editarUsuario (){
    this.clienteService.editUser(this.currentClient.id, this.currentClient).subscribe(response => {
      this.consultarUsuarios;
      this.closeModal();
    },
    error => {
      alert("Error al actualizar usuario");
      this.closeModal;
    })
  }

  addUser (){
    this.clienteService.addUser(this.currentClient).subscribe(response => {
      this.consultarUsuarios();
      this.closeModal();
    },
    error => {
      alert("Error al crear el usuario");
      this.closeModal;
    })
  }

  delete () {
    this.clienteService.delete(this.currentClient.id).subscribe(response => {
      this.consultarUsuarios();
      this.closeModal();
    },
    error => {
      alert("Error al eliminar cliente");
      this.closeModal();
    })
  }

  openAddForm() {
    this.isAdd = true;
  }

  openInfoForm(cliente: Cliente) {
    this.isInfo = true;
    this.currentClient = cliente
  }

  openEditForm(cliente: Cliente) {
    this.isEdit = true;
    this.currentClient = cliente
  }

  openDeleteForm(cliente: Cliente) {
    this.isDelete = true;
    this.currentClient = cliente
  }

  closeModal() {    
    this.isAdd = false;
    this.isInfo = false;
    this.isEdit = false;
    this.isDelete = false;
    this.currentClient = {
      id : 0,
      nombre : "",
      apellido: "",
      direccion: "",
      telefono: ""
    }
  }
}
