
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
import { FormUploadComponent } from '../upload/form-upload/form-upload.component';
import { ListUploadComponent } from '../upload/list-upload/list-upload.component';
import { DetailsUploadComponent } from '../upload/details-upload/details-upload.component';
import { UploadFileService } from '../upload/upload-file.service';


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
    // FileUploadComponent,
    // FormUploadComponent,
    ListUploadComponent,
    DetailsUploadComponent,
    FormUploadComponent,
  ],
  exports: [
    MessageComponent,
    // FileUploadComponent,
    // FormUploadComponent,
    // ListUploadComponent,
    // DetailsUploadComponent,
    ListUploadComponent,
    DetailsUploadComponent,
    FormUploadComponent,
  ],
  providers: [
    UserService,
    UploadFileService,
  ]
})
export class MessageModule { }
