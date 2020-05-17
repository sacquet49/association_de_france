#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import json
import psycopg2
from collections import deque
from io import StringIO
import glob
import os
from elasticsearch import Elasticsearch, helpers

with open('conf.json', encoding="utf8", errors='ignore') as json_file:
    conf = json.load(json_file)

client = Elasticsearch(conf["elastic_host"])
conn = psycopg2.connect("host=%s dbname=%s user=%s password=%s port=%s" % (conf["DB_HOST"],
                                                                           conf["DB_DATABASE"],
                                                                           conf["DB_USER"],
                                                                           conf["DB_PASSWORD"],
                                                                           conf["DB_PORT"]))

def bulk(docs, indexName):
    try:
        deque(helpers.parallel_bulk(
            client,
            docs,
            thread_count=4,
            chunk_size=10000,
            queue_size=4,
            index=indexName
        ), maxlen=0)

    except Exception as err:
        print("Elasticsearch helpers.bulk() ERROR:", err)
        quit()


for pathOfFile in glob.glob("./mapping/*_mapping.json"):

    filenames = os.path.basename(pathOfFile).replace(".json", "").replace("_mapping", "")
    with open(pathOfFile, encoding="utf8", errors='ignore') as json_file:
        mapping = json.load(json_file)
    cur = conn.cursor()
    cur.execute(mapping["sql"])
    docs = cur.fetchall()
    cur.close()

    io = StringIO(json.dumps(docs))
    associations = json.load(io)[0][0]

    doc_list = []
    for num, doc in enumerate(associations):
        try:
            doc["_id"] = doc["id"]
            doc_list += [doc]

        except json.decoder.JSONDecodeError as err:
            print("ERROR for num:", num, "-- JSONDecodeError:", err, "for doc:", doc)
            print("Dict docs length:", len(doc_list))

        if (num % 10000) == 1:
            bulk(doc_list, filenames)
            doc_list = []

    bulk(doc_list, filenames)


