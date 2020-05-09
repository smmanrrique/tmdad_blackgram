import {Component, OnInit} from "@angular/core";
import {Group} from "./group";
import {User} from "../auth/user-register/user";

@Component({
  selector: "app-group",
  templateUrl: "./group.component.html",
  styleUrls: ["./group.component.css"]
})
export class GroupComponent implements OnInit {

  selectedGroup: Group;

  User = new User();

  // @ts-ignore
  groups_test: Group[] = [
    { name: "g1", description: "ADD_USER_TO_SYSTEM", user: "[{}]" },
    { name: "g2", description: "ADD_USER_TO_SYSTEM", user: "[]" },
    { name: "g4", description: "ADD_USER_TO_SYSTEM", user: "[]" },
    { name: "g3", description: "ADD_USER_TO_SYSTEM", user: "[]" },
  ];

  ngOnInit() {

  }

  constructor() { }

  onSelect(group: Group): void {
    this.selectedGroup = group;
  }
}
