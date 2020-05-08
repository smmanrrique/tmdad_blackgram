import { FileUploadService } from './../file-upload/file-upload.service';
import { DetailsUploadComponent } from './../file-upload/details-upload.component';

import { UserService } from './../user/user.service';
import { ModalModule } from 'ngx-bootstrap/modal';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
// tslint:disable-next-line:max-line-length
import { MatTableModule, MatCheckboxModule, MatPaginatorModule, MatSelectModule, MatFormFieldModule, MatInputModule, MatSortModule, MatGridListModule, MatCardModule } from '@angular/material';
import { UtilsModule } from 'src/app/core/utils/utils.module';
import { MessageRoutingModule } from './message.routing';
import { MessageComponent } from './message.component';
import { ListUploadComponent } from '../file-upload/list-upload.component';
import { FileUploadComponent } from '../file-upload/file-upload.component';
import {ChatComponent} from "./chat.component";


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
    MessageRoutingModule,
  ],
  declarations: [
    ChatComponent,
    MessageComponent,
    FileUploadComponent,
    ListUploadComponent,
    DetailsUploadComponent,
  ],
  exports: [
    ChatComponent,
    MessageComponent,
    FileUploadComponent,
    ListUploadComponent,
    DetailsUploadComponent,
  ],
  providers: [
    UserService,
    FileUploadService,
  ]
})
export class MessageModule { }
