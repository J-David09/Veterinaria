import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { environment } from '../environments/environment';
import { Cliente } from '../interfaces/cliente';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {
  urlBase = environment.urlBaseApi;

  constructor(private http: HttpClient) { }

  getAll (): Observable<any> {
    return this.http.get<any>(environment.urlBaseApi + "cliente/getAll", {});
  }

  editUser (id: number, cliente:Cliente): Observable<any> {
    return this.http.post<any>(environment.urlBaseApi + "cliente/update/" + id, cliente);
  }

  addUser (cliente:Cliente): Observable<any> {
    return this.http.post<any>(environment.urlBaseApi + "cliente/create", cliente);
  }

  delete (id: number):  Observable<any> {
    return this.http.delete<any>(environment.urlBaseApi + "cliente/delete/" + id);
  }
}
