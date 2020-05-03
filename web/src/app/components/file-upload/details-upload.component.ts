import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'details-upload',
  template: `
    <a href="{{fileUpload}}">{{fileUpload}}</a>
  `,
  styleUrls: ['./file-upload.component.css']
})
export class DetailsUploadComponent implements OnInit {

  @Input() fileUpload: string;

  constructor() { }

  ngOnInit() {
  }

}
