# Best Practices - k8s 

## Basic 

* Don't use nacked pods 
* Create a Service before its corresponding backend workloads (Deployments or ReplicaSets), and before any workloads that need to access it.
* An optional (though strongly recommended) cluster add-on is a DNS server
* Don't specify a hostPort for a Pod unless it is absolutely necessary
* Avoid using hostNetwork
* Use headless Services (which have a ClusterIP of None) for easy service discovery when you don't need kube-proxy load balancing.
* Define and use labels that identify semantic attributes of your application or Deployment, such as { app: myapp, tier: frontend, phase: test, deployment: v3 }
* Have a look at the imagePullPolicy that fit better to your installation. ref: https://kubernetes.io/docs/concepts/containers/images/
* Create namespace. Decide when create + namespaces. ref: https://kubernetes.io/docs/concepts/overview/working-with-objects/namespaces/#when-to-use-multiple-namespaces
* Use label and selector whenever be possible. ref: https://kubernetes.io/docs/concepts/overview/working-with-objects/common-labels/
* Prepare you updating image policy. ref: https://kubernetes.io/docs/concepts/containers/images/#updating-images 

## Loggin 

* Set Up loggin System 
* Establish a Retention Mechanism => explanation ... adptar a nuestros logs: Whether you choose to perform logging internally or use a third-party service, logs still eat up a lot of space. Therefore, make sure you have a clear retention policy that stores logs for future use.
* Write Logs to Stdout and Stderr => explanation ... Although it’s standard practice when moving to a containerized environment, 
some companies still write apps that log to files. Redirecting logs to stdout and stderr allows Kubernetes’ centralized logging framework to come into play and automatically stream the logs to any desired location. ref: https://kubernetes.io/docs/concepts/cluster-administration/logging/


## Security ## 

* Upgrade to the latest version
* Enable RBAC
* Implement Continuous Security Vulnerability Scanning
* Regularly Apply Security Updates to Your Environment
* Ensure That Only Authorized Images are Used in Your Environment
* Limit Direct Access to Kubernetes Nodes
* Create Administrative Boundaries between Resources
* Define Resource Quota
* Apply Security Context to Your Pods and Containers
* Implement Network Segmentation

ref: https://kubernetes.io/blog/2018/07/18/11-ways-not-to-get-hacked/#2-enable-rbac-with-least-privilege-disable-abac-and-monitor-logs

## Operators 

An Operator extends Kubernetes to automate the management of the entire life cycle of a particular application. 
Operators serve as a packaging mechanism for distributing applications on Kubernetes, and they monitor, maintain, 
recover, and upgrade the software they deploy.

## Appendices

k8s best practices for deployements => https://kubernetes.io/blog/2016/08/security-best-practices-kubernetes-deployment/

#### Templates

Deployments => [ https://kubernetes.io/docs/concepts/workloads/controllers/deployment/#creating-a-deployment ]

StateFullSet => [ https://kubernetes.io/docs/concepts/workloads/controllers/statefulset/#components ]

Services => [ https://kubernetes.io/docs/concepts/services-networking/service/ ]


####  Connecting app with Services

ref: https://kubernetes.io/docs/concepts/services-networking/connect-applications-service/

####  Exposing services in the k8s cluster to external addresses

ref: https://kubernetes.io/docs/concepts/services-networking/ingress/

####  EBS Volume

ref: https://kubernetes.io/docs/concepts/storage/volumes/#awselasticblockstore

####  Storage Class

ref: https://kubernetes.io/docs/concepts/storage/storage-classes/#the-storageclass-resource

#### PVC

ref: https://kubernetes.io/docs/concepts/storage/persistent-volumes/

#### test environment - cluster-k8s-minikube
ref: https://minikube.sigs.k8s.io/docs/commands/start/

[kubectl commands](https://kubernetes.io/docs/reference/generated/kubectl/kubectl-commands)
[kubectl cheatsheet](https://kubernetes.io/docs/reference/kubectl/cheatsheet/)
[k8s guess book example - go](https://github.com/kubernetes/examples/tree/master/guestbook-go)

ref: https://kubernetes.io/docs/concepts/configuration/overview/#container-images

ref: https://kubernetes.io/docs/concepts/configuration/overview/#general-configuration-tips
