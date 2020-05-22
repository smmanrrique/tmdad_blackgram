import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpErrorResponse, HttpEventType, HttpRequest } from '@angular/common/http';
import { throwError, Observable } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { OnInit } from '@angular/core';
import { BaseService } from 'src/app/core/base.service';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class FileUploadService {

  private static readonly BASE_URL: string = environment.fileUrl + '/uploadFile';

  constructor(private http: HttpClient) { }

  pushFileToStorage(form: FormData): Observable<HttpEvent<{}>> {
    const req = new HttpRequest('POST', FileUploadService.BASE_URL, form, {
      reportProgress: true,
      responseType: 'text'
    });

    return this.http.request(req);
  }

  getAll(): Observable<any> {
    return this.http.get(FileUploadService.BASE_URL);
  }
}
