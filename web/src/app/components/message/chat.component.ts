import { Component, OnInit } from '@angular/core';
import {AddUserGroup, User} from '../auth/user-register/user';
import { Group } from '../group/group';
import {UserService} from "../auth/user-register/user.service";
import {GroupService} from "../group/group.service";
import {MessageService} from "./message.service";
import {NotificationService} from "../../core/utils/notification/notification.service";
import {Message, MessageList} from "./message";
import {HttpParams} from "@angular/common/http";
import {ContactService} from "../contact/contact.service";

@Component({
  // selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./message.component.css']
  // styleUrls: ['../group/group.component.css']
})

export class ChatComponent implements OnInit {

  user: User;
  messages: MessageList[];
  selectedGroup: Group;

  constructor(
    private userService: UserService,
    private groupService: GroupService,
    private messageService: MessageService,
    private contactService: ContactService,
    private notificationService: NotificationService
  ) { }

  ngOnInit() {

    // let paramsMessage = new HttpParams();
    // // httpParams = httpParams.append('userId', String(1));
    //
    // this.messageService.getAll(paramsMessage).subscribe(
    //   data => {
    //     this.messages = data['result'];
    //     console.log(this.messages)
    //   });

    // // TODO Get ID USER FROM URL
    // let paramsUser = new HttpParams();
    // this.userService.getById(1).subscribe(
    //   data => {
    //     this.user = data['result'];
    //     console.log(this.messages)});
    //
    // // TODO Get ID USER FROM URL
    // let paramsContact = new HttpParams();
    // paramsContact.set("userId", String(this.user.id))
    //
    // this.contactService.getAll(paramsContact).subscribe(
    //   data => {
    //     this.user = data['result'];
    //     console.log(this.messages)});

  }


  onSelect(group: Group): void {
    this.selectedGroup = group;
  }

}
