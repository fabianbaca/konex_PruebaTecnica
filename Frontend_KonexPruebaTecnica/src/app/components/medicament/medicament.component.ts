import { DatePipe } from '@angular/common';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ConfirmationService, MessageService } from 'primeng/api';
import { Table } from 'primeng/table';
import { Medicament } from 'src/app/models/medicament.model';
import { MedicamentService } from 'src/app/service/medicament.service';

@Component({
  selector: 'app-medicament',
  templateUrl: './medicament.component.html',
  styleUrls: ['./medicament.component.css'],
  providers: [MessageService, ConfirmationService]
})
export class MedicamentComponent implements OnInit{

  @ViewChild('myInput') myInput!: ElementRef;
  medicament!: Medicament;
  medicaments!: Medicament[];
  selectedMedicament: Medicament[];
  submitted: boolean;
  medicamentDialog: boolean;
  editDialog: boolean;
  clonedProducts: { [s: string]: Medicament } = {};


  constructor(private medicamentService: MedicamentService, private messageService: MessageService, private datePipe: DatePipe) { 
    this.selectedMedicament = [];
    this.submitted = false;
    this.medicamentDialog = false;
    this.editDialog = false;
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
      }
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

  openNew() {
    this.medicament = new Medicament;
    this.submitted = false;
    this.medicamentDialog = true;
  }

  hideDialog() {
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

} 
