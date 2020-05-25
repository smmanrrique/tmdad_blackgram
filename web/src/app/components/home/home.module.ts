import { MessageModule } from './../message/message.module';
import { HomeRoutingModule } from './home.routing';
import { UtilsModule } from '../../core/utils/utils.module';
import { HomeComponent } from './home.component';
import { BreadcrumbsComponent } from '../../core/breadcrumbs/breadcrumbs.component';
import { CommonModule } from '@angular/common';
import { ErrorStateMatcher, ShowOnDirtyErrorStateMatcher } from '@angular/material';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { SidebarComponent } from '../../core/sidebar/sidebar.component';
import { TopbarComponent } from '../../core/topbar/topbar.component';
import {TrendingModule} from "../trending/trending.module";
import {GroupModule} from "../group/group.module";
import {ContactModule} from '../contact/contact.module';
import {UserInformationComponent} from '../user-information/user-information.component';
import {UserInformationModule} from '../user-information/user-information.module';

@NgModule({
	imports: [
		CommonModule,
		FormsModule,
		ReactiveFormsModule,
		UtilsModule,
		MessageModule,
    GroupModule,
    TrendingModule,
    ContactModule,
    UserInformationModule,

		HomeRoutingModule,
	],
	declarations: [
		HomeComponent,
		SidebarComponent,
		TopbarComponent,
		BreadcrumbsComponent,
	],
	exports: [
		SidebarComponent,
		TopbarComponent,
		BreadcrumbsComponent,
	],
	providers: [
		{ provide: ErrorStateMatcher, useClass: ShowOnDirtyErrorStateMatcher }
	]
})
export class HomeModule { }
