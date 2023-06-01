import { Medicament } from "./medicament.model";

export class Sale{
    id: any;
    dateSale?: string;
    medicamente?: Medicament;
    quantity?: Number;
    totalValue?: number;
    unitValue?: number;
}