import { FileUploadService } from './file-upload.service';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';

@Component({
  selector: 'list-upload',
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
export class ListUploadComponent implements OnInit {

  showFile = false;
  fileUploads: Observable<string[]>;

  constructor(private fileUploadService: FileUploadService) { }

  ngOnInit() {
  }

  showFiles(enable: boolean) {
    this.showFile = enable;

    if (enable) {
      this.fileUploads = this.fileUploadService.getAll();
    }
  }
}
