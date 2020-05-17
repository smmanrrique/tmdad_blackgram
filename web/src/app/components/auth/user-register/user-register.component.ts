import { User } from './user';
import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { NotificationService } from 'src/app/core/utils/notification/notification.service';
import { UserService } from './user.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-user-register',
  templateUrl: './user-register.component.html',
  styleUrls: ['./user-register.component.css']
})
export class UserRegisterComponent implements OnInit {

  form: FormGroup;

  constructor(
    private location: Location,
    private userService: UserService,
    private notificationService: NotificationService,
  ) { }

  ngOnInit() {
    this.form = this.userService.getUser(new User());
  }

  create() {
    console.log('create');
    console.log(this.form);

    this.userService.create(<User>this.form.value)
      .subscribe(provider => {
        this.notificationService.sucessInsert('User');
        this.location.back();
      }, err => {
        this.notificationService.error(err);
      });
  }

}
