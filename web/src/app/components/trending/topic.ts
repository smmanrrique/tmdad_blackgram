import { BaseModel } from "src/app/core/models/base-model";
import { User } from "../auth/user-register/user";

export class Topic extends BaseModel {
  name: string;
  // messages: Message[] = [];
}

export class TimeTopicDTO {
  name: string;
  timestamp: string;
  date: string;
  day: number;
  hour: number;
  minute: number;
}

export class TopTopicDTO {
  name: string;
  count: number;
}


export class UserTopicDTO {
  id: number;
  userName: string;
  count: number;
}
