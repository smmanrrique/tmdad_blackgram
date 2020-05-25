import { Component, OnInit } from '@angular/core';
import { User} from '../auth/user-register/user';
import { Group } from '../group/group';
import {UserService} from '../auth/user-register/user.service';
import {GroupService} from '../group/group.service';
import {MessageService} from './message.service';
import {NotificationService} from '../../core/utils/notification/notification.service';
import {Message, MessageList} from './message';
import {ContactService} from '../contact/contact.service';
import { Contact } from 'src/app/components/contact/contact';
import {FormGroup} from '@angular/forms';
import {BaseService} from '../../core/base.service';
import {Globals} from '../../globals';


@Component({
  // selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./message.component.css']
  // styleUrls: ['../group/group.component.css']
})

export class ChatComponent implements OnInit {
  globals: Globals;

  userName: String = sessionStorage.getItem('userSession');
  user: User;
  message: Message;
  myGroups: Group[];
  myContacts: Contact[];

  selectedGroup: Group;
  selectedContact: Contact;
  selectedBroadcast: boolean = false;

  messageForm: FormGroup;
  userMessage: FormGroup;
  groupMessage: FormGroup;
  fileMessage: FormGroup;

  constructor(
    private userService: UserService,
    private groupService: GroupService,
    private messageService: MessageService,
    private contactService: ContactService,
    private notificationService: NotificationService,
    globals: Globals
  ) { this.globals = globals; }

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
    this.contactService.getAll({'userId': 1}).subscribe(
      data => {
        this.myContacts = data;
      }
    );

    // TODO USAR WEBSOCKET
    let paramsMessage = BaseService.jsonToHttpParams({
      toUser: 'u1'
    });

    this.messageService.getAll(paramsMessage).subscribe(
      data => {
        this.globals.appMessages = this.globals.appMessages.concat(data);
      });
  }


  onSelectGroup(group: Group): void {
    console.log("onSelectGroup ");
    this.selectedContact = null;
    this.selectedBroadcast = false;
    this.selectedGroup = group;
  }

  onSelectContact(contac: Contact): void {
    console.log(contac);
    this.selectedGroup = null;
    this.selectedBroadcast = false;
    this.selectedContact = contac;
  }

  onSelectedBroadcast(): void {
    this.selectedGroup = null;
    this.selectedContact = null;
    this.selectedBroadcast = true;
  }

  send_message(form:FormGroup,contact: String) {

    form.value.fromUser = this.user.userName;
    form.value.toUser = contact;

    this.messageService.sendMessage(<Message> form.value)
      .subscribe(user => {
        this.notificationService.sucessUpdate('Message sent to user');
      }, err =>  {
        this.notificationService.error(err);
      });
  }

  send_message_group(form:FormGroup,contact: String) {

    form.value.fromUser = this.user.userName;
    form.value.toGroup = contact;

    this.messageService.sendMessageGroup(<Message> form.value)
      .subscribe(user => {
        this.notificationService.sucessUpdate('Message sent to group');
      }, err =>  {
        this.notificationService.error(err);
      });
  }

  send_message_broadcast(form:FormGroup,contact: String) {
    form.value.fromUser = this.user.userName;
    form.value.toGroup = contact;

    this.messageService.sendMessageBroadcast(<Message> form.value)
      .subscribe(user => {
        this.notificationService.sucessUpdate('added User to Group');
      }, err =>  {
        this.notificationService.error(err);
      });
  }
}
