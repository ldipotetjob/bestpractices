## Dealing with cassandra backup on AWS with Nodes orchestrated on K8s

Scenario:

AWS EKS
EC2 images Amazon Linux 2
Volumes claimed: 

### All scenarios in local only tested in :

1. OS X 
2. Linux(dist.: Centos)

If you have our own cassandra Nodes instead of [AWS Cassandra Keyspaces](https://aws.amazon.com/keyspaces/) 

### For backup your data

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

### For recovery your data 

## On AWS console 

### on ec2 dashboard
1. attach you available volumen at your ec2 instance that is running at the same availability zone that your volume.
2. edit security group open ssh port 22 only for my IP address 
3. connect via ssh(ssh statement on ec2 dashboard)
4. fdisk -l (organize and review partition on your device) 
5. sudo mount volumen2mount /mnt/
6. work with volume 
7. sudo umount /mnt/


Remember that you can get easily from your AWS EC2 console the ssh command to connect to your ec2 instances

For copy directories 

......


......






ref.: Providers & Storage Class Resources:</br>
      https://kubernetes.io/docs/concepts/storage/storage-classes/#the-storageclass-resource
      
