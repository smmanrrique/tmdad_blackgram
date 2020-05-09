import { MessageService } from './message.service';
import { GroupService } from './../group/group.service';
import { Command } from './../../core/models/comman';
import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { UserService } from '../auth/user-register/user.service';
import { User } from '../auth/user-register/user';
import { Group } from '../group/group';
import { Message } from './message';

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
  ) { }


  ngOnInit() {
    this.userform = this.userService.getUser(new User());
    this.groupform = this.groupService.getGroup(new Group());
    this.messageform = this.messageService.getMessage(new Message());
  }

  add_user_to_system() {
    console.log("add_user_to_system");

  }

  create_chat_room() {
    console.log("create_chat_room");
  }

  add_user_chat_room() {
    console.log("create_chat_room");
  }

  // handleFileInput(files: FileList) {
  //   this.fileToUpload = files.item(0);
  // }

  send_message() {
    console.log("send_message");
  }

}
