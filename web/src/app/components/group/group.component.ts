import {Component, OnInit} from "@angular/core";
import {Group} from "./group";
import {User} from "../auth/user-register/user";
import {MatTableDataSource} from "@angular/material/table";
import {UserService} from "../auth/user-register/user.service";
import {GroupService} from "./group.service";
import {HttpParams} from "@angular/common/http";
import {MessageService} from "../message/message.service";
import {BaseService} from "../../core/base.service";
import {Message} from "../message/message";

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
  groupMessages: Message[];

  sms: Sms[] = [
    {fromUser: 'U1 SHAMUEL AMS', body: 'Pese a los datos oficiales, desde el inicio de la emergencia sanitaria el líder del Partido Popular, Pablo Casado, ha considerado que la gestión de la pandemia y las políticas puestas en marcha por Díaz Ayuso son un "ejemplo a seguir". "Es lo que haríamos a nivel nacional", apuntó el líder del PP el pasado sábado, tras participar en el acto oficial de la comunidad con motivo de la festividad del 2 de mayo.', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U2', body: 'asssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U3', body: 'asssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U4', body: 'asssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U5', body: 'asssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U6', body: 'asssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U1', body: 'asssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U1', body: 'asssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U1', body: 'asssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U1', body: 'asssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U1', body: 'asssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U1', body: 'asssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U1', body: 'asssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U1', body: 'asssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U1', body: 'asssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U1', body: 'asssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U1', body: 'asssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U1', body: 'asssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U1', body: 'asssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U1', body: 'asssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U1', body: 'asssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U1', body: 'asssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U1', body: 'asssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U1', body: 'asssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U1', body: 'asssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U1', body: 'asssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U1', body: 'asssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U1', body: 'asssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U1', body: 'asssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U1', body: 'asssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U1', body: 'asssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U1', body: 'asssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U1', body: 'asssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U1', body: 'asssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U1', body: 'asssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
  ];

  constructor(
    private userService: UserService,
    private groupService: GroupService,
    private messageService: MessageService,
  ) { }

  ngOnInit() {
    // // TODO Get ID USER FROM URL
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
      // toGroup: group.name
      fromUser: "u1"
    });

    // httpParams = httpParams.append('userId', String(1));

    this.messageService.getAll(paramsMessage).subscribe(
      data => {
        console.log(data)
        this.groupMessages = data['result'];
        console.log(this.groupMessages)
      });
  }
}
