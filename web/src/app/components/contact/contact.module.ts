import { ModalModule } from 'ngx-bootstrap/modal';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatTableModule, MatCheckboxModule, MatPaginatorModule, MatSelectModule, MatFormFieldModule, MatInputModule,
  MatSortModule, MatGridListModule, MatCardModule } from '@angular/material';
import { UtilsModule } from 'src/app/core/utils/utils.module';
import {ContactService} from "../contact/contact.service";
import {ContactComponent} from "./contact.component";


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

    UtilsModule
  ],
  declarations: [
    ContactComponent
  ],
  exports: [
    ContactComponent
  ],
  providers: [
    ContactService,
  ]
})
export class GroupModule { }
