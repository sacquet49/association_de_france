export class Association {
    id: string;
    titre: string;
    objet: string;
    libcom: string;
    adrsCodepostal: string;
    adr1: string;
    adr2: string;
    datePubli: string;
    telephone: string;
    email: string;
    siret: string;
    majTime: string;
}

export let query = {
    bool: {
        should: {
            multi_match: {
                query: '',
                fields: ['objet', 'titre'],
            }
        },
        must: {
            multi_match: {
                query: '',
                fields: ['libcom', 'adrs_codepostal^2']
            }
        }
    }
};

export let queryWithNoFilter = {
    multi_match: {
        query: '',
        fields: ['adrs_codepostal', 'objet^2', 'titre^3'],
    }
};
