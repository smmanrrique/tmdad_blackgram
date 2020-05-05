import { Topic } from './topic';
import { Message } from './message';
import { HttpClient } from '@angular/common/http';
import { BaseService } from 'src/app/core/base.service';
import { Injectable } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  private static readonly BASE_URL: string = BaseService.HOST + '/message';

  constructor(
    private http: HttpClient,
    private fb: FormBuilder
  ) { }

  getMessage(message: Message): FormGroup {
    return this.fb.group({
      id: new FormControl(message.id),
      toUser: new FormControl(message.toUser, Validators.required),
      fromUser: new FormControl(message.fromUser, Validators.required),
      group_message: new FormControl(message.group_message, Validators.required),
      multimedia: new FormControl(message.multimedia ? message.multimedia.url : undefined),
      topics: this.fb.array([
        this.initTopic(new Topic())
      ])
    });
  }

  initTopic(topic: Topic): FormGroup {
    return this.fb.group({
      id: new FormControl(topic.id, Validators.required),
      name: new FormControl(topic.name, Validators.required),
      description: new FormControl(topic.description),
    });
  }

}
