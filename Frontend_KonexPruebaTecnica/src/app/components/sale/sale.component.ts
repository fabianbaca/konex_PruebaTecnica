import { DatePipe } from '@angular/common';
import { Component } from '@angular/core';
import { Sale } from 'src/app/models/sale.model';
import { SaleService } from 'src/app/service/sale.service';

@Component({
  selector: 'app-sale',
  templateUrl: './sale.component.html',
  styleUrls: ['./sale.component.css']
})
export class SaleComponent {

  sales!: Sale[];
  startDate!: Date;
  endDate!: Date;

  constructor(private SaleService: SaleService,  private datePipe: DatePipe) {}

  ngOnInit(): void {
    
    this.onGetSales();
  }

  onGetSales(): void {
    this.SaleService.getAll()
      .subscribe({
        next: (sales) => {
          console.log(sales)
          this.sales = sales;
        },
        error: (e) => console.error(e)
      });
  }


  onfindAllByDateSaleBetween(): void {
    let startDate = this.datePipe.transform(this.startDate, 'yyyy-MM-dd');
    let endDate = this.datePipe.transform(this.endDate, 'yyyy-MM-dd');
    this.SaleService.findAllByDateSaleBetween(startDate!, endDate!)
    .subscribe({
      next: (sales) => {
        this.sales = sales
      },
      error: (e) => console.error(e)
    });
  }

  onCrear() {
    this.onGetSales();
  }


}
