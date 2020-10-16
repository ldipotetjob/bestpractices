== AWS ===
EBS drive 
Ephimeral SSD 
Have Strong backup and restore measures in place
- Test backup & Test Restores
Know how long it takes to boostrap/rebuild nodes and rebuild DCs
Test failure scenarios 

== Replication Factor ==
Avoid keyspace with different replication factor. [It cans potentially cause hot spots.]
Review token value. Default value = 256. Check algorithm how to assign tokens to nodes

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
