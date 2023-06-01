import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MedicamentComponent } from './components/medicament/medicament.component';
import { SaleComponent } from './components/sale/sale.component';
import {MedicamentService} from './service/medicament.service'
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';

// Modulo Personalisado
import { PrimeNgModule } from './prime-ng/prime-ng.module';


@NgModule({
  declarations: [
    AppComponent,
    MedicamentComponent,
    SaleComponent
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    PrimeNgModule,
    AppRoutingModule,
    FormsModule,
    BrowserAnimationsModule
  ],
  providers: [
    MedicamentService,DatePipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
