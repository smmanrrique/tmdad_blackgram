import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient, HttpParams } from '@angular/common/http';
import { BaseService } from 'src/app/core/base.service';
import { Observable } from 'rxjs';
import {  FormBuilder } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})

export class LoginService {

  private static readonly BASE_URL: string = BaseService.HOST + '/user';

  constructor(
    private http: HttpClient,
    private fb: FormBuilder) {}

  getAll(httpparams: HttpParams = new HttpParams()): Observable<any> {
    let parameters = BaseService.httpOptions();
    parameters.params = httpparams;
    return this.http.get<any>(LoginService.BASE_URL, parameters);
  }

}
