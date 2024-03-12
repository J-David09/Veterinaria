import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  urlBase = environment.urlBaseApi;

  constructor(private http: HttpClient) { }

  login (username: String , password : String): Observable<any> {
    return this.http.post<any>(environment.urlBaseApi + "login/" + username + "/" + password, {});
  }
}
