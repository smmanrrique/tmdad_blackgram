import { UserService } from './../user/user.service';
import { AuthRoutingModule } from './auth.routing';
import { UtilsModule } from '../../core/utils/utils.module';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './login/login.component';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import {MatInputModule, MatFormFieldModule, MatSlideToggleModule} from '@angular/material';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { ToastrModule } from 'ng6-toastr-notifications';
import { UserRegisterComponent } from './user-register/user-register.component';
import { UtilService } from 'mydatepicker/dist';

@NgModule({
  imports: [
    HttpClientModule,
    FormsModule,
    RouterModule,
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    ToastrModule.forRoot(),

    UtilsModule,
    AuthRoutingModule,
    MatSlideToggleModule,
  ],
	declarations: [
		LoginComponent,
		ChangePasswordComponent,
		UserRegisterComponent,
	],
	exports: [
		LoginComponent,
		ChangePasswordComponent,
		UserRegisterComponent,
	],
	providers: [
		UtilService,
		UserService,
	  ]
})

export class AuthModule { }
