import { Injectable } from '@angular/core';
import {BaseService} from '../../core/base.service';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {User} from "../auth/user-register/user";
import {Observable} from "rxjs";
import {FormBuilder} from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class TrendingService {

  private static readonly BASE_URL: string = BaseService.HOST + '/topic';

  constructor(
    private http: HttpClient,
    private fb: FormBuilder) {}

  getAll(): Observable<any> {
    return this.http.get<any>(TrendingService.BASE_URL, BaseService.httpOptions());
  }

  FindTopTopics(): Observable<any> {
    return this.http.get<any>(TrendingService.BASE_URL+"/top", BaseService.httpOptions());
  }

  FindRecebtTopics(): Observable<any> {
    return this.http.get<any>(TrendingService.BASE_URL+"/topfive", BaseService.httpOptions());
  }

  FindUserFromTopics(): Observable<any> {
    return this.http.get<any>(TrendingService.BASE_URL+"/userfrom", BaseService.httpOptions());
  }

  FindUserTopics(): Observable<any> {
    return this.http.get<any>(TrendingService.BASE_URL+"/user", BaseService.httpOptions());
  }

  FindTimeTopics(): Observable<any> {
    return this.http.get<any>(TrendingService.BASE_URL+"/time", BaseService.httpOptions());
  }

  FindRealTimeTopics(): Observable<any> {
    return this.http.get<any>(TrendingService.BASE_URL+"/realtime", BaseService.httpOptions());
  }

}
