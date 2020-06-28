#!/bin/bash

VERSION=$1
docker build --no-cache -t booksplatform:0.0.$VERSION -f Dockerfile .
docker run --rm -p 3000:3000 -p 8080:8080 --name booksplatform-0.0.$VERSION booksplatform:0.0.$VERSION