import { Component, OnInit } from '@angular/core';
import {Globals} from '../../globals';
import {User} from '../auth/user-register/user';

@Component({
  selector: 'app-user-information',
  templateUrl: './user-information.component.html',
  styleUrls: ['./user-information.component.css']
})
export class UserInformationComponent implements OnInit {
  user:User;
  globals: Globals;

  constructor(
    globals: Globals
  ) { this.globals = globals; }

  ngOnInit() {
    this.user = JSON.parse(sessionStorage.getItem('user'));
  }

}
