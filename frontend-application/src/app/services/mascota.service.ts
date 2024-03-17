import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { environment } from '../environments/environment';
import { Mascota } from '../interfaces/mascota';


@Injectable({
  providedIn: 'root'
})
export class MascotaService {

  constructor(private http: HttpClient) { }

  getAll (): Observable<any> {
    return this.http.get<any>(environment.urlBaseApi + "mascota/getAll", {});
  }

  editUser (id: number, mascota:Mascota): Observable<any> {
    return this.http.post<any>(environment.urlBaseApi + "mascota/update/" + id, mascota);
  }

  addUser (mascota:Mascota): Observable<any> {
    return this.http.post<any>(environment.urlBaseApi + "mascota/create", mascota);
  }

  delete (id: number):  Observable<any> {
    return this.http.delete<any>(environment.urlBaseApi + "mascota/delete/" + id);
  }

  getOwner (id: number):  Observable<any> {
    return this.http.get<any>(environment.urlBaseApi + "cliente/getOne/" + id);
  }
}
