import {Component, OnInit, ViewChild} from '@angular/core';
import {TrendingService} from './trending.service';
import {FormGroup} from '@angular/forms';
import {RealTimeTopicDTO, TimeTopicDTO, TopTopicDTO, UserTopicDTO} from './topic';
import * as Chart from 'chart.js';
import {tick} from '@angular/core/testing';

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

  constructor(
    private trendingService: TrendingService,
  ) { }

  ngOnInit(){
    this.trendingService.FindTopTopics().subscribe(
      data => {
        console.log("top",data);
        this.topTopic = data;
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
        this.drag(this.realTimeTopic);
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

  public setMinute(tick: any): any{
    return tick.toString() + 'm';
  }

  public setHour(tick: any): any{
    return tick.toString() + 'm';
  }

  drag(dataTime: any) {
    this.canvas = this.mychart.nativeElement;
    this.ctx = this.canvas.getContext('2d');

    // @ts-ignore
    let myChart = new Chart(this.ctx, {
      type: 'line',

      data: {
        datasets: [{
          label: 'RealTime',
          backgroundColor: "rgba(194,180,180,0.4)",
          borderColor: "rgb(83,80,80)",
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
