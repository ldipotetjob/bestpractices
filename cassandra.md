## Alternatives for cassandra on Kubernetes 

* Cassandra on EC2 images 
* Amazon Keyspaces [released on 2020]
* Cassandra on k8s Cluster [EKS]/EC2 Images 
 
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

Consistency Levels(number of replicas that must answer[Read/Writes]):

ONE: Only a single replica must respond.</br>
TWO: Two replicas must respond.</br>
THREE: Three replicas must respond.</br> 
QUORUM: A majority (n/2 + 1) of the replicas must respond. Must be rounded down.</br></br> 

QUORUM: In an scenario with 3 nodes only 2 noders must respond so in the worst case one node canbe down and the System must work without any problem.</br></br>  

#### Replication Factor

* Avoid keyspace with different replication factor. [It cans potentially cause hot spots.]

#### Tokens

* Review token value. Default value = 256. Check algorithm how to assign tokens to nodes
ref. : https://cassandra.apache.org/doc/latest/getting_started/production.html#tokens </br>

**Ensure Keyspaces are Created with NetworkTopologyStrategy** </br>
ref: https://cassandra.apache.org/doc/latest/getting_started/production.html#ensure-keyspaces-are-created-with-networktopologystrategy

ref. [about cassandra best practices on aws](https://aws.amazon.com/es/blogs/big-data/best-practices-for-running-apache-cassandra-on-amazon-ec2/)</br>
     [about tunnable consistency in cassandra](https://cassandra.apache.org/doc/latest/architecture/dynamo.html#tunable-consistency)</br>

ref. [about cassandra configuration](https://github.com/apache/cassandra/tree/trunk/conf)

ref. [Check algorithm how to assign tokens to nodes](https://www.datastax.com/blog/new-token-allocation-algorithm-cassandra-30)





== AWS ===
EBS drive 
Ephimeral SSD 
Have Strong backup and restore measures in place
- Test backup & Test Restores
Know how long it takes to boostrap/rebuild nodes and rebuild DCs
Test failure scenarios 

===

Backups 
ref: https://medium.com/flant-com/running-cassandra-in-kubernetes-challenges-and-solutions-9082045a7d93
...

Let me remind you that Cassandra stores parts of the data in memory. To do a full backup, you have to 
flush in-memory data (Memtables) to the disk (SSTables). In Cassandra’s terminology, that process is
called a “nodetool drain”: it makes a Cassandra node stop receiving connections and become
unreachable — an unwanted behaviour in most cases.
...


ref.: 
[ https://thelastpickle.com/blog/2019/02/21/set-up-a-cluster-with-even-token-distribution.html ]
[ https://www.bmc.com/blogs/cassandra-tokens/ ]
[ https://danielparker.me/cassandra/vnodes/til/vnodes-in-cassandra/ ]
