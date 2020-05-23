import { ViewEncapsulation } from '@angular/core';
import { Component, OnInit, AfterViewInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { CustomValidators } from '../../../core/utils/validator/custom-validator';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from '../auth.service';
import { AuthorizationRequest } from '../../../core/models/authorizationRequest';
import { UserService } from '../../user/user.service';
import { ToastrManager } from 'ng6-toastr-notifications';
import { NotificationService } from '../../../core/utils/notification/notification.service';
import {User} from '../user-register/user';

@Component({
	selector: 'app-login',
	templateUrl: './login.component.html',
	styleUrls: ['./login.component.css'],
	encapsulation: ViewEncapsulation.None
})

export class LoginComponent implements OnInit {
	form: FormGroup;
	authorizationRequest: AuthorizationRequest;

	constructor(
		private authService: AuthService,
		private userService: UserService,
		private activatedRoute: ActivatedRoute,
		private router: Router,
		private fb: FormBuilder,
		public toastr: ToastrManager,
		private notificationService: NotificationService,
	) {
		this.form = this.getForm();
	}

	ngOnInit() {
		this.authorizationRequest = new AuthorizationRequest();
		this.authorizationRequest.grant_type = 'password';
		this.authorizationRequest.client_id = 'web_site';
		this.form = this.toFormGroup(new User());
	}

	login(): void {
		if (this.form.valid) {
			this.main(<User> this.form.value);
      console.log("")



			// this.authorizationRequest.username = this.form.value['email'];
			// this.authorizationRequest.password = this.form.value['password'];

			// this.authService.login(this.authorizationRequest).subscribe(authorizationResponse => {
			// 	sessionStorage.setItem('token', authorizationResponse.access_token);
			// 	sessionStorage.setItem('refresh_token', authorizationResponse.refresh_token);

			// 	this.userService.getByAuthUserId(authorizationResponse.user_id).subscribe(user => {
			// 		sessionStorage.setItem('user', JSON.stringify(user));
			// 		window.location.href = window.location.href + 'admin';
			// 	}, error => {
			// 		let err = error.json();
			// 		// console.log(error);
			// 	});
			// },
			// 	err => {
			// 		console.log(err);
			// 		this.notificationService.error(err);
			// 	});
		}
	}

	toFormGroup(user: User): FormGroup {
		return this.fb.group({
			userName: new FormControl(user.userName, [Validators.required, Validators.maxLength(30)]),
			password: new FormControl(user.password)
			// password: new FormControl('', Validators.required)
		});
	}

	main(user: User) {
		console.log('estoy log');
    sessionStorage.setItem('userSession',user.userName );
    console.log(sessionStorage.getItem('userSession'))
		this.router.navigate(['./admin'], { relativeTo: this.activatedRoute });
	}

	getForm(): FormGroup {
		return this.fb.group({
			userName: new FormControl('', [Validators.required, Validators.maxLength(30)]),
			password: new FormControl('', Validators.required)
		});
	}

	create() {
		this.router.navigate(['./admin'], { relativeTo: this.activatedRoute });
	}

	register() {
		this.router.navigate(['./reg'], { relativeTo: this.activatedRoute });
	}
}
