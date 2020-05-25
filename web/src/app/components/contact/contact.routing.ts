import { RouterModule, Routes } from "@angular/router";
import { NgModule } from '@angular/core';
import {ContactComponent} from './contact.component';

export const contacRoutes: Routes = [
  {
    path: 'contac',
    component: ContactComponent,
    data: {
      breadcrumb: 'contacts'
    },
    children: [
      {
        path: '',
        pathMatch: 'full',
        component: ContactComponent
      }
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(contacRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class ContacRoutingModule { }
