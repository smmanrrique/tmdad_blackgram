import { ViewEncapsulation } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from '../auth.service';
import { AuthorizationRequest } from '../../../core/models/authorizationRequest';
import { ToastrManager } from 'ng6-toastr-notifications';
import { NotificationService } from '../../../core/utils/notification/notification.service';
import {User} from '../user-register/user';
import { BaseService } from 'src/app/core/base.service';
import {LoginService} from './login.service';

@Component({
	selector: 'app-login',
	templateUrl: './login.component.html',
	styleUrls: ['./login.component.css'],
	encapsulation: ViewEncapsulation.None
})

export class LoginComponent implements OnInit {
	userSession: User;
	form: FormGroup;
	authorizationRequest: AuthorizationRequest;

	constructor(
		private authService: AuthService,
		private loginService: LoginService,
		private activatedRoute: ActivatedRoute,
		private router: Router,
		private fb: FormBuilder,
		public toastr: ToastrManager,
		private notificationService: NotificationService,
	) {
		this.form = this.getForm(new User());
	}

	ngOnInit() {
		this.authorizationRequest = new AuthorizationRequest();
		this.authorizationRequest.grant_type = 'password';
		this.authorizationRequest.client_id = 'web_site';
		this.form = this.getForm(new User());
	}

	login(formUse: FormGroup): void {

			let params = BaseService.jsonToHttpParams({
        userName: this.form.value['userName'],
				password: this.form.value['password']
			});

			this.loginService.getAll(params).subscribe(
				data => {
				  let users = data;
					if(data.length !== 0){
            this.userSession = <User> users[0];
            this.main(<User> this.userSession);
          }else{
            this.notificationService.showInfo("Credentialess Incorrectas");

          }
        }, err =>  {
          this.notificationService.error(err);
        });

	}

	main(user: User) {
    sessionStorage.setItem('user', JSON.stringify(user));
		this.router.navigate(['./admin'], { relativeTo: this.activatedRoute });
	}

	getForm(user: User): FormGroup {
		return this.fb.group({
			userName: new FormControl(user.userName, [Validators.required, Validators.maxLength(30)]),
			password: new FormControl(user.password, Validators.required)
		});
	}

	create() {
		this.router.navigate(['./admin'], { relativeTo: this.activatedRoute });
	}

	register() {
		this.router.navigate(['./reg'], { relativeTo: this.activatedRoute });
	}
}
