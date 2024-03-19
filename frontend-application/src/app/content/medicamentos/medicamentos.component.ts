import { Component } from '@angular/core';
import { Cliente } from 'src/app/interfaces/cliente';
import { Mascota } from 'src/app/interfaces/mascota';
import { Medicamento } from 'src/app/interfaces/medicamento';
import { MascotaService } from 'src/app/services/mascota.service';
import { MedicamentoService } from 'src/app/services/medicamento.service';


@Component({
  selector: 'app-medicamentos',
  templateUrl: './medicamentos.component.html',
  styleUrls: ['./medicamentos.component.scss']
})
export class MedicamentosComponent {  
  
  isAdd:boolean = false;;
  isInfo:boolean = false;
  isEdit:boolean = false;
  isDelete:boolean = false;

  medicamentos: Medicamento[] = [];

  currentMedicamento : Medicamento = {
    id : 0,
    nombre : "",
    descripcion: ""
  }

  medicamento : Medicamento = {
    id : 0,
    nombre : "",
    descripcion: ""
  }

  constructor(private medicamentoService: MedicamentoService) { }

  ngOnInit() {
    this.consultarMedicamentos();
  }

  consultarMedicamentos () {
    this.medicamentoService.getAll().subscribe(response => {
      this.medicamentos = response.data;
      console.log(this.medicamentos);
    },
    error => {
      alert("Error al consultar medicamentos");
    })
  }

  editarUsuario (){
    this.medicamentoService.editMedicamento(this.currentMedicamento.id, this.currentMedicamento).subscribe(response => {
      this.consultarMedicamentos;
      this.closeModal();
    },
    error => {
      alert("Error al actualizar usuario");
      this.closeModal;
    })
  }

  addUser (){
    this.medicamentoService.addMedicamento(this.currentMedicamento).subscribe(response => {
      this.consultarMedicamentos();
      this.closeModal();
    },
    error => {
      alert("Error al crear el usuario");
      this.closeModal;
    })
  }

  delete () {
    this.medicamentoService.deleteMedicamento(this.currentMedicamento.id).subscribe(response => {
      this.consultarMedicamentos();
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

  openInfoForm(medicamento: Medicamento) {
    this.isInfo = true;
    this.currentMedicamento = medicamento
  }

  openEditForm(medicamento: Medicamento) {
    this.isEdit = true;
    this.currentMedicamento = medicamento
  }

  openDeleteForm(medicamento: Medicamento) {
    this.isDelete = true;
    this.currentMedicamento = medicamento
  }

  closeModal() {    
    this.isAdd = false;
    this.isInfo = false;
    this.isEdit = false;
    this.isDelete = false;
    this.currentMedicamento =  {
      id : 0,
      nombre : "",
      descripcion: ""
    }
    this.medicamento = {
      id : 0,
      nombre : "",
      descripcion: ""
    }
  }
}
