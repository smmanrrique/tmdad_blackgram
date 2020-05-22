import { Component, OnInit } from '@angular/core';
import { User} from '../auth/user-register/user';
import { Group } from '../group/group';
import {UserService} from "../auth/user-register/user.service";
import {GroupService} from "../group/group.service";
import {MessageService} from "./message.service";
import {NotificationService} from "../../core/utils/notification/notification.service";
import {Message, MessageList} from "./message";
import {ContactService} from "../contact/contact.service";
import { Contact } from 'src/app/components/contact/contact';
import {FormGroup} from "@angular/forms";
import {BaseService} from "../../core/base.service";

@Component({
  // selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./message.component.css']
  // styleUrls: ['../group/group.component.css']
})

export class ChatComponent implements OnInit {

  user: User;
  myGroups: Group[];
  myContacts: Contact[];
  messages: MessageList[];

  selectedGroup: Group;
  selectedContact: Contact;
  selectedBroadcast: String;

  messageForm: FormGroup;
  userMessage: FormGroup;
  groupMessage: FormGroup;
  fileMessage: FormGroup;


  constructor(
    private userService: UserService,
    private groupService: GroupService,
    private messageService: MessageService,
    private contactService: ContactService,
    private notificationService: NotificationService
  ) { }

  ngOnInit() {
    this.userMessage = this.messageService.getUserMessage(new Message());
    this.groupMessage = this.messageService.getGroupMessage(new Message());
    this.fileMessage = this.messageService.getFileMessage(new Message());

    // TODO Get ID USER FROM URL
    this.userService.getById(1).subscribe(
      data => {
        this.user = data;
        this.myGroups = this.user.myGroups;
      });

    // SET ID USER FROM URL
    this.contactService.getAll({"userId": 1}).subscribe(
      data => {
        this.myContacts = data;
      }
    );

    // TODO USAR WEBSOCKET
    let paramsMessage = BaseService.jsonToHttpParams({
      toUser: "u1"
    });

    this.messageService.getAll(paramsMessage).subscribe(
      data => {
        console.log(data)
        this.messages = data;
      });

  }


  onSelectGroup(group: Group): void {
    this.selectedContact = null;
    this.selectedBroadcast = null;
    this.selectedGroup = group;
  }

  onSelectContact(contac: Contact): void {
    console.log(contac)
    this.selectedGroup = null;
    this.selectedBroadcast = null;
    this.selectedContact = contac;
  }
  onSelectedBroadcast(all: string): void {
    this.selectedGroup = null;
    this.selectedContact = null;
    this.selectedBroadcast = all;
  }


  send_message() {
    console.log("send_message");
    console.log(this.messageForm)
    this.notificationService.showInfo('Send Message');

    this.messageService.sendMessage(<Message> this.messageForm.value)
      .subscribe(user => {
        this.notificationService.sucessUpdate('added User to Group');
      }, err =>  {
        this.notificationService.error(err);
      });
  }

  send_message_group() {
    console.log("send_message");
    console.log(this.messageForm)
    this.notificationService.showInfo('Send Message');

    this.messageService.sendMessageGroup(<Message> this.messageForm.value)
      .subscribe(user => {
        this.notificationService.sucessUpdate('added User to Group');
      }, err =>  {
        this.notificationService.error(err);
      });
  }

  send_message_broadcast() {
    console.log("send_message");
    console.log(this.messageForm)
    this.notificationService.showInfo('Send Message');

    this.messageService.sendMessageBroadcast(<Message> this.messageForm.value)
      .subscribe(user => {
        this.notificationService.sucessUpdate('added User to Group');
      }, err =>  {
        this.notificationService.error(err);
      });
  }

}
