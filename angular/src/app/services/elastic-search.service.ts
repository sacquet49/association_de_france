import {Injectable} from '@angular/core';

import {from, Observable} from 'rxjs';

import {Client, SearchResponse} from 'elasticsearch';
import {Client as ClientJs} from 'elasticsearch-browser';

@Injectable({
    providedIn: 'root'
})
export class ElasticSearchService {

    private static readonly NB_RESULTS_PAGE: number = 250;
    private static readonly SCROLL_API_SEARCH_CONTEXT_DURATION: string = '1m';

    private client: Client;

    public getDocumentsContent<T>(searchResponse: SearchResponse<any>): Array<any> {
        return searchResponse.hits.hits ? searchResponse.hits.hits.map(v => v._source) : [];
    }

    public getDocumentsWithScrollFirstPage<T>(queryBody: any
        , documentIndex?: string | Array<string>
        , size = ElasticSearchService.NB_RESULTS_PAGE,): Observable<SearchResponse<T>> {
        // @ts-ignore
        return from(this.getElasticsearchClient().search({
            "index": documentIndex,
            "scroll": ElasticSearchService.SCROLL_API_SEARCH_CONTEXT_DURATION,
            "body": {
                "size": size,
                "query": queryBody
            }
        }));
    }

    private getElasticsearchClient(): Client {
        if (!this.client) {
            // The name of the types in not the same as the package name,
            // so we instantiate it with its JS import and use its TS type elsewhere.
            this.client = new ClientJs({
                host: 'localhost:4200'
            });
        }
        return this.client;
    }

}
