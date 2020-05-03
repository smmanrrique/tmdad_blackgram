import { Component, OnInit } from '@angular/core';
import { UploadFileService } from './upload-file.service';
import { HttpResponse, HttpEventType } from '@angular/common/http';

@Component({
  selector: 'form-upload',
  template: `
    <button class="button btn-info" *ngIf='showFile' (click)='showFiles(false)'>Hide Files</button>

    <button class="button btn-info" *ngIf='!showFile' (click)='showFiles(true)'>Show Files</button>

    <div [hidden]="!showFile">
      <div class="panel panel-primary">
        <div class="panel-heading">List of Files</div>
        <div *ngFor="let file of fileUploads | async">
          <div class="panel-body">
            <details-upload [fileUpload]='file'></details-upload>
          </div>
        </div>
      </div>
    </div>  
  `,
  styleUrls: ['./file-upload.component.css']
})
export class FormUploadComponent implements OnInit {

  selectedFiles: FileList;
  currentFileUpload: File;
  progress: { percentage: number } = { percentage: 0 };

  constructor(
    private uploadService: UploadFileService,
    private uploadFileService: UploadFileService,

  ) { }

  ngOnInit() {
  }

  selectFile(event) {
    this.selectedFiles = event.target.files;
  }

  upload() {
    this.progress.percentage = 0;

    this.currentFileUpload = this.selectedFiles.item(0);
    this.uploadService.pushFileToStorage(this.currentFileUpload).subscribe(event => {
      if (event.type === HttpEventType.UploadProgress) {
        this.progress.percentage = Math.round(100 * event.loaded / event.total);
      } else if (event instanceof HttpResponse) {
        console.log('File is completely uploaded!');
      }
    });

    // this.uploadFileService.pushFileToStorage(this.currentFileUpload).subscribe(event => {
    //   if (event.type === HttpEventType.UploadProgress) {
    //     this.progress.percentage = Math.round(100 * event.loaded / event.total);
    //   } else if (event instanceof HttpResponse) {
    //     console.log('File is completely uploaded!');
    //   }
    // });

    this.selectedFiles = undefined;
  }

}
