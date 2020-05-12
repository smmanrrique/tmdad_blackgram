import { ModalModule } from 'ngx-bootstrap/modal';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatTableModule, MatCheckboxModule, MatPaginatorModule, MatSelectModule, MatFormFieldModule, MatInputModule,
  MatSortModule, MatGridListModule, MatCardModule } from '@angular/material';
import { UtilsModule } from 'src/app/core/utils/utils.module';
import {GroupRoutingModule} from "./group.routing";
import {GroupComponent} from "./group.component";
import {GroupService} from "./group.service";
import {UtilService} from "mydatepicker/dist";


@NgModule({
  imports: [
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
    GroupRoutingModule
  ],
  declarations: [
    GroupComponent
  ],
  exports: [
    GroupComponent
  ],
  providers: [
    GroupService,
    UtilService,
  ]
})
export class GroupModule { }
