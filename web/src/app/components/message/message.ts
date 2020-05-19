import { BaseModel } from "src/app/core/models/base-model";


export class Message extends BaseModel {
    fromUser: string;
    toUser: string;
    toGroup: string;
    body: string;
    multimedia: string;
    topics: string[] = [];
}

// export class MessageFile extends BaseModel {
//   fromUser: string;
//   toUser: string;
//   toGroup: string;
//   body: string;
//   multimedia: string;
//   topics: Topic[] = [];
// }
