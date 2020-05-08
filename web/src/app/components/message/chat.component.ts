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
  // selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./message.component.css']
})

export class ChatComponent implements OnInit {

  userform: FormGroup;
  groupform: FormGroup;
  messageform: FormGroup;

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

  send_message() {
    console.log("send_message");
  }

}
