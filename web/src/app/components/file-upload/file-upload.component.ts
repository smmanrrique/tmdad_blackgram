import { FileUploadService } from './file-upload.service';

import { HttpClient, HttpEvent, HttpErrorResponse, HttpEventType, HttpResponse } from '@angular/common/http';
import { OnInit, Component } from '@angular/core';

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.css']
})

export class FileUploadComponent {

  selectedFiles: FileList;
  currentFileUpload: File;
  progress: { percentage: number } = { percentage: 0 };

  constructor(private fileUploadService: FileUploadService) { }

  ngOnInit() {
  }

  selectFile(event) {
    this.selectedFiles = event.target.files;
  }

  upload() {
    this.progress.percentage = 0;

    this.currentFileUpload = this.selectedFiles.item(0);

    const formfile: FormData = new FormData();
    formfile.append('file', this.currentFileUpload);
    // TODO Change by userName
    formfile.append('user', "UserName");

    this.fileUploadService.pushFileToStorage(formfile).subscribe(event => {
      if (event.type === HttpEventType.UploadProgress) {
        this.progress.percentage = Math.round(100 * event.loaded / event.total);
      } else if (event instanceof HttpResponse) {
        console.log('File is completely uploaded!');
      }
    });

    this.selectedFiles = undefined;
  }


}