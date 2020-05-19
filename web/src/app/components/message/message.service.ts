import { Topic } from './topic';
import { Message } from './message';
import {HttpClient} from '@angular/common/http';
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
    console.log(message)
    // Todo procesar topics
    return this.http.post<any>(MessageService.BASE_URL+'/send', JSON.stringify(message), BaseService.httpOptions());
  }

  sendMessageGroup(message: Message): Observable<any> {
    console.log(message)
    // Todo procesar topics
    return this.http.post<any>(MessageService.BASE_URL+'/sendGroup', JSON.stringify(message), BaseService.httpOptions());
  }

  sendMessageBroadcast(message: Message): Observable<any> {
    // Todo procesar topics
    return this.http.post<any>(MessageService.BASE_URL+'/sendBroadcast', JSON.stringify(message), BaseService.httpOptions());
  }

  getMessage(message: Message): FormGroup {
    return this.fb.group({
      toUser: new FormControl(message.toUser),
      fromUser: new FormControl(message.fromUser),
      toGroup: new FormControl(message.toGroup),
      body: new FormControl(message.body),
      multimedia: new FormControl(message.multimedia),
      topics: new FormControl(message.topics),
    });
  }

  initTopic(topic: Topic): FormGroup {
    return this.fb.group({
      id: new FormControl(topic.id, Validators.required),
      name: new FormControl(topic.name, Validators.required)
    });
  }

}
