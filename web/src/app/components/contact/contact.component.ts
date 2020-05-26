import { Component, OnInit } from '@angular/core';
import {Globals} from '../../globals';
import { User } from 'src/app/components/auth/user-register/user';
import {Contact, CreateContact} from './contact';
import {ContactService} from './contact.service';
import {FormGroup} from '@angular/forms';
import {Group} from '../group/group';
import {NotificationService} from '../../core/utils/notification/notification.service';
import {UserService} from '../auth/user-register/user.service';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {
  user:User;
  globals: Globals;
  myContacts: Contact[];
  allContacts:  User[];
  contacForm: FormGroup;


  constructor(
    private userService: UserService,
    private contactService: ContactService,
    private notificationService: NotificationService,
    globals: Globals
  ) { this.globals = globals; }

  ngOnInit() {
    this.user = JSON.parse(sessionStorage.getItem('user'));
    this.contacForm = this.contactService.getAddContac(new CreateContact());
    // this.contacForm = this.contactService.addContac(new Contact());

    this.contactService.getAllByUser({'userId': this.user.id}).subscribe(
      data => {
        this.myContacts = data;
      }
    );

    this.userService.getAll().subscribe(
      data => {
        this.allContacts = data;
        console.log(this.allContacts);
      }
    );

  }

  add_contact(form: FormGroup ){

    this.contactService.create(<Contact> form.value, {userId:this.user.id})
      .subscribe(user => {
        this.notificationService.sucessInsert('Added new contact');
      }, err =>  {
        this.notificationService.error(err);
      });
    console.log(form.value);

  }

}
