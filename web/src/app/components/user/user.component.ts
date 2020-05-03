import { FilterService } from './../../core/utils/filter/filter.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user',
  template: '<router-outlet></router-outlet>',
  styleUrls: ['./user.component.css'],
  providers: [FilterService],
})
export class UserComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
