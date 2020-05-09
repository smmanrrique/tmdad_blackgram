import {Component, OnInit} from "@angular/core";
import {Group} from "./group";
import {User} from "../auth/user-register/user";
import {MatTableDataSource} from "@angular/material/table";

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

  // dataSource = new MatTableDataSource<Message>();
  // dataSource = new MatTableDataSource<Sms>();
  selectedGroup: Group;

    User = new User();

  // @ts-ignore
  groups_test: Group[] = [
    { name: "g1", description: "ADD_USER_TO_SYSTEM", user: "[{}]" },
    { name: "g2", description: "ADD_USER_TO_SYSTEM", user: "[]" },
    { name: "g4", description: "ADD_USER_TO_SYSTEM", user: "[]" },
    { name: "g3", description: "ADD_USER_TO_SYSTEM", user: "[]" },
  ];

  sms: Sms[] = [
    {fromUser: 'U1 SHAMUEL AMS', body: 'asssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U2', body: 'asssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U3', body: 'asssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U4', body: 'asssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U5', body: 'asssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U6', body: 'asssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U1', body: 'asssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U1', body: 'asssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U1', body: 'asssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
  ];

  displayedColumns: string[] = ['message.fromUser', 'message'];

  g1 = new Group();
  dataSource = this.sms;
  ngOnInit() {
    // this.dataSource.data = this.sms;
  }

  constructor() { }

  onSelect(group: Group): void {
    this.selectedGroup = group;
  }
}
