import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Router } from "@angular/router";
import { Observable } from 'rxjs';

import * as Stomp from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  
  text: string;
  ouser: string;
  duser: string;
  group: string;
  contact: string;

  messages: string[] = [];

  private stompClient = null;

  constructor(
    private http: HttpClient,
    private router: Router
  ) { }

  ngOnInit() {
    // Verify if user is logged in otherwise redirect to login page
    if (sessionStorage.getItem('token')) {
      // console.log('Authenticating...')
      // let url = 'http://localhost:8080/user';

      // let headers: HttpHeaders = new HttpHeaders({
      //   'Authorization': 'Basic ' + sessionStorage.getItem('token')
      // });

      // let options = { headers: headers };
      // this.http.post<Observable<Object>>(url, {}, options).
      //   subscribe(principal => {
      //     this.user = principal['name'];
      //   },
      //     error => {
      //       if (error.status == 401)
      //         alert('Unauthorized**');
      //     }
      //   );
      // Now is time to create the websocket to receive messages 
      this.ouser = sessionStorage.getItem('user');           
      this.connect();
    } else {
      this.router.navigate(['/login']);
    }
  }

  connect() {
    var socket = new SockJS('http://localhost:8090/connect');
    this.stompClient = Stomp.over(socket);

    const _this = this;
    this.stompClient.connect({}, function (frame) {
    
      _this.stompClient.subscribe("/queue/reply/" + _this.ouser, function (messageOutput) {
        console.log("*******/queue/reply/" + _this.ouser + messageOutput.body);
        _this.showMessageOutput(JSON.parse(messageOutput.body));
      });
    
      _this.stompClient.subscribe("/queue/updates/" + _this.ouser, function (messageOutput) {
        console.log("*******/queue/updates/" + _this.ouser + messageOutput.body);
        _this.showMessageOutput(JSON.parse(messageOutput.body));
      });

      _this.stompClient.subscribe("/topic/all", function (messageOutput) {
        console.log("*******/topic/all" + _this.ouser + messageOutput.body);
        _this.showMessageOutput(JSON.parse(messageOutput.body));
      });
  
      _this.stompClient.subscribe("/topic/trending", function (messageOutput) {
        console.log("*******/topic/trending"  + messageOutput.body);
        _this.showMessageOutput(JSON.parse(messageOutput.body));
      });
    });
    
    socket.addEventListener('open', function (e) { 
      _this.sendAction('get-contacts');
      _this.sendAction('get-groups');
    });
  }

  sendAction(type) {
    let endpoint = '';
    let data = {};

    if (type == 'get-contacts') {
      data['type'] = type;
      data['user'] = this.ouser;
      endpoint     = '/chat/get-info';
    } else if (type == 'get-groups') {
      data['type'] = type;
      data['user'] = this.ouser;
      endpoint     = '/chat/get-info';
    } else if (type == 'add-group') { 
      data['user']  = this.ouser
      data['group'] = this.group
      endpoint      = '/chat/add-group';
    } else if (type == 'add-contact') {
      data['user']    = this.ouser
      data['contact'] = this.contact
      endpoint        = '/chat/add-contact';
    }
    this.stompClient.send(endpoint, {}, JSON.stringify(data));
  }

  sendMessage(type) {
    let data = {};

    if (type == 'msg-direct') {
      data['type']  = type
      data['ouser'] = this.ouser
      data['duser'] = this.duser 
      data['text']  = this.text 

    } else if (type == 'msg-group') {
      data['type']  = type
      data['ouser'] = this.ouser
      data['duser'] = this.duser
      data['group'] = this.group
      data['text']  = this.text 

    } else if (type == 'msg-all') {
      data['type']  = type
      data['ouser'] = this.ouser      
      data['text']  = this.text 
    }
    console.log({ 'type': type, 'data': data });
    this.stompClient.send("/action", {}, JSON.stringify({ 'type': type, 'data': '--'}));
  }

  showMessageOutput(messageOutput) {
    this.messages.push(messageOutput);
  }

  disconnect() {
    if (this.stompClient != null) {
      this.stompClient.disconnect();
    }
  }

  logout() {
    sessionStorage.setItem('token', '');
    this.disconnect();
  }
}