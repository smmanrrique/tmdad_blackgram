import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home.component';
import { NgModule } from '@angular/core';
import { messageRoutes } from '../message/message.routing';
import {trendingRoutes} from "../trending/trending.routing";
import {groupRoutes} from "../group/group.routing";

const homeRoutes: Routes = [
	{
		path: 'admin',
		component: HomeComponent,
		children: [
			{
				path: '',
				redirectTo: '/admin/messages',
				pathMatch: 'full',
				data: {
					breadcrumb: '',
					icon: '',
					expectedRoles: ['super', 'employee']
				}
			},
			...messageRoutes,
      ...groupRoutes,
      ...trendingRoutes,

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