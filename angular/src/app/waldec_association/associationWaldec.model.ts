export class AssociationWaldec {
    id: string;
    titre: string;
    objet: string;
    adrsLibcommune: string;
    adrsCodepostal: string;
    adrsNumvoie: string;
    adrsTypevoie: string;
    adrsLibvoie: string;
    datePubli: string;
    telephone: string;
    email: string;
    siret: string;
    majTime: string;
    groupement: string;
    position: string;
    adrs_numvoie: string;
    adrs_typevoie: string;
    adrs_libvoie: string;
    adrs_libcommune: string;
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
                fields: ['adrs_libcommune', 'adrs_codepostal^2']
            }
        }
    }
};

export let queryWithNoFilter = {
    multi_match: {
        query: '',
        fields: ['adrs_libcommune', 'objet^2', 'titre^3'],
    }
};
