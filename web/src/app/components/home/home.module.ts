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

@NgModule({
	imports: [
		CommonModule,
		FormsModule,
		ReactiveFormsModule,
		UtilsModule,

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
