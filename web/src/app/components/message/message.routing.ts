import { MessageComponent } from "./message.component";
import { RouterModule, Routes } from "@angular/router";
import { NgModule } from '@angular/core';
import {ChatComponent} from "./chat.component";
import {ApiComponent} from "./api.component";

export const messageRoutes: Routes = [
    {
        path: 'messages',
        component: MessageComponent,
        data: {
            breadcrumb: 'Messages'
        },
        children: [
            {
              path: '',
              pathMatch: 'full',
              component: ApiComponent,
              data: {
                breadcrumb: 'API'
              }
            },
            {
              path: 'api',
              component: ApiComponent,
              data: {
                breadcrumb: 'API'
              }
            },
          {
            path: 'chats',
            component: ChatComponent,
            data: {
              breadcrumb: 'Chats'
            }
          }
        ]
    }
];

@NgModule({
    imports: [
        RouterModule.forChild(messageRoutes)
    ],
    exports: [
        RouterModule
    ]
})
export class MessageRoutingModule { }
