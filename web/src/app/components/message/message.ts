import { Multimedia } from '../multimedia/multimedia';
import { BaseModel } from "src/app/core/models/base-model";
import { Topic } from "./topic";

export class Message extends BaseModel {
    fromUser: string;
    toUser: string;
    body: string;
    topic: Topic[];
    multimedia: Multimedia;
} 