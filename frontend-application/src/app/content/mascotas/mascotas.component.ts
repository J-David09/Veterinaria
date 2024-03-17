import { Component } from '@angular/core';
import { Cliente } from 'src/app/interfaces/cliente';
import { Mascota } from 'src/app/interfaces/mascota';
import { MascotaService } from 'src/app/services/mascota.service';

@Component({
  selector: 'app-mascotas',
  templateUrl: './mascotas.component.html',
  styleUrls: ['./mascotas.component.scss']
})
export class MascotasComponent {
  isAdd:boolean = false;;
  isInfo:boolean = false;
  isEdit:boolean = false;
  isDelete:boolean = false;

  mascotas: Mascota[] = [];

  currentMascota : Mascota = {
    id : 0,
    nombre : "",
    edad: 0,
    idCliente: 0,
    peso: 0,
    raza: ""
  }

  client : Cliente = {
    id : 0,
    nombre : "",
    apellido: "",
    direccion: "",
    telefono: ""
  }

  constructor(private mascotaService: MascotaService) { }

  ngOnInit() {
    this.consultarUsuarios();
  }

  consultarUsuarios () {
    this.mascotaService.getAll().subscribe(response => {
      this.mascotas = response.data;
      console.log(this.mascotas);
    },
    error => {
      alert("Error al consultar usuarios");
    })
  }

  editarUsuario (){
    this.mascotaService.editUser(this.currentMascota.id, this.currentMascota).subscribe(response => {
      this.consultarUsuarios;
      this.closeModal();
    },
    error => {
      alert("Error al actualizar usuario");
      this.closeModal;
    })
  }

  addUser (){
    this.mascotaService.addUser(this.currentMascota).subscribe(response => {
      this.consultarUsuarios();
      this.closeModal();
    },
    error => {
      alert("Error al crear el usuario");
      this.closeModal;
    })
  }

  delete () {
    this.mascotaService.delete(this.currentMascota.id).subscribe(response => {
      this.consultarUsuarios();
      this.closeModal();
    },
    error => {
      alert("Error al eliminar cliente");
      this.closeModal();
    })
  }

  getOwner (id: number) {
    this.mascotaService.getOwner(id).subscribe(response => {
      this.client = response.data;
    },
    error => {
      alert("Error al consultar cliente");
      this.closeModal();
    })
  }

  openAddForm() {
    this.isAdd = true;
  }

  openInfoForm(mascota: Mascota) {
    this.isInfo = true;
    this.currentMascota = mascota
    this.getOwner(this.currentMascota.idCliente);
  }

  openEditForm(mascota: Mascota) {
    this.isEdit = true;
    this.currentMascota = mascota
  }

  openDeleteForm(mascota: Mascota) {
    this.isDelete = true;
    this.currentMascota = mascota
  }

  closeModal() {    
    this.isAdd = false;
    this.isInfo = false;
    this.isEdit = false;
    this.isDelete = false;
    this.currentMascota =  {
      id : 0,
      nombre : "",
      edad: 0,
      idCliente: 0,
      peso: 0,
      raza: ""
    }
    this.client = {
      id : 0,
      nombre : "",
      apellido: "",
      direccion: "",
      telefono: ""
    }
  }
}
