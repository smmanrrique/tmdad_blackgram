import { BaseModel } from "src/app/core/models/base-model";
import {Group} from "../../group/group";

export class User extends BaseModel {
	userName: string;
  password: string;
	firstName: string;
	lastName: string;
	email: string;
  admin: boolean = false;
  myGroups: Group[] = [];


}
