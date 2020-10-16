## Alternatives for cassandra on Kubernetes 

* Cassandra on EC2 images 
* Amazon Keyspaces [released on 2020]
* Cassandra on k8s Cluster [EKS]/EC2 Images 
 
## Cassandra on k8s Cluster [EKS]/EC2 Images

After having a looks we think that perhaps in the future Amazon Key Spaces could be a feasible choice but not in this momment 
because its complexity.

**The 2 first rows in the table below are the EC2 amis recommended as best practice.**Those instances offer 10–Gb/s performance.**

**The 2 last row in the table below have been tested in a moderated production environment and looks quite well.**

Ec2 amis/Region Ireland:

| EC2 Ami       | vCPU          | ECU   | Memory (GiB)  | Instance Storage (GB) | Linux/UNIX Usage |
| ------------- |:-------------:| -----:|--------------:|----------------------:|-----------------:|
| c5d.9xlarge   | 36            | 139   | 72            | 1 x 900 NVMe SSD      | $1.962 per Hour  |
| i3.8xlarge    | 32            | 97    | 244           | 4 x 1900 NVMe SSD     | $1.962 per Hour  |
| m5.large      | 2             | 10    | 8             | EBS Only              | $0.107 per Hour  |
| m5.xlarge     | 4             | 16    | 16            | EBS Only              | $0.214 per Hour  |

ref. [about cassandra best practices on aws](https://aws.amazon.com/es/blogs/big-data/best-practices-for-running-apache-cassandra-on-amazon-ec2/) 

#### Backup 

* flush in-memory data (Memtables) to the disk (SSTables)


## Cassandra configuration 

ref. [about cassandra configuration](https://github.com/apache/cassandra/tree/trunk/conf)


#### Consistency Levels

Consistency Levels(number of replicas that must answer[Read/Writes]):

* ONE: Only a single replica must respond.
* TWO: Two replicas must respond.
* THREE: Three replicas must respond.
* QUORUM: A majority (n/2 + 1) of the replicas must respond.Must be rounded down. n:node. 

ref: [about tunnable consistency in cassandra](https://cassandra.apache.org/doc/latest/architecture/dynamo.html#tunable-consistency)</br>

**In an scenario with 3 nodes only 2 noders must respond so in the worst case one node canbe down and the System must work without any problem.**  
**Avoid keyspace with different replication factor. [It cans potentially cause hot spots.]**

#### Tokens

**Review token value. Default value=256/Recommended value=16 . Check algorithm how to assign tokens to nodes**</br>
ref. : https://cassandra.apache.org/doc/latest/getting_started/production.html#tokens </br>

#### Topology
**Ensure Keyspaces are Created with NetworkTopologyStrategy** </br>
ref: https://cassandra.apache.org/doc/latest/getting_started/production.html#ensure-keyspaces-are-created-with-networktopologystrategy

#### Memomry Config

**Heap size is usually between ¼ and ½ of system memory but not larger than 32 GB.**</br>
HEAP_NEWSIZE=20%MAX_HEAP_SIZE

ref: [Memory configuration in cassandra](https://github.com/apache/cassandra/blob/trunk/conf/cassandra-env.sh) 


From medium:</br>
https://medium.com/flant-com/running-cassandra-in-kubernetes-challenges-and-solutions-9082045a7d93



ref.: 
1. How To Set Up A Cluster With Even Token Distribution:</br>https://thelastpickle.com/blog/2019/02/21/set-up-a-cluster-with-even-token-distribution.html
2. How cassandra distributes token:</br>https://www.bmc.com/blogs/cassandra-tokens/
3. Resources on k8s:</br>https://kubernetes.io/docs/tasks/configure-pod-container/assign-cpu-resource/

