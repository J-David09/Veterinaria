import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {
  urlBase = environment.urlBaseApi;

  constructor(private http: HttpClient) { }

  getAll (): Observable<any> {
    return this.http.get<any>(environment.urlBaseApi + "cliente/getAll", {});
  }
}
