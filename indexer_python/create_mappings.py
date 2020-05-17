#!/usr/bin/env python3
#-*- coding: utf-8 -*-

import json
import glob
import os
from elasticsearch import Elasticsearch
with open('conf.json', encoding="utf8", errors='ignore') as json_file:
    conf = json.load(json_file)
client = Elasticsearch(conf["elastic_host"])

for pathOfFile in glob.glob("./mapping/*_mapping.json"):

    filenames = os.path.basename(pathOfFile)

    with open(pathOfFile, encoding="utf8", errors='ignore') as json_file:
        mapping = json.load(json_file)

    response = client.indices.create(
        index=filenames.replace(".json", "").replace("_mapping", ""),
        body=mapping["mapping"],
        ignore=400 # ignore 400 already exists code
    )

    if 'acknowledged' in response:
        if response['acknowledged'] == True:
            print ("INDEX MAPPING SUCCESS FOR INDEX:", response['index'])

    # catch API error response
    elif 'error' in response:
        print ("ERROR:", response['error']['root_cause'])
        print ("TYPE:", response['error']['type'])