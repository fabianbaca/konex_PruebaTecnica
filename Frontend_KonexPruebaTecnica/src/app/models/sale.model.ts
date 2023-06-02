import { Medicament } from "./medicament.model";

export class Sale{
    id: any;
    dateSale?: Date;
    timeSale?: String;
    medicament?: Medicament;
    quantity?: number;
    totalValue?: number;
    unitValue?: number;
}