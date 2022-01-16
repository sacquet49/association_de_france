export class Nouvelle {
    id: number;
    dateCreation: string;
    description: string;
    titre: string;
}

export class User {
    id: number;
    username: string;
    isActive: string;
    roles: string;
}

export class SousCategorie {
    id: number;
    titre: string;
}

export class Categorie {
    id: number;
    titre: string;
    sousCategories: SousCategorie[];
}
