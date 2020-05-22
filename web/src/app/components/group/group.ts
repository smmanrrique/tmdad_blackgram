import { BaseModel } from "src/app/core/models/base-model";
import { User } from "../auth/user-register/user";

export class Group extends BaseModel {
    name: string;
    owner: User;
    users: User[] = [];
}
