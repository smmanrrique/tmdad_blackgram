import { Injectable } from '@angular/core';
import { Group } from './group';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class GroupService {

  constructor(
    private fb: FormBuilder
  ) { }

  getGroup(group: Group): FormGroup {
    return this.fb.group({
      id: new FormControl(group.id),
      name: new FormControl(group.name, [Validators.required, Validators.maxLength(30)]),
      user: new FormControl(group.user, [Validators.maxLength(50)]),
      // users: new FormControl(group.users, [Validators.maxLength(50)]),
    });
  }



}
