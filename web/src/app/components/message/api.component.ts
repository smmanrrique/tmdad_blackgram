import { MessageService } from './message.service';
import { GroupService } from './../group/group.service';
import { Command } from './../../core/models/comman';
import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { UserService } from '../auth/user-register/user.service';
import { User } from '../auth/user-register/user';
import { Group } from '../group/group';
import { Message } from './message';
import {NotificationService} from "../../core/utils/notification/notification.service";

@Component({
  // selector: 'app-message',
  templateUrl: './api.component.html',
  styleUrls: ['./message.component.css']
})

export class ApiComponent implements OnInit {

  userform: FormGroup;
  groupform: FormGroup;
  messageform: FormGroup;

  commands: Command[] = [
    { name: 'ADD_USER_TO_SYSTEM', command: 'ADD_USER_TO_SYSTEM', fields: ['username'] },
    { name: 'CREATE_CHAT_ROOM', command: 'CREATE_CHAT_ROOM', fields: ['room_name'] },
    { name: 'ADD_USER_TO_CHAT_ROOM', command: 'ADD_USER_TO_CHAT_ROOM', fields: ['username', 'room_name'] },
    { name: 'SEND_MESSAGE', command: 'SEND_MESSAGE', fields: ['from_user', 'to_user', 'msg_content'] },
    { name: 'SEND_MESSAGE_TO_ROOM', command: 'SEND_MESSAGE_TO_ROOM', fields: ['from_user', 'room_name', 'msg_content'] },
    { name: 'SEND_FILE', command: 'SEND_FILE', fields: ['from_user', 'to_user', 'content'] },
    { name: 'SEND_MESSAGE_TO_ALL', command: 'SEND_MESSAGE_TO_ALL', fields: ['msg_content'] }
  ];


  constructor(
    private userService: UserService,
    private groupService: GroupService,
    private messageService: MessageService,
    private notificationService: NotificationService
  ) { }


  ngOnInit() {
    this.userform = this.userService.getUser(new User());
    this.groupform = this.groupService.getGroup(new Group());
    this.messageform = this.messageService.getMessage(new Message());
  }

  // Function to create user
  add_user_to_system() {
    // console.log(this.form);
    // this.lotService.create(<Lot> this.form.value)
    //   .subscribe(lot => {
    //     this.notificationService.sucessInsert('Lote');
    //     this.location.back();
    //   }, err =>  {
    //     this.notificationService.error(err);
    //   });

    console.log("add_user_to_system");
    console.log(this.userform)
    this.notificationService.sucessInsert('User');
  }

  create_chat_room() {
    console.log("create_chat_room");
    console.log(this.groupform)
    this.notificationService.sucessInsert('Group');
  }

  add_user_chat_room() {
    console.log("create_chat_room");
    console.log(this.groupform)
    this.notificationService.sucessUpdate('added user to Group');
  }

  send_message() {
    console.log("send_message");
    console.log(this.messageform)
    this.notificationService.showInfo('Send Message');
  }

}
