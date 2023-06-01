import { Medicament } from "./medicament.model";

export class Sale{
    id: any;
    dateSale?: Date;
    medicament?: Medicament;
    quantity?: number;
    totalValue?: number;
    unitValue?: number;
}