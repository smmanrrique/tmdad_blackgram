import { BaseModel } from "src/app/core/models/base-model";
import {Multimedia} from "../file-upload/multimedia";
import {Group} from "../group/group";
import { User } from "src/app/components/auth/user-register/user";
import {Topic} from '../trending/topic';


export class Message extends BaseModel {
    fromUser: string;
    toUser: string;
    toGroup: string;
    body: string;
    multimedia: string;
    topics: string[] = [];
}

export class MessageList extends BaseModel {
  fromUser: User;
  toUser: User;
  toGroup: Group;
  body: string;
  multimedia: Multimedia;
  topics: Topic[] = [];
}
