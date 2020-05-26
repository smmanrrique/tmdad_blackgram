import { Injectable } from '@angular/core';
import {MessageList} from './components/message/message';

@Injectable()
export class Globals {
  appMessages: MessageList[] = [];
}
