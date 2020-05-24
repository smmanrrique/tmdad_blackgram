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

import * as Stomp from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';

@Component({
  // selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./message.component.css']
  // styleUrls: ['../group/group.component.css']
})

export class ChatComponent implements OnInit {

  private stompClient = null;

  userName: String = sessionStorage.getItem('userSession');
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
    this.userMessage = this.messageService.getChatUserMessage(new Message());
    this.groupMessage = this.messageService.getChatGroupMessage(new Message());
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


    this.connect();

  }


  connect() {
    console.log("// tslint:disable-next-line:indent")

    let socket = new SockJS('http://localhost:8090/connect');
    this.stompClient = Stomp.over(socket);

    const _this = this
    this.stompClient.connect({}, function (frame) {
      console.log("*******" + _this.userName);
      _this.stompClient.subscribe("/queue/reply/" + _this.userName, function (messageOutput) {
        console.log("*******/queue/reply/" + _this.userName + messageOutput.body);
        _this.showMessageOutput(JSON.parse(messageOutput.body));

      });
    });

    socket.addEventListener('open', function (e) {
      _this.stompClient.send("/chat/prueba", {}, JSON.stringify("soy yo menor "));
    });

    console.log(this.stompClient )
  }


  showMessageOutput(messageOutput) {
    this.messages.push(messageOutput);
    console.log(messageOutput)
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
  onSelectedBroadcast(): void {
    this.selectedGroup = null;
    this.selectedContact = null;
    this.selectedBroadcast = "ADMIN BROADCAST";
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
