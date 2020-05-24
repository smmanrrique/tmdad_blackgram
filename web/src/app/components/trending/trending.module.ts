import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {ModalModule} from "ngx-bootstrap/modal";
import {UtilsModule} from "../../core/utils/utils.module";
import {UserService} from "../user/user.service";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatCheckboxModule} from "@angular/material/checkbox";
import {MatTableModule} from "@angular/material/table";
import {MatGridListModule} from "@angular/material/grid-list";
import {MatSelectModule} from "@angular/material/select";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatSortModule} from "@angular/material/sort";
import {MatCardModule} from "@angular/material/card";
import {TrendingComponent} from "./trending.component";
import {TrendingService} from "./trending.service";
import {TrendingRoutingModule} from "./trending.routing";
import {ChartsModule} from 'ng2-charts';

@NgModule({
  imports: [
    ChartsModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MatTableModule,
    MatCheckboxModule,
    MatPaginatorModule,
    MatGridListModule,
    MatCardModule,
    MatSelectModule,
    MatFormFieldModule,
    MatInputModule,
    MatSortModule,
    ModalModule.forRoot(),

    UtilsModule,
    TrendingRoutingModule,
  ],
  declarations: [
    TrendingComponent,
  ],
  exports: [
    TrendingComponent,
  ],
  providers: [
    UserService,
    TrendingService,
  ]
})
export class TrendingModule { }
