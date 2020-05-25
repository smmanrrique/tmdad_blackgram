import { Injectable } from '@angular/core';
import {BaseService} from "../../core/base.service";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Group} from "../group/group";
import {Observable} from "rxjs";
import {User} from "../auth/user-register/user";
import {Createcontact} from './contact';

@Injectable({
  providedIn: 'root'
})
export class ContactService {

  private static readonly BASE_URL: string = BaseService.HOST + '/contact';

  constructor(
    private http: HttpClient,
    private fb: FormBuilder) {}


  getAll(data: {}): Observable<any> {
    return this.http.get<any>(ContactService.BASE_URL, BaseService.httpAll(data));
  }


  getAddContac(contac: Createcontact): FormGroup {
    return this.fb.group({
      name: new FormControl(contac.name, [Validators.required, ]),
      owner: new FormControl(contac.owner)
    });
  }

}
