import { Message } from './message';
import {HttpClient, HttpParams} from '@angular/common/http';
import { BaseService } from 'src/app/core/base.service';
import { Injectable } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  private static readonly BASE_URL: string = BaseService.HOST + '/message';

  constructor(
    private http: HttpClient,
    private fb: FormBuilder
  ) { }

  sendMessage(message: Message): Observable<any> {
    // Getting hashtags
    let topics = BaseService.topicBody(message.body);

    let url = BaseService.urlBody(message.body)
    console.log("topics", topics)
    console.log("url", url)
    message.topics = topics
    console.log(message)
    return this.http.post<any>(MessageService.BASE_URL+'/send', JSON.stringify(message), BaseService.httpOptions());
  }

  sendMessageGroup(message: Message): Observable<any> {
    // Getting hashtags
    message.topics = BaseService.topicBody(message.body);
    console.log(message)
    return this.http.post<any>(MessageService.BASE_URL+'/sendGroup', JSON.stringify(message), BaseService.httpOptions());
  }

  sendMessageBroadcast(message: Message): Observable<any> {
    // Getting hashtags
    message.topics = BaseService.topicBody(message.body);
    message.toUser = "BROADCAST";
    console.log(message)
    return this.http.post<any>(MessageService.BASE_URL+'/sendBroadcast', JSON.stringify(message), BaseService.httpOptions());
  }

  getAll(params: HttpParams = new HttpParams()): Observable<any> {
    let parameters = BaseService.httpOptions();
    parameters.params = params;
    return this.http.get<any>(MessageService.BASE_URL, parameters);
  }

  getMessage(message: Message): FormGroup {
    return this.fb.group({
      toUser: new FormControl(message.toUser),
      fromUser: new FormControl(message.fromUser),
      toGroup: new FormControl(message.toGroup),
      body: new FormControl(message.body, [Validators.required]),
      multimedia: new FormControl(message.multimedia),
      topics: new FormControl(message.topics),
    });
  }

  getChatUserMessage(message: Message): FormGroup {
    return this.fb.group({
      toUser: new FormControl(message.toUser),
      fromUser: new FormControl(message.fromUser),
      toGroup: new FormControl(message.toGroup),
      body: new FormControl(message.body, [Validators.required]),
      multimedia: new FormControl(message.multimedia),
      topics: new FormControl(message.topics),
    });
  }

  getChatGroupMessage(message: Message): FormGroup {
    return this.fb.group({
      toUser: new FormControl(message.toUser),
      fromUser: new FormControl(message.fromUser),
      toGroup: new FormControl(message.toGroup, ),
      body: new FormControl(message.body, [Validators.required]),
      multimedia: new FormControl(message.multimedia),
      topics: new FormControl(message.topics),
    });
  }

  getUserMessage(message: Message): FormGroup {
    return this.fb.group({
      toUser: new FormControl(message.toUser, [Validators.required, Validators.maxLength(30)]),
      fromUser: new FormControl(message.fromUser),
      toGroup: new FormControl(message.toGroup),
      body: new FormControl(message.body, [Validators.required]),
      multimedia: new FormControl(message.multimedia),
      topics: new FormControl(message.topics),
    });
  }

  getGroupMessage(message: Message): FormGroup {
    return this.fb.group({
      toUser: new FormControl(message.toUser),
      fromUser: new FormControl(message.fromUser),
      toGroup: new FormControl(message.toGroup,[Validators.required, Validators.maxLength(30)]),
      body: new FormControl(message.body, [Validators.required]),
      multimedia: new FormControl(message.multimedia),
      topics: new FormControl(message.topics),
    });
  }

  getFileMessage(message: Message): FormGroup {
    return this.fb.group({
      toUser: new FormControl(message.toUser,[Validators.required, Validators.maxLength(30)]),
      fromUser: new FormControl(message.fromUser),
      toGroup: new FormControl(message.toGroup),
      body: new FormControl(message.body, [Validators.required]),
      multimedia: new FormControl(message.multimedia, [Validators.required]),
      topics: new FormControl(message.topics),
    });
  }

}
