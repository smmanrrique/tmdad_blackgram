import { HttpClient, HttpEvent, HttpErrorResponse, HttpEventType } from '@angular/common/http';
import { throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { OnInit, Component } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { FileUploadService } from './file-upload.service';
import { FileUploader } from 'ng2-file-upload';

const uri = 'http://localhost:3000/file/upload';

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.css']
})

export class FileUploadComponent {

  uploader: FileUploader = new FileUploader({ url: uri });

  attachmentList: any = [];

  constructor(
    // private _fileService: FileService
  ) {

    this.uploader.onCompleteItem = (item: any, response: any, status: any, headers: any) => {
      this.attachmentList.push(JSON.parse(response));
    }
  }



  // download(index) {
  //   var filename = this.attachmentList[index].uploadname;

  //   this._fileService.downloadFile(filename)
  //     .subscribe(
  //       data => saveAs(data, filename),
  //       error => console.error(error)
  //     );
  // }

}