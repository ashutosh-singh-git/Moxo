#!/bin/sh

mvn clean install

docker build -t moxo:v1 .

docker run --name=moxo --rm  moxo:v1
