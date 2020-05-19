import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient, HttpParams } from '@angular/common/http';
import { BaseService } from 'src/app/core/base.service';
import { Observable } from 'rxjs';
import { FormControl, Validators, FormBuilder, FormGroup } from '@angular/forms';
import { CustomValidators } from 'src/app/core/utils/validator/custom-validator';
import {Group} from "../../group/group";
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
    let httpParams = new HttpParams();
    httpParams.append('userName', addU.userName);
    httpParams.append('groupName', addU.groupName);
    console.log("create_chat_room");

    let param = BaseService.httpOptions();

    param.params = httpParams;

    return this.http.post<any>(UserService.BASE_URL, this.httpOptions);
  }




  update(user: User): Observable<User> {
		return this.http.put<any>(UserService.BASE_URL + '/' + user.id, User);
	}

	delete(id: number): Observable<any> {
		return this.http.delete<any>(UserService.BASE_URL + '/' + id);
	}

	getById(id: number): Observable<User> {
		return this.http.get<any>(UserService.BASE_URL + '/' + id);
	}

	getAll(params: HttpParams = new HttpParams()): Observable<any> {
		return this.http.get<any>(UserService.BASE_URL, { params: params });
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
