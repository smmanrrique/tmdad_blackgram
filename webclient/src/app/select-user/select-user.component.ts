import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-select-user',
  templateUrl: './select-user.component.html',
  styleUrls: ['./select-user.component.css']
})
export class SelectUserComponent implements OnInit {

  selectedValue: string;
  options: Array<Object>;

  constructor() { }

  ngOnInit() {
    this.options = [
      { id: 0, name: 'aldrix' },
      { id: 1, name: 'shamuel' },
      { id: 2, name: 'gabriel' },
      { id: 3, name: 'charles' },
  ]

    this.selectedValue = this.options[1]['name'];
  }

  saveUser() {
    sessionStorage.setItem('user', this.selectedValue);
    sessionStorage.setItem('token', btoa(this.selectedValue + ':' + '1234'));
  }
}
