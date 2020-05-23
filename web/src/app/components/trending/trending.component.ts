import { Component, OnInit } from '@angular/core';
import {TrendingService} from './trending.service';

@Component({
  selector: 'app-trending',
  templateUrl: './trending.component.html',
  styleUrls: ['./trending.component.css']
})
export class TrendingComponent implements OnInit {

  constructor(
    private trendingService: TrendingService;
  ) { }

  ngOnInit()


  }

}
