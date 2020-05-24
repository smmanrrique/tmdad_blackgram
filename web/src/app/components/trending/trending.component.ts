import {Component, OnInit, ViewChild} from '@angular/core';
import {TrendingService} from './trending.service';
import {FormGroup} from '@angular/forms';
import {RealTimeTopicDTO, TimeTopicDTO, TopTopicDTO, UserTopicDTO} from './topic';
import {forEach} from '@angular/router/src/utils/collection';
import Chart = require('chart.js');

@Component({
  selector: 'app-trending',
  templateUrl: './trending.component.html',
  styleUrls: ['./trending.component.css']
})
export class TrendingComponent implements OnInit {
  displayedColumns: string[] = ['position', 'name', 'count'];

  timeTopicForm: FormGroup;
  userTopicForm: FormGroup;
  topTopicForm: FormGroup;

  timeTopic: TimeTopicDTO[];
  userTopic: UserTopicDTO[];
  topTopic: TopTopicDTO[];
  realTimeTopic: RealTimeTopicDTO[];

  canvas: any;
  ctx: any;
  @ViewChild('mychart') mychart;


  // data: [
  //   { x: 1, y: 2 },
  //   { x: 2500, y: 2.5 },
  //   { x: 3000, y: 5 },
  //   { x: 3400, y: 4.75 },
  //   { x: 3600, y: 4.75 },
  //   { x: 5200, y: 6 },
  //   { x: 6000, y: 9 },
  //   { x: 7100, y: 6 },
  // ],

  constructor(
    private trendingService: TrendingService,
  ) { }

  ngOnInit(){
    this.trendingService.FindTopTopics().subscribe(
      data => {
        console.log("top",data);
        this.topTopic = data;
        // this.dataSource = this.setPosition(this.topTopic);
      });

    this.trendingService.FindUserTopics().subscribe(
      data => {
        console.log(data)
        this.userTopic = data;
      });

    this.trendingService.FindRealTimeTopics().subscribe(
      data => {
        console.log(data)
        this.realTimeTopic = data;
        console.log("this.realTimeTopic", this.realTimeTopic)
      });

    this.trendingService.FindTimeTopics().subscribe(
      data => {
        console.log(data)
        this.timeTopic = data;
      });

  }

  title = 'app';
  public pieChartLabels:string[] = ["Pending", "InProgress", "OnHold", "Complete", "Cancelled"];
  public pieChartData:number[] = [21, 39, 10, 14, 16];
  public pieChartType:string = 'pie';
  public pieChartOptions:any = {'backgroundColor': [
      "#FF6384",
      "#4BC0C0",
      "#FFCE56",
      "#E7E9ED",
      "#36A2EB"
    ]}

  // events on slice click
  public chartClicked(e:any):void {
    console.log(e);
  }

  // event on pie chart slice hover
  public chartHovered(e:any):void {
    console.log(e);
  }

  public setPosition(data: any): any{
    let n = data.length;
    for (let i = 0; i < n; i++) {
      data[i].position = i+1
    }
    return data;
  }

  ngAfterViewInit() {
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
          data: [
            {
              "x": 8,
              "y": 45
            },
            {
              "x": 9,
              "y": 25
            },
            {
              "x": 9,
              "y": 45
            },
            {
              "x": 10,
              "y": 50
            },
            {
              "x": 11,
              "y": 25
            },
            {
              "x": 11,
              "y": 45
            },
            {
              "x": 11,
              "y": 55
            },
            {
              "x": 11,
              "y": 60
            },
            {
              "x": 12,
              "y": 15
            },
            {
              "x": 12,
              "y": 25
            },
            {
              "x": 12,
              "y": 35
            },
            {
              "x": 12,
              "y": 55
            }],
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
              userCallback: function (tick):string {
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
              userCallback: function (tick) {
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
