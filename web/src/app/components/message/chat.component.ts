import { Component, OnInit } from '@angular/core';
import { User } from '../auth/user-register/user';
import { Group } from '../group/group';
import {Sms} from "../group/group.component";

@Component({
  // selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./message.component.css']
  // styleUrls: ['../group/group.component.css']
})

export class ChatComponent implements OnInit {

  // dataSource = new MatTableDataSource<Message>();
  // dataSource = new MatTableDataSource<Sms>();
  selectedGroup: Group;

  User = new User();

  // @ts-ignore
  groups_test: Group[] = [
    { name: "g1", description: "ADD_USER_TO_SYSTEM", user: "[{}]" },
    { name: "g2", description: "ADD_USER_TO_SYSTEM", user: "[]" },
    { name: "g4", description: "ADD_USER_TO_SYSTEM", user: "[]" },
    { name: "g3", description: "ADD_USER_TO_SYSTEM", user: "[]" },
    { name: "g3", description: "ADD_USER_TO_SYSTEM", user: "[]" },
    { name: "g3", description: "ADD_USER_TO_SYSTEM", user: "[]" },
    { name: "g3", description: "ADD_USER_TO_SYSTEM", user: "[]" },
    { name: "g3", description: "ADD_USER_TO_SYSTEM", user: "[]" },
    { name: "g3", description: "ADD_USER_TO_SYSTEM", user: "[]" },
    { name: "g3", description: "ADD_USER_TO_SYSTEM", user: "[]" },
    { name: "g3", description: "ADD_USER_TO_SYSTEM", user: "[]" },
    { name: "g3", description: "ADD_USER_TO_SYSTEM", user: "[]" },
    { name: "g3", description: "ADD_USER_TO_SYSTEM", user: "[]" },
    { name: "g3", description: "ADD_USER_TO_SYSTEM", user: "[]" },
    { name: "g3", description: "ADD_USER_TO_SYSTEM", user: "[]" },
    { name: "g3", description: "ADD_USER_TO_SYSTEM", user: "[]" },
    { name: "g3", description: "ADD_USER_TO_SYSTEM", user: "[]" },
    { name: "g3", description: "ADD_USER_TO_SYSTEM", user: "[]" },
    { name: "g3", description: "ADD_USER_TO_SYSTEM", user: "[]" },
  ];

  sms: Sms[] = [
    {fromUser: 'U1 SHAMUEL AMS', body: 'Pfdgsdfgdfsgese a los datos oficiales, desde el inicio de la emergencia sanitaria el líder del Partido Popular, Pablo Casado, ha considerado que la gestión de la pandemia y las políticas puestas en marcha por Díaz Ayuso son un "ejemplo a seguir". "Es lo que haríamos a nivel nacional", apuntó el líder del PP el pasado sábado, tras participar en el acto oficial de la comunidad con motivo de la festividad del 2 de mayo.', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U1 SHAMUEL AMS', body: 'Pfdgsdfgdfsgese a los datos oficiales, desde el inicio de la emergencia sanitaria el líder del Partido Popular, Pablo Casado, ha considerado que la gestión de la pandemia y las políticas puestas en marcha por Díaz Ayuso son un "ejemplo a seguir". "Es lo que haríamos a nivel nacional", apuntó el líder del PP el pasado sábado, tras participar en el acto oficial de la comunidad con motivo de la festividad del 2 de mayo.', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U1 SHAMUEL AMS', body: 'Pfdgsdfgdfsgese a los datos oficiales, desde el inicio de la emergencia sanitaria el líder del Partido Popular, Pablo Casado, ha considerado que la gestión de la pandemia y las políticas puestas en marcha por Díaz Ayuso son un "ejemplo a seguir". "Es lo que haríamos a nivel nacional", apuntó el líder del PP el pasado sábado, tras participar en el acto oficial de la comunidad con motivo de la festividad del 2 de mayo.', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U1 SHAMUEL AMS', body: 'Pfdgsdfgdfsgese a los datos oficiales, desde el inicio de la emergencia sanitaria el líder del Partido Popular, Pablo Casado, ha considerado que la gestión de la pandemia y las políticas puestas en marcha por Díaz Ayuso son un "ejemplo a seguir". "Es lo que haríamos a nivel nacional", apuntó el líder del PP el pasado sábado, tras participar en el acto oficial de la comunidad con motivo de la festividad del 2 de mayo.', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U1 SHAMUEL AMS', body: 'Pfdgsdfgdfsgese a los datos oficiales, desde el inicio de la emergencia sanitaria el líder del Partido Popular, Pablo Casado, ha considerado que la gestión de la pandemia y las políticas puestas en marcha por Díaz Ayuso son un "ejemplo a seguir". "Es lo que haríamos a nivel nacional", apuntó el líder del PP el pasado sábado, tras participar en el acto oficial de la comunidad con motivo de la festividad del 2 de mayo.', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U1 SHAMUEL AMS', body: 'Pfdgsdfgdfsgese a los datos oficiales, desde el inicio de la emergencia sanitaria el líder del Partido Popular, Pablo Casado, ha considerado que la gestión de la pandemia y las políticas puestas en marcha por Díaz Ayuso son un "ejemplo a seguir". "Es lo que haríamos a nivel nacional", apuntó el líder del PP el pasado sábado, tras participar en el acto oficial de la comunidad con motivo de la festividad del 2 de mayo.', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U1 SHAMUEL AMS', body: 'Pfdgsdfgdfsgese a los datos oficiales, desde el inicio de la emergencia sanitaria el líder del Partido Popular, Pablo Casado, ha considerado que la gestión de la pandemia y las políticas puestas en marcha por Díaz Ayuso son un "ejemplo a seguir". "Es lo que haríamos a nivel nacional", apuntó el líder del PP el pasado sábado, tras participar en el acto oficial de la comunidad con motivo de la festividad del 2 de mayo.', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U1 SHAMUEL AMS', body: 'Pfdgsdfgdfsgese a los datos oficiales, desde el inicio de la emergencia sanitaria el líder del Partido Popular, Pablo Casado, ha considerado que la gestión de la pandemia y las políticas puestas en marcha por Díaz Ayuso son un "ejemplo a seguir". "Es lo que haríamos a nivel nacional", apuntó el líder del PP el pasado sábado, tras participar en el acto oficial de la comunidad con motivo de la festividad del 2 de mayo.', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U2', body: 'rgsdfgs', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
    {fromUser: 'U3', body: 'gsdfg', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
      {fromUser: 'U1', body: 'asssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss', topics: '1.0079', multimedia: 'https://th.bing.com/th/id/OIP.8AjxN0zjW0u8Y2t-hgdSXQHaEK?w=300&h=168&c=7&o=5&pid=1.7'},
  ];

  displayedColumns: string[] = ['message.fromUser', 'message'];

  g1 = new Group();
  dataSource = this.sms;
  ngOnInit() {
    // this.dataSource.data = this.sms;
  }

  constructor() { }

  onSelect(group: Group): void {
    this.selectedGroup = group;
  }

}