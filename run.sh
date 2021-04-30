#!/bin/sh

##docker build -t moxo:v1 .
## Jib Alternative
mvn compile jib:dockerBuild

docker run --name=moxo --rm  gcr.io/bikerapp-e3150/moxo:latest
