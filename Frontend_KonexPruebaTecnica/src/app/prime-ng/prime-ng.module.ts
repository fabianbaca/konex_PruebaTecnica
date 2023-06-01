import { NgModule } from '@angular/core';

import { TableModule } from 'primeng/table';
import {ToastModule} from 'primeng/toast';
import {ButtonModule} from 'primeng/button'
import {ToolbarModule} from 'primeng/toolbar'
import {DialogModule} from 'primeng/dialog'
import {InputNumberModule} from 'primeng/inputnumber';
import {InputTextModule} from 'primeng/inputtext';
import {CalendarModule} from 'primeng/calendar';
import {MenubarModule} from 'primeng/menubar'

@NgModule({
  exports: [
    TableModule,
    ToastModule,
    ButtonModule,
    ToolbarModule,
    DialogModule,
    InputNumberModule,
    InputTextModule,
    CalendarModule,
    MenubarModule
  ]
})
export class PrimeNgModule { }
