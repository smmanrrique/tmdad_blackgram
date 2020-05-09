import { RouterModule, Routes } from "@angular/router";
import { NgModule } from '@angular/core';
import {GroupComponent} from "./group.component";

export const groupRoutes: Routes = [
  {
    path: 'groups',
    component: GroupComponent,
    data: {
      breadcrumb: 'Groups'
    },
    children: [
      {
        path: '',
        pathMatch: 'full',
        component: GroupComponent
      }
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(groupRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class GroupRoutingModule { }
