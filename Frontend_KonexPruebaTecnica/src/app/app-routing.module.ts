import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MedicamentComponent } from './components/medicament/medicament.component';
import { SaleComponent } from './components/sale/sale.component';

const routes: Routes = [
  { path: '', redirectTo: 'Medicament', pathMatch: 'full' },
  { path: 'Medicament', component: MedicamentComponent },
  { path: 'Sale', component: SaleComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
