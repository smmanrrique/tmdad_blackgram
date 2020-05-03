import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient, HttpParams } from '@angular/common/http';
import { JwtHelperService } from '@auth0/angular-jwt';
import { BaseService } from 'src/app/core/base.service';
import { Observable } from 'rxjs';
import { FormControl, Validators, FormBuilder, FormGroup } from '@angular/forms';
import { CustomValidators } from 'src/app/core/utils/validator/custom-validator';
import { User } from './user';

@Injectable({
	providedIn: 'root'
})
export class UserService {


	private static readonly BASE_URL: string = BaseService.HOST + '/user';

	private jwtHelper = new JwtHelperService();
	private headers: HttpHeaders;

	constructor(
		private http: HttpClient,
		private fb: FormBuilder) {
		this.headers = new HttpHeaders();
		this.headers.set('Content-Type', 'application/x-www-form-urlencoded');
	}

	create(user: User): Observable<User> {
		return this.http.post<any>(UserService.BASE_URL, User);
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
			id: new FormControl(user.id),
			userName: new FormControl(user.userName, [Validators.required, Validators.maxLength(30)]),
			firstName: new FormControl(user.firstName, [Validators.maxLength(50)]),
			lastName: new FormControl(user.lastName, [Validators.maxLength(50)]),
			email: new FormControl(user.email, [CustomValidators.emailRegex]),
			password: new FormControl(user.password, [Validators.maxLength(50)]),
		});
	}

}
