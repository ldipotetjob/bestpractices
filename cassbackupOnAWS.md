## Dealing with cassandra backup on AWS with Nodes orchestrated on K8s

Scenario on production:</br>
* AWS EKS
* EC2 images Amazon Linux 2
* Volumes claimed:  

### All scenarios in local only tested in :

1. OS X 
2. Linux(dist.: Centos)

If you have our own cassandra Nodes instead of [AWS Cassandra Keyspaces](https://aws.amazon.com/keyspaces/) 

### For backup your data

You can shedule your backups with any other mechanism for execute the process periodically but the process
must follow these steps: </br>

1. nodetool drain( it makes a Cassandra node stop receiving connections and become unreachable — an unwanted behaviour in most cases.)
2. nodetool cleanup -- <keyspace_name>
3. nodetool flush -- <keyspace_name> 
4. Type of backup</br>
    4.1 nodetool snapshot --tag <mnemonic_name> <keyspace_name> </br>
    4.2 incremental backup(must be configured on cassandra.yaml) </br> 

Before Shutdown your Cluster follow the following instrunction:
- Pay attention don't delete the data volumenes that contain our our data 

volumeMounts:
        - name: cassandra-data
          mountPath: /var/lib/cassandra

## On AWS console 

### on ec2 dashboard get the info stored on ebs volume or ebs snapshot 
1. attach you available volumen at your ec2 instance that is running at the same availability zone that your volume.
2. edit security group open ssh port 22 only for my IP address 
3. connect via ssh(ssh statement on ec2 dashboard)
4. fdisk -l (organize and review partition on your device) 
5. sudo mount volumen2mount /mnt/
6. work with the attached volume</br>
   6.1. **you can't copy from your local on claimed volume** </br>
       - sudo scp -r -i /path/<keypair_name.pem> ec2-user@ip_url_aws_ec2_image:/mounted_dir target_dir
7. sudo umount /mnt/

**Remember that you can get easily from your AWS EC2 console the ssh command to connect to your ec2 instances**


1. move data to your cassandra image: kubectl cp ./target_dir/ cassandra_pod:/var/lib/data/<specific_keyspace>/specific tables


### For recovery your data 

1. [nodetool repair](https://cassandra.apache.org/doc/latest/tools/nodetool/repair.html) </br>
   from k8s: kubectl exec cassandra_node -ti -- nodetool repair -full
2. \<regenerate materialized views\> **not recommended  in production**: </br>
   the .sql statement generated in the backup on snapshop process creates tables NOT views
3. nodetool refresh (but in our case we take very carefully migrations).
   * we recommend an script that traverse all tables in your keyspace.

note: in Cassandra ver 4+ nodetool refresh is deprecated and **nodetool import** is recomended.




ref.: Providers & Storage Class Resources:</br>
      https://kubernetes.io/docs/concepts/storage/storage-classes/#the-storageclass-resource
      
