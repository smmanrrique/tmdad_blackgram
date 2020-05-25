import { Injectable } from '@angular/core';
import {BaseService} from "../../core/base.service";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Group} from "../group/group";
import {Observable} from "rxjs";
import {Contact, CreateContact} from './contact';
import {User} from '../auth/user-register/user';

@Injectable({
  providedIn: 'root'
})
export class ContactService {

  private static readonly BASE_URL: string = BaseService.HOST + '/contact';

  constructor(
    private http: HttpClient,
    private fb: FormBuilder) {}

  create(contact: Contact, data: {}): Observable<any> {
    return this.http.post<any>(ContactService.BASE_URL, JSON.stringify(contact),BaseService.httpAll(data));
  }

  getAllByUser(data: {}): Observable<any> {
    return this.http.get<any>(ContactService.BASE_URL, BaseService.httpAll(data));
  }

  getAll(): Observable<any> {
    return this.http.get<any>(ContactService.BASE_URL+'/all', BaseService.httpOptions());
  }

  getAddContac(contac: CreateContact): FormGroup {
    return this.fb.group({
      name: new FormControl(contac.name, [Validators.required]),
      user: new FormControl(contac.owner)
    });
  }

}
