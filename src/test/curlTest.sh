#!/bin/bash

DEST='localhost:8080'

echo -e "\n> sanity test"
curl "$DEST/greeting"

echo -e "\n> name test"
curl "$DEST/greeting?name=steve"

echo -e "\n> store test"
curl "$DEST/store"

echo -e "\n> To upload a file like hello.test"
rm -rf hello.test
echo "hello world" > hello.test
curl -F "file=@hello.test" "$DEST/store"

echo -e "\n> To retrieve a uploaded file like hello.test"
curl "$DEST/store/files/hello.test"
