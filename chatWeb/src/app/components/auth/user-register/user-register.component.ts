import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { NotificationService } from 'src/app/core/utils/notification/notification.service';
import { UserService } from '../../user/user.service';

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
  }

}
