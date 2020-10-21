### Dealing with cassandra backup on AWS with Nodes orchestrated on K8s

Scenario:

AWS EKS
EC2 images Amazon Linux 2
Volumes claimed: 

If you have our own cassandra Nodes instead of [AWS Cassandra Keyspaces](https://aws.amazon.com/keyspaces/) 

You can shedule your backups our any other mechanism for execute the process periodically but the process
must follow these steps: </br>

1. nodetool drain( it makes a Cassandra node stop receiving connections and become unreachable — an unwanted behaviour in most cases.)
2. nodetool cleanup -- <keyspace_name>
3. nodetool flush -- <keyspace_name> 
4. nodetool snapshot --tag <mnemonic_name> <keyspace_name>



Before Shutdown your Cluster follow the following instrunction:
- Pay attention don't delete the data volumenes that contain our our data 

.....
        volumeMounts:
        - name: cassandra-data
          mountPath: /var/lib/cassandra
.....

We need to attach the volume 
To list partition tables and partitions on a device
fdisk -l 




ref.: Providers & Storage Class Resources:</br>
      https://kubernetes.io/docs/concepts/storage/storage-classes/#the-storageclass-resource
      
