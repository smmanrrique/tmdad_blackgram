import { FileUploadComponent } from './../file-upload/file-upload.component';
import { UserService } from './../user/user.service';
import { ModalModule } from 'ngx-bootstrap/modal';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
// tslint:disable-next-line:max-line-length
import { MatTableModule, MatCheckboxModule, MatPaginatorModule, MatSelectModule, MatFormFieldModule, MatInputModule, MatSortModule } from '@angular/material';
import { UtilsModule } from 'src/app/core/utils/utils.module';
import { MessageRoutingModule } from './message.routing';
import { MessageComponent } from './message.component';


@NgModule({
  imports: [
    // FileUploadModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MatTableModule,
    MatCheckboxModule,
    MatPaginatorModule,
    MatSelectModule,
    MatFormFieldModule,
    MatInputModule,
    MatSortModule,
    ModalModule.forRoot(),

    UtilsModule,
    MessageRoutingModule,
  ],
  declarations: [
    MessageComponent,
    FileUploadComponent
  ],
  exports: [
    MessageComponent,
    FileUploadComponent
  ],
  providers: [
    UserService,
  ]
})
export class MessageModule { }
