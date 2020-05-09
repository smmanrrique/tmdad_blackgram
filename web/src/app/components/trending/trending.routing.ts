import { RouterModule, Routes } from "@angular/router";
import { NgModule } from '@angular/core';
import {TrendingComponent} from "./trending.component";

export const trendingRoutes: Routes = [
  {
    path: 'trendings',
    component: TrendingComponent,
    data: {
      breadcrumb: 'Trendings'
    },
    children: [
      {
        path: '',
        pathMatch: 'full',
        component: TrendingComponent
      }
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(trendingRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class TrendingRoutingModule { }
