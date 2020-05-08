import { MessageComponent } from "./message.component";
import { RouterModule, Routes } from "@angular/router";
import { NgModule } from '@angular/core';
import {ChatComponent} from "./chat.component";

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
              component: MessageComponent,
              data: {
                breadcrumb: 'Chat'
              }
            },
            {
              path: 'api',
              // pathMatch: 'full',
              component: ChatComponent,
              data: {
                breadcrumb: 'API'
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
