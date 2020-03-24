import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home.component';
import { NgModule } from '@angular/core';

const homeRoutes: Routes = [
	{
		path: 'admin',
		component: HomeComponent,
		children: [
			{
				path: '',
				redirectTo: '/admin/',
				pathMatch: 'full',
				data: {
					breadcrumb: '',
					icon: '',
				}
			}

			// ADD NEW ROUTES
			// ...providerRoutes,
		],
		data: {
			breadcrumb: 'home'
		},
	},
];

@NgModule({
	imports: [
		RouterModule.forChild(homeRoutes)
	],
	exports: [
		RouterModule
	]
})
export class HomeRoutingModule { }
