import { Component, OnInit } from '@angular/core';
import {MessageList} from '../message/message';
import {Globals} from '../../globals';

import * as Stomp from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import {User} from '../auth/user-register/user';


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
      this.user = JSON.parse(sessionStorage.getItem('user'));
      this.connect();
    }

  connect() {
    let socket = new SockJS('http://localhost:8090/connect');
    this.stompClient = Stomp.over(socket);

    const _this = this;
    this.stompClient.connect({}, function (frame) {
      _this.stompClient.subscribe('/queue/reply/' + _this.user.userName, function (messageOutput) {
        _this.saveMessageOutput(JSON.parse(messageOutput.body));
      });
    });

    socket.addEventListener('open', function (e) {
      _this.stompClient.send('/chat/listen', {}, JSON.stringify(_this.user.userName));
    });
  }

  saveMessageOutput(message) {
    let msg = <MessageList> message;
    this.globals.appMessages.push(msg);
  }

  disconnect() {
    if (this.stompClient != null) {
      this.stompClient.disconnect();
    }
  }
}
