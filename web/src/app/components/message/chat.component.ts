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
import {Multimedia} from '../file-upload/multimedia';
import {Topic} from '../trending/topic';


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
  userReq: User;
  message: Message;
  myGroups: Group[];
  myContacts: Contact[];

  selectedGroup: Group;
  selectedContact: Contact;
  selectedBroadcast: boolean = false;

  userMessage: FormGroup;
  groupMessage: FormGroup;
  fileMessage: FormGroup;

  messages: MessageList[];

  constructor(
    private userService: UserService,
    private groupService: GroupService,
    private messageService: MessageService,
    private contactService: ContactService,
    private notificationService: NotificationService,
    globals: Globals
  ) { this.globals = globals; }

  ngOnInit() {
    this.user = JSON.parse(sessionStorage.getItem('user'));
    this.userMessage = this.messageService.getChatUserMessage(new Message());
    this.groupMessage = this.messageService.getChatGroupMessage(new Message());
    this.fileMessage = this.messageService.getFileMessage(new Message());

    this.userService.getById(this.user.id).subscribe(
      data => {
        this.userReq = data;
        sessionStorage.setItem('user', JSON.stringify(this.userReq));
        this.myGroups = this.user.myGroups;
      });

    this.contactService.getAllByUser({'userId': this.user.id}).subscribe(
      data => {
        this.myContacts = data;
      }
    );

    let paramsMessage = BaseService.jsonToHttpParams({
      toUser: this.user.userName
    });

    this.messageService.getAll(paramsMessage).subscribe(
      data => {
        this.globals.appMessages = this.globals.appMessages.concat(data);
        // this.messages = data;
        this.globals.appMessages = data;
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

    console.log("aaaaaaaaa",form.value)
    form.value.fromUser = this.user.userName;
    form.value.toUser = contact;
    console.log("ssssssssssssss",form.value)

    this.messageService.sendMessage(<Message> form.value)
      .subscribe(user => {
        this.notificationService.showSuccess();
      }, err =>  {
        this.notificationService.error(err);
      });

    this.addMessage(<Message> form.value,true);

  }

  send_message_group(form:FormGroup,contact: String) {

    form.value.fromUser = this.user.userName;
    form.value.toGroup = contact;

    this.messageService.sendMessageGroup(<Message> form.value)
      .subscribe(user => {
        this.notificationService.showSuccess();
      }, err =>  {
        this.notificationService.error(err);
      });
    this.addMessage(<Message> form.value,false);
  }

  send_message_broadcast(form:FormGroup,contact: String) {
    form.value.fromUser = this.user.userName;
    form.value.toGroup = contact;

    this.messageService.sendMessageBroadcast(<Message> form.value)
      .subscribe(user => {
        this.notificationService.showSuccess();
      }, err =>  {
        this.notificationService.error(err);
      });
    this.addMessage(<Message> form.value,false);
  }

  addMessage(message: Message, isUser: boolean){
    let messageTemp = new MessageList();
    let userFrom = new User();
    userFrom.userName = message.fromUser;
    messageTemp.fromUser = userFrom;
    messageTemp.body = message.body;

    if(isUser){
      let userTo = new User();
      userTo.userName = message.toUser;
      messageTemp.toUser = userTo;
      messageTemp.toGroup = null;
    }else{
      let groupTo = new Group();
      groupTo.name = message.toGroup;
      messageTemp.toGroup = groupTo;
      messageTemp.toUser = null;
    }

    let multimedia = new Multimedia();
    multimedia.url = message.multimedia;
    messageTemp.multimedia = multimedia;

    let topics: Topic[] = [];
    for( let t of message.topics) {
      let topic = new Topic();
      topic.name = t;
      topics.push(topic);
    }

    messageTemp.topics = topics;

    console.log("aaaaaaaaaaaaa", messageTemp)
    this.globals.appMessages = this.globals.appMessages.concat(messageTemp);
  }

}
