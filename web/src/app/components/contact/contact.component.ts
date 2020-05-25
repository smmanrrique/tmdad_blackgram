import { Component, OnInit } from '@angular/core';
import {Globals} from '../../globals';
import { User } from 'src/app/components/auth/user-register/user';
import {Contact} from './contact';
import {ContactService} from './contact.service';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {
  user:User;
  globals: Globals;
  myContacts: Contact[];
  allContacts:  Contact[];


  constructor(
    private contactService: ContactService,
    globals: Globals
  ) { this.globals = globals; }

  ngOnInit() {
    this.user = JSON.parse(sessionStorage.getItem('user'));

    this.contactService.getAll({'userId': this.user.id}).subscribe(
      data => {
        this.myContacts = data;
      }
    );

    this.contactService.getAll({'userId': this.user.id}).subscribe(
      data => {
        this.allContacts = data;
      }
    );

  }

  add_contact(){

  }

}
