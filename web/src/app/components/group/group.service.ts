import { Injectable } from '@angular/core';
import { Group } from './group';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {User} from "../auth/user-register/user";
import {Observable} from "rxjs";
import {BaseService} from "../../core/base.service";

@Injectable({
  providedIn: 'root'
})
export class GroupService {

  private static readonly BASE_URL: string = BaseService.HOST + '/group';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      observe: 'response',
    }),
    params:{}
  };

  constructor(
    private http: HttpClient,
    private fb: FormBuilder) {}

  create(group: Group,params: HttpParams = new HttpParams()): Observable<User> {
    this.httpOptions.params = params;
    console.log(this.httpOptions)
    console.log(group);
    return this.http.post<any>(GroupService.BASE_URL, JSON.stringify(group), this.httpOptions);
  }

  // getAll(params: HttpParams = new HttpParams()): Observable<any> {
  //   return this.http.get<any>(ProviderService.BASE_URL, {params: params});
  // }


  getGroup(group: Group): FormGroup {
    return this.fb.group({
      id: new FormControl(group.id),
      name: new FormControl(group.name, [Validators.required, Validators.maxLength(30)]),
      owner: new FormControl(group.owner ? group.owner.userName: null),
      users: new FormControl(group.users),
    });
  }



}
