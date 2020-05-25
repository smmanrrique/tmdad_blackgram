import { BaseModel } from "src/app/core/models/base-model";
import {User} from '../auth/user-register/user';

export class Contact extends BaseModel {
  name: string;
}

export class Createcontact extends BaseModel {
  name: string;
  owner: User;
}
