import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { NgModule } from '@angular/core';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { UserRegisterComponent } from './user-register/user-register.component';

const authRoutes: Routes = [
	{
		path: '',
		pathMatch: 'full',
		component: LoginComponent
	},
	{
		path: 'identify',
		component: ChangePasswordComponent
	},
	{
		path: 'reg',
		component: UserRegisterComponent
	}
];
// export const loginRouting = RouterModule.forRoot(loginRoutes);

@NgModule({
	imports: [
		RouterModule.forChild(authRoutes)
	],
	exports: [
		RouterModule
	]
})
export class AuthRoutingModule { }
