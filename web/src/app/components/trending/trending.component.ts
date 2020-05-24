import { Component, OnInit } from '@angular/core';
import {TrendingService} from './trending.service';
import {FormGroup} from '@angular/forms';
import {TimeTopicDTO, TopTopicDTO, UserTopicDTO} from './topic';
import {forEach} from '@angular/router/src/utils/collection';

@Component({
  selector: 'app-trending',
  templateUrl: './trending.component.html',
  styleUrls: ['./trending.component.css']
})
export class TrendingComponent implements OnInit {
  displayedColumns: string[] = ['position', 'name', 'count'];
  dataSource = [{}];

  timeTopicForm: FormGroup;
  userTopicForm: FormGroup;
  topTopicForm: FormGroup;

  timeTopic: TimeTopicDTO[];
  userTopic: UserTopicDTO[];
  topTopic: TopTopicDTO[];


  constructor(
    private trendingService: TrendingService,
  ) { }

  ngOnInit(){
    this.trendingService.FindTopTopics().subscribe(
      data => {
        console.log("top",data);
        this.topTopic = data;
        this.dataSource = this.setPosition(this.topTopic);
      });

    this.trendingService.FindTimeTopics().subscribe(
      data => {
        console.log(data)
        this.timeTopic = data;
      });



    this.trendingService.FindUserTopics().subscribe(
      data => {
        console.log(data)
        this.userTopic = data;
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

}
