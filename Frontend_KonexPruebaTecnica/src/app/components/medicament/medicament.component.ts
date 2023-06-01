import { DatePipe } from '@angular/common';
import { Component, OnInit, ViewChild } from '@angular/core';
import { ConfirmationService, MessageService } from 'primeng/api';
import { Table } from 'primeng/table';
import { Medicament } from 'src/app/models/medicament.model';
import { Sale } from 'src/app/models/sale.model';
import { MedicamentService } from 'src/app/service/medicament.service';
import { SaleService } from 'src/app/service/sale.service';

@Component({
  selector: 'app-medicament',
  templateUrl: './medicament.component.html',
  styleUrls: ['./medicament.component.css'],
  providers: [MessageService, ConfirmationService]
})
export class MedicamentComponent implements OnInit{

  @ViewChild('dt') dt: Table | undefined;
  medicament!: Medicament;
  medicaments!: Medicament[];
  selectedMedicament: Medicament[];
  submitted: boolean;
  medicamentDialog: boolean;
  sale!: Sale;
  saledialog: boolean;
  editDialog: boolean;
  clonedProducts: { [s: string]: Medicament } = {};


  constructor(private medicamentService: MedicamentService, private SaleService: SaleService, private messageService: MessageService, private datePipe: DatePipe) { 
    this.selectedMedicament = [];
    this.submitted = false;
    this.medicamentDialog = false;
    this.editDialog = false;
    this.saledialog = false;
  }

  ngOnInit(): void {
    this.onGetMedicament();
  }


  onGetMedicament(): void {
    this.medicamentService.getAll()
      .subscribe({
        next: (medicaments) => {
          console.log(medicaments)
          this.medicaments = medicaments;
        },
        error: (e) => console.error(e)
      });
  }

  clickSaveDialog() {
    if(this.editDialog) {
      this.editMedicament();
    } else {
      this.saveMedicament()
    }
  }

  saveMedicament() {
    this.submitted = true;

    if (this.medicament.name!.trim()) {
      let existsMedicament =this.medicaments.find((val) => val.name == this.medicament.name)
      if(existsMedicament) {
        this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El nombre del medicamenton no se puede repetir'})
      } else {
        this.medicamentService.create(this.medicament).subscribe({
          next: () => {
            this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Se Registro correctamente' });
                this.medicaments = [...this.medicaments];
                this.medicamentDialog = false;
                this.medicament = new Medicament;
                this.refreshList();
              },
              error: (e) =>  {
                console.log(e)
                this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Error al Registrar'})
              }
            
        });
      }
    }
  }

  openNew() {
    this.medicament = new Medicament;
    this.submitted = false;
    this.medicamentDialog = true;
  }

  hideDialog() {
    this.saledialog =false;
    this.medicamentDialog = false;
    this.submitted = false;
  }

  onEditMedicament(medicamentEdit: Medicament) {
    this.medicament = { ...medicamentEdit };
    this.medicamentDialog = true;
    this.editDialog = true;
  }

  editMedicament() {
    if (this.medicament?.unitValue! > 0) {
        this.medicamentService.update(this.medicament.id, this.medicament).subscribe({
          next: () => {
            this.editDialog=false;
            this.medicaments = [...this.medicaments];
            this.medicamentDialog = false;
            this.medicament = new Medicament;
            this.refreshList();
            this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Medicamento modificado correctamente' });
          },
          error: (e) => this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Error al modificar'})
        });
    } else {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Invalid unit value' });
    }
}

  deleteMedicament(medicamentDelete: Medicament) {
    this.medicamentService.delete(medicamentDelete.id).subscribe({
      next: (res) => {
        this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Se elimino correctamente' })
        this.refreshList();
      },
      error: (e) => this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Error al Eliminar'})
    });
  }

  refreshList(): void {
    this.onGetMedicament();
  }

  applyFilterGlobal($event: any, stringVal: any) {
    this.dt!.filterGlobal(($event.target as HTMLInputElement).value, stringVal);
  }

  openDialogSale(medicamentSale: Medicament) {
    if(medicamentSale?.quantityStock! >= 1) {
      this.medicament = medicamentSale;
      this.sale = new Sale;
      this.submitted = false;
      this.saledialog = true;
    }  else {
      this.messageService.add({ severity: 'info', summary: 'info', detail: 'el medicamento '+medicamentSale.name+' no tiene cantidad disponible para la venta'})
    }
  }

  TotalValue($event: any, stringVal: any) {
    let cantida =  $event.target.value as number;
    if(cantida>= 0) {
      let totalValue = this.medicament?.unitValue!*cantida;
      this.sale.totalValue = totalValue;
    } 
  }


  saveSale() {
    this.submitted = true;
    if(this.sale?.quantity! >= 0 && this.sale?.totalValue! >= 0 && this.sale?.quantity! <= this.medicament?.quantityStock!) {
      this.medicament.quantityStock = this.medicament?.quantityStock!-this.sale?.quantity!;
      this.sale.medicament = this.medicament;
      this.sale.unitValue = this.medicament.unitValue;
      console.log(this.sale);
      this.sale.dateSale = new Date();
      this.SaleService.create(this.sale).subscribe({
        next: () => {
          this.saledialog = false;
          this.sale = new Sale;
          this.medicament = new Medicament;
          this.refreshList();
          this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Se creo la venta correctamente' });
        },
        error: (e) => console.log(e)
      });
    }
  }
} 
