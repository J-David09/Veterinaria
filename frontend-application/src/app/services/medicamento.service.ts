import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { environment } from '../environments/environment';
import { Mascota } from '../interfaces/mascota';
import { Medicamento } from '../interfaces/medicamento';
import { MascMed } from '../interfaces/masc-med';

@Injectable({
  providedIn: 'root'
})
export class MedicamentoService {

  constructor(private http: HttpClient) { }

  getAll (): Observable<any> {
    return this.http.get<any>(environment.urlBaseApi + "medicamento/getAll", {});
  }

  editMedicamento (id: number, medicamento:Medicamento): Observable<any> {
    return this.http.post<any>(environment.urlBaseApi + "medicamento/update/" + id, medicamento);
  }

  addMedicamento (medicamento:Medicamento): Observable<any> {
    return this.http.post<any>(environment.urlBaseApi + "medicamento/create", medicamento);
  }

  deleteMedicamento (id: number):  Observable<any> {
    return this.http.delete<any>(environment.urlBaseApi + "medicamento/delete/" + id);
  }

  addMascMed (mascMed: MascMed): Observable<any> {
    return this.http.post<any>(environment.urlBaseApi + "mascmed/create", mascMed);
  }

}
