import { Injectable } from '@angular/core';
import {BaseService} from "../../core/base.service";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Group} from "../group/group";
import {Observable} from "rxjs";
import {User} from "../auth/user-register/user";

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


  // getGroup(group: Group): FormGroup {
  //   return this.fb.group({
  //     id: new FormControl(group.id),
  //     name: new FormControl(group.name, [Validators.required, Validators.maxLength(30)]),
  //     owner: new FormControl(group.owner ? group.owner.userName: null),
  //     users: new FormControl(group.users),
  //   });
  // }

}
