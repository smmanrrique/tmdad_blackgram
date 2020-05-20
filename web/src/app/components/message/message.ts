import { BaseModel } from "src/app/core/models/base-model";
import {Multimedia} from "../file-upload/multimedia";
import {Topic} from "./topic";


export class Message extends BaseModel {
    fromUser: string;
    toUser: string;
    toGroup: string;
    body: string;
    multimedia: string;
    topics: string[] = [];
}

export class MessageList extends BaseModel {
  fromUser: string;
  toUser: string;
  toGroup: string;
  body: string;
  multimedia: Multimedia;
  topics: Topic[] = [];
}
