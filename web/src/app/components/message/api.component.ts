import { MessageService } from './message.service';
import { GroupService } from '../group/group.service';
import { Command } from './../../core/models/comman';
import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { UserService } from '../auth/user-register/user.service';
import {AddUserGroup, User} from '../auth/user-register/user';
import { Group } from '../group/group';
import { Message } from './message';
import {NotificationService} from "../../core/utils/notification/notification.service";
import {HttpParams} from "@angular/common/http";

@Component({
  templateUrl: './api.component.html',
  styleUrls: ['./message.component.css']
})

export class ApiComponent implements OnInit {
  user: User;

  userMessage: FormGroup;
  groupMessage: FormGroup;
  fileMessage: FormGroup;
  userform: FormGroup;
  groupform: FormGroup;
  messageform: FormGroup;
  addform: FormGroup;

  commands: Command[] = [
    { name: 'ADD_USER_TO_SYSTEM', command: 'ADD_USER_TO_SYSTEM', fields: ['username'] },
    { name: 'CREATE_CHAT_ROOM', command: 'CREATE_CHAT_ROOM', fields: ['room_name'] },
    { name: 'ADD_USER_TO_CHAT_ROOM', command: 'ADD_USER_TO_CHAT_ROOM', fields: ['username', 'room_name'] },
    { name: 'SEND_MESSAGE', command: 'SEND_MESSAGE', fields: ['from_user', 'to_user', 'msg_content'] },
    { name: 'SEND_MESSAGE_TO_ROOM', command: 'SEND_MESSAGE_TO_ROOM', fields: ['from_user', 'room_name', 'msg_content'] },
    { name: 'SEND_MESSAGE_TO_ALL', command: 'SEND_MESSAGE_TO_ALL', fields: ['msg_content'] },
    { name: 'SEND_FILE', command: 'SEND_FILE', fields: ['from_user', 'to_user', 'content'] }
  ];


  constructor(
    private userService: UserService,
    private groupService: GroupService,
    private messageService: MessageService,
    private notificationService: NotificationService
  ) { }


  ngOnInit() {
    this.user = JSON.parse(sessionStorage.getItem('user'));

    this.userform = this.userService.getUser(new User());
    this.groupform = this.groupService.getGroup(new Group());
    this.messageform = this.messageService.getMessage(new Message());
    this.addform = this.userService.AddUserGroup(new AddUserGroup());

    this.userMessage = this.messageService.getUserMessage(new Message());
    this.groupMessage = this.messageService.getGroupMessage(new Message());
    this.fileMessage = this.messageService.getFileMessage(new Message());
  }

  // Function to create user
  add_user_to_system() {
    this.userService.create(<User> this.userform.value)
      .subscribe(user => {
        this.notificationService.sucessInsert('User');
      }, err =>  {
        this.notificationService.error(err);
      });
  }

  // Function to create Group
  create_chat_room() {
    let httpParams = new HttpParams();
    httpParams = httpParams.append('userId', String(this.user.id));

    this.groupService.create(<Group> this.groupform.value, httpParams)
      .subscribe(user => {
        this.notificationService.sucessInsert('Group');
      }, err =>  {
        this.notificationService.error(err);
      });
  }


  // Function to add User to group
  add_user_chat_room() {
    this.userService.addUserToGroup(<AddUserGroup> this.addform.value )
      .subscribe(user => {
        this.notificationService.sucessUpdate('added User to Group');
      }, err =>  {
        this.notificationService.error(err);
      });
  }

  send_message(form: FormGroup) {
    form.value.fromUser = this.user.userName;
    console.log(form.value)
    this.messageService.sendMessage(<Message> form.value)
      .subscribe(user => {
        this.notificationService.sucessUpdate('Message sent to user');
      }, err =>  {
        this.notificationService.error(err);
      });
  }

  send_message_group(form: FormGroup) {
    form.value.fromUser = this.user.userName;
    this.messageService.sendMessageGroup(<Message> form.value)
      .subscribe(user => {
        this.notificationService.sucessUpdate('Message sent to group');
      }, err =>  {
        this.notificationService.error(err);
      });
  }

  send_message_broadcast(form: FormGroup) {
    form.value.fromUser = this.user.userName;
    this.messageService.sendMessageBroadcast(<Message> form.value)
      .subscribe(user => {
        this.notificationService.sucessUpdate('added User to Group');
      }, err =>  {
        this.notificationService.error(err);
      });
  }

}
