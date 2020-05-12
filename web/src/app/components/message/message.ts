import { Multimedia } from './../file-upload/multimedia';
import { BaseModel } from "src/app/core/models/base-model";
import { Topic } from "./topic";

export class Message extends BaseModel {
    id: number;
    toUser: string;
    fromUser: string;
    group_message: string;
    body: string;
    topics: Topic[];
    multimedia: Multimedia;
} 