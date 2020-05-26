import {Component, OnInit, ViewChild} from '@angular/core';
import {TrendingService} from './trending.service';
import {FormGroup} from '@angular/forms';
import {RealTimeTopicDTO, TimeTopicDTO, TopTopicDTO, UserTopicDTO} from './topic';
import {Globals} from '../../globals';
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
  setInterval = setInterval;

  constructor(
    private trendingService: TrendingService,
    globals: Globals
  ) { this.globals = globals;}

  ngOnInit(){
    this.user = JSON.parse(sessionStorage.getItem('user'));
    console.log("Starting to request websocket...")
    this.connect();
    setInterval(this.draw, 10000, this.globals.realTimeTopic);
  }

  connect() {
    this.trendingService.FindTopTopics().subscribe(
      data => {
        console.log("top",data);
        this.globals.topTopic = data;
      });


    this.trendingService.FindRecebtTopics().subscribe(
      data => {
        console.log("top recent",data);
        this.globals.recentTopic = data;
      });

    this.trendingService.FindRealTimeTopics().subscribe(
      data => {
        console.log(data);
        this.globals.realTimeTopic = data;
        this.draw(this.realTimeTopic);
        console.log("this.realTimeTopic", this.realTimeTopic);
      });

    this.trendingService.FindUserTopics().subscribe(
      data => {
        console.log(data);
        this.globals.userTopic = data;
      });

    this.trendingService.FindRealTimeTopics().subscribe(
      data => {
        console.log(data);
        this.globals.realTimeTopic = data;
        console.log("this.realTimeTopic", this.realTimeTopic)
      });

    this.trendingService.FindTimeTopics().subscribe(
      data => {
        console.log(data);
        this.globals.timeTopic = data;
      });
  }

  draw(dataTime: any) {
    console.log("Executing...")
    this.canvas = this.mychart.nativeElement;
    this.ctx = this.canvas.getContext('2d');

    let myChart = new Chart(this.ctx, {
      type: 'line',

      data: {
        datasets: [{
          label: 'RealTime',
          backgroundColor: "rgba(194,180,180,0.4)",
          borderColor: "rgb(83,80,80)",
          fill: true,
          data: this.globals.realTimeTopic,
        }]
      },
      options: {
        responsive: true,
        title: {
          display: true,
          text: 'Trendings'
        },
        scales: {
          xAxes: [{
            type: 'linear',
            position: 'bottom',
            ticks: {
              callback: function (tick):string {
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
