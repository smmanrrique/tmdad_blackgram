import {Component, OnInit, ViewChild} from '@angular/core';
import {TrendingService} from './trending.service';
import {FormGroup} from '@angular/forms';
import {RealTimeTopicDTO, TimeTopicDTO, TopTopicDTO, UserTopicDTO} from './topic';
import {Globals} from '../../globals';
import * as Stomp from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import {User} from '../auth/user-register/user';
import {Chart} from 'chart.js';

@Component({
  selector: 'app-trending',
  templateUrl: './trending.component.html',
  styleUrls: ['./trending.component.css']
})
export class TrendingComponent implements OnInit {
  displayedColumns: string[] = ['position', 'name', 'count'];

  user: User;
  globals: Globals;

  timeTopicForm: FormGroup;
  userTopicForm: FormGroup;
  topTopicForm: FormGroup;

  timeTopic: TimeTopicDTO[];
  userTopic: UserTopicDTO[];
  topTopic: TopTopicDTO[];
  recentTopic: TopTopicDTO[];
  realTimeTopic: RealTimeTopicDTO[];

  canvas: any;
  ctx: any;
  @ViewChild('mychart') mychart;
  private stompClient = null;
  private dataSource: any;
  private chartRef: any;
  private chart: Chart;

  constructor(
    private trendingService: TrendingService,
    globals: Globals
  ) { this.globals = globals;}

  ngOnInit(){
    this.user = JSON.parse(sessionStorage.getItem('user'));
    console.log("Starting to request websocket...")
    this.connect();
  }

  connect() {
    let socket = new SockJS('http://localhost:8080/connect');
    this.stompClient = Stomp.over(socket);

    const _this = this;
    this.stompClient.connect({}, function (frame) {
      _this.stompClient.subscribe('/queue/trending/top/' + _this.user.userName, function (messageOutput) {
        _this.saveMessageOutput('top', JSON.parse(messageOutput.body));
      });

      _this.stompClient.subscribe('/queue/trending/topfive/' + _this.user.userName, function (messageOutput) {
        _this.saveMessageOutput('topfive', JSON.parse(messageOutput.body));
      });

      _this.stompClient.subscribe('/queue/trending/time/' + _this.user.userName, function (messageOutput) {
        _this.saveMessageOutput('time', JSON.parse(messageOutput.body));
      });

      _this.stompClient.subscribe('/queue/trending/realtime/' + _this.user.userName, function (messageOutput) {
        _this.saveMessageOutput('realtime', JSON.parse(messageOutput.body));
      });

      _this.stompClient.subscribe('/queue/trending/user/' + _this.user.userName, function (messageOutput) {
        _this.saveMessageOutput('user', JSON.parse(messageOutput.body));
      });

      _this.stompClient.subscribe('/queue/trending/userto/' + _this.user.userName, function (messageOutput) {
        _this.saveMessageOutput('userto', JSON.parse(messageOutput.body));
      });
    });

    socket.addEventListener('open', function (e) {
      _this.stompClient.send('/chat/topic/top', {}, JSON.stringify(_this.user.userName));
      _this.stompClient.send('/chat/topic/topfive', {}, JSON.stringify(_this.user.userName));
      _this.stompClient.send('/chat/topic/time', {}, JSON.stringify(_this.user.userName));
      _this.stompClient.send('/chat/topic/realtime', {}, JSON.stringify(_this.user.userName));
      _this.stompClient.send('/chat/topic/user', {}, JSON.stringify(_this.user.userName));
      _this.stompClient.send('/chat/topic/userto', {}, JSON.stringify(_this.user.userName));
    });
  }

  saveMessageOutput(type, message) {
    if (type == 'top') {
      this.topTopic = message;
    } else if (type == 'topfive') {
      this.recentTopic = message;
      // this.dataSource = this.setPosition(this.topTopic);
    } else if (type == 'time') {
      this.realTimeTopic = message;
          this.draw(this.realTimeTopic);
    } else if (type == 'realtime') {
      this.realTimeTopic = message;
    } else if (type == 'user') {
      this.userTopic = message
    } else if (type == 'userto') {
      this.timeTopic = message;
    }
  }

  draw(dataTime: any) {
    this.canvas = this.mychart.nativeElement;
    this.ctx = this.canvas.getContext('2d');
    this.chart = new Chart(this.ctx, {
      type: 'line',
      data: {
         datasets: [{
            label: 'RealTime',
            backgroundColor: 'rgba(194,180,180,0.4)',
            borderColor: 'rgb(83,80,80)',
            fill: true,
            data: dataTime,
        }]
      },
      options: {
        responsive: true,
        title: {
          display: true,
          text: 'Trendings'
        },
        legend: {
          display: false
        },
        scales: {
          xAxes: [{
            type: 'linear',
            position: 'bottom',
            ticks: {
              callback(tick): string {
                return tick.toString() + 'm';
              }
            },
            scaleLabel: {
              labelString: 'Minute',
              display: true,
            }
          }],
          yAxes: [{
            type: 'linear',
            ticks: {
              callback: function (tick) {
                return tick.toString() + 'h';
              }
            },
            scaleLabel: {
              labelString: 'Hour',
              display: true
            }
          }]
        }
      }
    });
  }
}
