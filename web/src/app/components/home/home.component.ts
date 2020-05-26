import {Component, HostListener, OnInit} from '@angular/core';
import {MessageList} from '../message/message';
import {Globals} from '../../globals';

import * as Stomp from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import {User} from '../auth/user-register/user';
import {BaseService} from '../../core/base.service';

@Component({
		selector: 'app-home',
		templateUrl: './home.component.html',
		styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  user: User;
  globals: Globals;
  private stompClient = null;

  constructor(globals: Globals) { this.globals = globals; }

  ngOnInit() {
    console.log("starting again...");
    this.user = JSON.parse(sessionStorage.getItem('user'));
    this.connect();
  }

  @HostListener('window:beforeunload')
  closeChannelBeforeReload() {
    this.stompClient.send('/chat/close', {}, JSON.stringify(this.user.userName));
  }

  connect() {
    let socket = new SockJS(BaseService.HOST+'/connect');
    this.stompClient = Stomp.over(socket);

    const _this = this;
    this.stompClient.connect({}, function (frame) {
      _this.stompClient.subscribe('/queue/reply/' + _this.user.userName, function (messageOutput) {
        _this.saveMessageOutput(JSON.parse(messageOutput.body));
      });

      if (_this.user.admin) {
          _this.stompClient.subscribe('/queue/trending/top', function (messageOutput) {
            _this.saveMessageOutputTrending('top', JSON.parse(messageOutput.body));
          });

          _this.stompClient.subscribe('/queue/trending/topfive', function (messageOutput) {
            _this.saveMessageOutputTrending('topfive', JSON.parse(messageOutput.body));
          });

          _this.stompClient.subscribe('/queue/trending/time', function (messageOutput) {
            _this.saveMessageOutputTrending('time', JSON.parse(messageOutput.body));
          });

          _this.stompClient.subscribe('/queue/trending/realtime', function (messageOutput) {
            _this.saveMessageOutputTrending('realtime', JSON.parse(messageOutput.body));
          });

          _this.stompClient.subscribe('/queue/trending/user', function (messageOutput) {
            _this.saveMessageOutputTrending('user', JSON.parse(messageOutput.body));
          });

          _this.stompClient.subscribe('/queue/trending/userto', function (messageOutput) {
            _this.saveMessageOutputTrending('userto', JSON.parse(messageOutput.body));
          });
      }
    });
    socket.addEventListener('open', function (e) {
      _this.stompClient.send('/chat/listen', {}, JSON.stringify(_this.user.userName));
    });
  }

  saveMessageOutput(message) {
    let msg = <MessageList> message;
    this.globals.appMessages.push(msg);
  }

  saveMessageOutputTrending(type, message) {
    if (type == 'top') {
      this.globals.topTopic = message;
    } else if (type == 'topfive') {
      this.globals.recentTopic = message;
    } else if (type == 'time') {
      this.globals.realTimeTopic = message;
    } else if (type == 'realtime') {
      this.globals.realTimeTopic = message;
    } else if (type == 'user') {
      this.globals.userTopic = message
    } else if (type == 'userto') {
      this.globals.timeTopic = message;
    }
  }

  disconnect() {
    if (this.stompClient != null) {
      this.stompClient.send('/chat/close', {}, JSON.stringify(this.user.userName));
      sessionStorage.setItem('user', '');
      this.stompClient.disconnect();
    }
  }
}
