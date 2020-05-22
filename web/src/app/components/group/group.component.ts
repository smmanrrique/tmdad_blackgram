import {Component, OnInit} from "@angular/core";
import {Group} from "./group";
import {User} from "../auth/user-register/user";
import {UserService} from "../auth/user-register/user.service";
import {GroupService} from "./group.service";
import {MessageService} from "../message/message.service";
import {BaseService} from "../../core/base.service";
import {Message, MessageList} from "../message/message";

export interface Sms {
  fromUser: string;
  body: string;
  topics: string;
  multimedia: string;
}

@Component({
  selector: "app-group",
  templateUrl: "./group.component.html",
  styleUrls: ["./group.component.css"]
})
export class GroupComponent implements OnInit {

  selectedGroup: Group;

  user: User;
  myGroup: Group[];
  groupMessages: MessageList[];

  constructor(
    private userService: UserService,
    private groupService: GroupService,
    private messageService: MessageService,
  ) { }

  ngOnInit() {
    // TODO Get ID USER FROM URL
    this.userService.getById(1).subscribe(
      data => {
        // Get User
        this.user = data;

        // Get userGroups
        this.myGroup = this.user.myGroups
        console.log(this.myGroup)
      });

  }


  onSelect(group: Group): void {
    this.selectedGroup = group;

    let paramsMessage = BaseService.jsonToHttpParams({
      toGroup: group.name
    });

    this.messageService.getAll(paramsMessage).subscribe(
      data => {
        console.log(data)
        this.groupMessages = data;
        console.log(this.groupMessages)
      });
  }
}
