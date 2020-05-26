import { RouterModule, Routes } from "@angular/router";
import { NgModule } from '@angular/core';
import {UserInformationComponent} from './user-information.component';

export const userInformationRoutes: Routes = [
  {
    path: 'info',
    component: UserInformationComponent,
    data: {
      breadcrumb: 'user/information'
    },
    children: [
      {
        path: '',
        pathMatch: 'full',
        component: UserInformationComponent
      }
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(userInformationRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class UserInformationRoutingModule { }
