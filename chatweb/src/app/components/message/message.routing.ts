import { MessageComponent } from "./message.component";
import { RouterModule, Routes } from "@angular/router";
import { NgModule } from '@angular/core';

export const messageRoutes: Routes = [
    {
        path: 'messages',
        component: MessageComponent,
        data: {
            breadcrumb: 'messages'
        },
        children: [
            {
                path: '',
                pathMatch: 'full',
                component: MessageComponent,
                data: {
                    expectedRoles: ['super', 'employee']
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
export class LotRoutingModule { }