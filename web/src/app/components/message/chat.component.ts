import { Component, OnInit } from '@angular/core';
import { User} from '../auth/user-register/user';
import { Group } from '../group/group';
import {UserService} from "../auth/user-register/user.service";
import {GroupService} from "../group/group.service";
import {MessageService} from "./message.service";
import {NotificationService} from "../../core/utils/notification/notification.service";
import {MessageList} from "./message";
import {ContactService} from "../contact/contact.service";
import { Contact } from 'src/app/components/contact/contact';

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

  constructor(
    private userService: UserService,
    private groupService: GroupService,
    private messageService: MessageService,
    private contactService: ContactService,
    private notificationService: NotificationService
  ) { }

  ngOnInit() {
    // TODO Get ID USER FROM URL
    this.userService.getById(1).subscribe(
      data => {
        console.log(data)
        this.user = data;
        this.myGroups = this.user.myGroups
        console.log(this.myGroups)}
        );

    // SET ID USER FROM URL
    this.contactService.getAll({"userId": 1}).subscribe(
      data => {
        console.log(data)
        this.myContacts = data;
        // this.myGroups = this.user.myGroups
        console.log(this.myContacts)}
    );

    // TODO Get ID USER FROM URL
    // this.contactService.getAll(paramsContact).subscribe(
    //   data => {
    //     this.user = data['result'];
    //     console.log(this.messages)});

  }


  onSelect(group: Group): void {
    this.selectedGroup = group;
  }

  onSelectContact(contac: Contact): void {
    console.log(contac)
  }

}
