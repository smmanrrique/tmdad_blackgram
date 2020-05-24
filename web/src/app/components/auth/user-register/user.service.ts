import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient, HttpParams } from '@angular/common/http';
import { BaseService } from 'src/app/core/base.service';
import { Observable } from 'rxjs';
import { FormControl, Validators, FormBuilder, FormGroup } from '@angular/forms';
import { CustomValidators } from 'src/app/core/utils/validator/custom-validator';
import {AddUserGroup, User} from './user';

@Injectable({
	providedIn: 'root'
})
export class UserService {

	private static readonly BASE_URL: string = BaseService.HOST + '/user';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      observe: 'response',
    })
  };

	constructor(
		private http: HttpClient,
		private fb: FormBuilder) {}

	create(user: User): Observable<User> {
	  if (user.password == null){
      user.password = user.userName;
    }
    console.log(user);
    return this.http.post<any>(UserService.BASE_URL, JSON.stringify(user), this.httpOptions);
	}

  addUserToGroup(addU: AddUserGroup): Observable<User> {
    console.log(addU);

    let parameters = BaseService.httpOptions();
    parameters.params =  BaseService.jsonToHttpParams({
      userName: addU.userName,
      groupName: addU.groupName,
    });

    return this.http.post<any>(UserService.BASE_URL, parameters);
  }


  update(user: User): Observable<User> {
		return this.http.put<any>(UserService.BASE_URL + '/' + user.id, User);
	}

	delete(id: number): Observable<any> {
		return this.http.delete<any>(UserService.BASE_URL + '/' + id);
	}

	getById(id: number): Observable<User> {
	  console.log(UserService.BASE_URL + '/' + id)
		return this.http.get<User>(UserService.BASE_URL + '/' + id);
	}

	getAll(httpparams: HttpParams = new HttpParams()): Observable<any> {
		let parameters = BaseService.httpOptions();
    	parameters.params = httpparams;
		return this.http.get<any>(UserService.BASE_URL, parameters);
	}

	findAll(httpparams: HttpParams = new HttpParams()): Observable<any> {
		let parameters = BaseService.httpOptions();
    	parameters.params = httpparams;
		return this.http.get<any>(UserService.BASE_URL, parameters);
	}

	getUser(user: User): FormGroup {
    return this.fb.group({
			userName: new FormControl(user.userName, [Validators.required, Validators.maxLength(30)]),
      password: new FormControl(user.password, [Validators.maxLength(50)]),
			firstName: new FormControl(user.firstName, [Validators.maxLength(50)]),
			lastName: new FormControl(user.lastName, [Validators.maxLength(50)]),
			email: new FormControl(user.email, [CustomValidators.emailRegex]),
      admin: new FormControl(user.admin ),
      myGroup: new FormControl(user.myGroups )
		});
	}

  AddUserGroup(user: AddUserGroup): FormGroup {
    return this.fb.group({
			userName: new FormControl(user.userName, [Validators.required, Validators.maxLength(30)]),
      groupName: new FormControl(user.groupName, [Validators.required, Validators.maxLength(30)])
		});
	}

}
