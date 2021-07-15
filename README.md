# Quarkus Kubernetes Client Extension with Kubernetes Ipv6 Cluster

You would need a Kubernetes Cluster with Ipv6 setup. If you're using Kind, you can easily do it by following these steps:
```bash
$ cat kind-ipv6-config.yaml
kind: Cluster                                                                  apiVersion: kind.x-k8s.io/v1alpha4                                            
networking:        
  ipFamily: ipv6 

$ kind create cluster --config kind-ipv6-config.yaml   
```

## How to Run Locally?
```
mvn quarkus:dev
```

## Hot to Build Native Executable?
```
mvn package -Pnative
```
