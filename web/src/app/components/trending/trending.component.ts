import { Component, OnInit } from '@angular/core';
import {TrendingService} from './trending.service';
import {FormGroup} from '@angular/forms';
import {TimeTopicDTO, TopTopicDTO, UserTopicDTO} from './topic';

class PeriodicElement {
}

const ELEMENT_DATA: PeriodicElement[] = [
  {position: 1, name: 'Hydrogen', count: 1.0079, symbol: 'H'},
  {position: 2, name: 'Helium', count: 4.0026, symbol: 'He'},
  {position: 3, name: 'Lithium', count: 6.941, symbol: 'Li'},
  {position: 4, name: 'Beryllium', count: 9.0122, symbol: 'Be'},
  {position: 5, name: 'Boron', count: 10.811, symbol: 'B'},
  {position: 6, name: 'Carbon', count: 12.0107, symbol: 'C'},
  {position: 7, name: 'Nitrogen', count: 14.0067, symbol: 'N'},
  {position: 8, name: 'Oxygen', count: 15.9994, symbol: 'O'},
  {position: 9, name: 'Fluorine', count: 18.9984, symbol: 'F'},
  {position: 10, name: 'Neon', count: 20.1797, symbol: 'Ne'},
];


@Component({
  selector: 'app-trending',
  templateUrl: './trending.component.html',
  styleUrls: ['./trending.component.css']
})
export class TrendingComponent implements OnInit {
  displayedColumns: string[] = ['position', 'name', 'count'];
  dataSource = ELEMENT_DATA;

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

    this.trendingService.FindTimeTopics().subscribe(
      data => {
        console.log(data)
        this.timeTopic = data;
      });

    this.trendingService.FindTopTopics().subscribe(
      data => {
        console.log(data)
        this.topTopic = data;
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

}
