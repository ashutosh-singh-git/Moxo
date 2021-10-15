## Useful Commands


###``Build and Run Application``
```mvn clean install && java -jar target/moxo-0.0.1-SNAPSHOT.jar```

###``Swagger URL``
```http://localhost:8080/swagger-ui/```

###``Create User``
```db.createUser({user: "local",pwd: "local",roles: [ "readWrite", "dbAdmin" ]});```

###```Connect to Gcloud```
```export CLOUDSDK_PYTHON=python2.7```
```gcloud container clusters get-credentials moxo-gke --zone asia-south1-a```

###``List All Pods``
```kubectl get pods```

### ``Go To Pods``
```kubectl exec -it mongo-75675c6cc-jsh4j -- /bin/bash```

###``Change Reclaim Policy of PV (Mongo)``
```kubectl get pv```

```kubectl patch pv pvc-82cab12f-3725-470a-8720-999b9ad4f38a -p '{"spec":{"persistentVolumeReclaimPolicy":"Retain"}}'```