import { Injectable } from '@angular/core';
import {MessageList} from './components/message/message';
import {RealTimeTopicDTO, TimeTopicDTO, TopTopicDTO, UserTopicDTO} from './components/trending/topic';

@Injectable()
export class Globals {
  appMessages: MessageList[] = [];
  timeTopic: TimeTopicDTO[] = [];
  userTopic: UserTopicDTO[];
  topTopic: TopTopicDTO[] = [];
  recentTopic: TopTopicDTO[] = [];
  realTimeTopic: RealTimeTopicDTO[] = [];
}
