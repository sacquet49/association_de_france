export class AssociationStat {
    departement: string;
    count: number;
}

export class DataChartBar {
    labels: string[];
    datasets: DataSetBar[];
}

export class DataSetBar {
    label: string;
    borderColor: string;
    backgroundColor: string;
    data: number[];
}
