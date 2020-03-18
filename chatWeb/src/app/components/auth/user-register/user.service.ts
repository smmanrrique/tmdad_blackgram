import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient, HttpParams } from '@angular/common/http';
import { JwtHelperService } from '@auth0/angular-jwt';
import { User } from 'src/app/core/models/user';
import { BaseService } from 'src/app/core/base.service';
import { Observable } from 'rxjs';
import { FormControl } from '@angular/forms';

@Injectable({
	providedIn: 'root'
})
export class UserService {


	private static readonly BASE_URL: string = BaseService.HOST + '/user';

	private jwtHelper = new JwtHelperService();
	private headers: HttpHeaders;

	constructor(private http: HttpClient) {
		this.headers = new HttpHeaders();
		this.headers.set('Content-Type', 'application/x-www-form-urlencoded');
	}

	create(user: User): Observable<User> {
		return this.http.post<any>(UserService.BASE_URL, User);
	}


	update(User: User): Observable<User> {
		return this.http.put<any>(UserService.BASE_URL + '/' + User.id, User);
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

	getUser(User: User): FormGroup {
		return this.fb.group({
			id: new FormControl(User.id),
			UserType: new FormControl(User.UserType ? User.UserType.id : undefined, Validators.required),
			statusUser: new FormControl(User.statusUser ? User.statusUser.id : undefined, Validators.required),
			nitUser: new FormControl(User.nitUser, [Validators.required, Validators.maxLength(100)]),
			nameUser: new FormControl(User.nameUser, [Validators.required, Validators.maxLength(60)]),
			addressUser: new FormControl(User.addressUser, [Validators.required, Validators.maxLength(60)]),
			numberUser: new FormControl(User.numberUser, [CustomValidators.numberRegex, Validators.maxLength(20)]),
			emailUser: new FormControl(User.emailUser, [CustomValidators.emailRegex]),
			contactNameUser: new FormControl(User.contactNameUser, [Validators.required, Validators.maxLength(50)]),
			// invoices: new FormControl(User.emailUser, [Validators.required, Validators.maxLength(100)]),
		});
	}

}
