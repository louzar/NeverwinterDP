NeverwinterDP
=============
- A [DemandCube](https://github.com/DemandCube) Project

NeverwinterDP the Big Data Pipeline for Hadoop





WHAT IS NEVERWINTER DP?
=======================
Neverwinter is an open source distributed data ingestion system/framework for capturing large amounts of data  (ranging from gigabytes to petabytes) to be (processed or saved in real-time) to one or more down databases / repositories (i.e. Hadoop, HDFS, S3, Mysql, Hbase, Storm).

Neverwinter was designed and written from the ground up for reliability, scalability, and operational maintainability to support the growing needs of event and message data collection at scale to support startups and enterprise organizations.

Neverwinter - The real-time log/data pipeline. Sparkngin(Nginx) Kafka, Scribengin, leveraging processing in Hadoop and Storm the Data pipeline integrates with Logstash, Ganglia and Nagios Integration.  It's a replacement for flume but also can be integrated with it.

Neverwinter is the combination of three major open source project that leverage the best in open source. 

1. Sparkngin (powered by Nginx)
2. Kafka
3. Scribengin

Now that we have used enough buzz words.  Neverwinter reliably captures lots of data and saves it to hadoop and other systems.

WHAT CAN NEVERWINTER DO?
========================
Neverwinter allows data ingestion from any system that can emit http/rest (or zeromq) calls and then publish this data to a down stream database, including Hive, HBase, relational databases or even proprietary data stores. A single Neverwinter pipeline can combine data from multiple sources and deliver them to multiple sources, allowing for data to be delivered to multiple team or an entire organization.

Neverwinter is targeted at data and analytics engineering teams who expect response times ranging from sub-second to minutes. Neverwinter breaks the false choice between having  a batch or real-time system. Also the false choice between having a fast or maintainable system.

Use-Cases
---------
Problem - Data Collection and Saving

Components
----------

1) Http/Rest/ZeroMQ Log Collection Endpoint - Sparkngin
- [Sparkngin - Nginx](https://github.com/DemandCube/Sparkngin)

2) Data Bus
- [Kafka](http://kafka.apache.org/) or [Amazon Kinesis](http://aws.amazon.com/kinesis/) (Version 2)

3) Data Pump/Transport
- [Scribengin](https://github.com/DemandCube/Scribengin)


Architecture
------------
1. High Level

```
    +-----------+    +-------------+    +---------------+    +------------+    +------------+
    |Submission |    |Rest         |    |Transport      |    |Streaming   |    |Data        |
    | Client    |+-->| Endpoint    |+-->| Queue         |+-->| Adapter    |+-->| Repository |
    |           |    |(Sparkngin)  |    |(Kafka/Kinesis)|    |(Scribengin)|    |(HDFS/Hbase)|
    +-----------+    +-------------+    +---------------+    +------------+    +------------+
```
2. Mid Level
  1. Submission Client
  2. Endpoint Collector
  3. Collector Producer
  4. Transport Queue
  5. Stream Processor (CEP - Complex Event Processing)
  6. Destination Adapter

Transport Protocol Levels
--------
1. Binary
2. Framework
3. Schema
4. Encryption

<dl>
  <dt>Binary</dt>
  <dd>This level transports any arbitrary data</dd>
  <dt>Framework</dt>
  <dd>This level transports any data wrapped in fields of data needed by the framework for monitorying</dd>
  <dt>Schema</dt>
  <dd>This level adds schemas to the data being transported in the framework layer</dd>
  <dt>Encryption</dt>
  <dd>This level adds encrytion around the data in schemas or the framework transport</dd>
</dl>

Community
======
- [Mailing List](https://groups.google.com/forum/#!forum/neverwinterdp)
- IRC channel #neverwinterdp on irc.freenode.net


Other
======

HA Testing
 - Testing using [SimianArmy](https://github.com/Netflix/SimianArmy/wiki) and [Chaos Monkey](https://github.com/Netflix/SimianArmy/wiki/Chaos-Home), and [Jenkins](http://jenkins-ci.org/)

Providing
- Data Steaming/Collection Framework
- High Availability and Scalable Data Collection
- Data Monitoring
- Autoconfiguration - with ZeroConf - Stored in Zookeeper?
- Data Partition Notification

Additional Features: High Availability and Performant  Log Collection
- Log Distribution - Multi-data center
- Log Replay
- Log Monitoring
- Log Search
- Log Operational Watchdog
- Log Reporting
- Log Alerting

Logs are fed into
- HDFS
- Elastic Search
- Hive
- Mysql
- HBase
- Vertica

TODO
====
- [ ] Put in contributor information and update projects to reference (Kafka and Flume)
- [ ] Log Stash to Neverwinter Plugin
- [ ] Log Collection Standard
- [ ] Neverwinter Nginx Plugin to Kafka
- [ ] Neverwinter Kafka Queue Monitor in Kibana
- [ ] Develop NW Distributed Data Pump -> HCatalog - Think can be distributed framework for managing a cluster of writers to Flume or Logstash
- [ ] Add other main github projects

Areas to flush out
====
Prototype framework with zmq in python

Topics
- Registry
- Heartbeat
- Stats
- LogTopics

- [ ] Develop - Protocol

Main development
====
- Sparkngin
- Connector Component

Monitoring Outof the box
====
Out of the box super easy plugin to
- Nagios
- Ganglia

![NWD-HighLevel](diagrams/images/NWD-HighLevel.png?raw=true "A Highlevel Diagram")

[Nginx] -> Openresty, libkafka with spillover buffer, spillagent, window registration and monitoring
- Nginx -> Kafka -> Flume -> Hcatalog -> Hive

[Log]
- Epoch timestamp, ip, process and optional type and version

[Monitoring] Log normal, error, watchdog, normal spill, error spill, watchdog spill
- Type, Lines, Size per minute per process per server

[Concept/Abstraction]
_ Emmiter Client
 - Zero Conf - module to -> zookeeper
 - async
 - persistence
 - send 
 - spillover
 - spillover recovery agent
- LogEmitter
- LogFormat
- LogVersion
- LogType
- WatchDogEmitter
- WatchDog Register
- WatchDog Monitor and Alerts


[Reporting]
- Summary Report
- LogType Report
- Key Coverage Report
- Value Coverage Report

[Support]
- Data Processing Assesment
- Process management
- Annual Data Assessment

[Dependencies]
- [OpenResty](http://openresty.org/)
- [Nginx](http://nginx.com/)
- [Kafka](http://kafka.apache.org/)
- [Hadoop](http://hadoop.apache.org/)
- [Ganglia](http://ganglia.sourceforge.net/)
- [Cacti](http://www.cacti.net/)
- [ElasticSearch](http://www.elasticsearch.org/)
- [LogStash](http://logstash.net/)
- [HCatalog](http://hive.apache.org/hcatalog/)
- [Hive](http://hive.apache.org/)
- [Storm](http://storm-project.net/)
- [HBase](http://hbase.apache.org/)
- [Flume](http://flume.apache.org/)
- [Kibana](http://www.elasticsearch.org/overview/kibana/)
- [Doxygen](http://www.stack.nl/~dimitri/doxygen/index.html)
- [Zeromq](http://zeromq.org)


[ To investigate ]
- Nginx -> Kafka
- Nginx -> Logrotate
- Nginx -> module timestamp
- Nginx -> logrotation module
- Logrotate frequency mod
- Logtail - find reference to old project that I looked at
- Filehandle monitor
 - fuser
 - inotify-tools

Capabilities
- file handle monitor
- file process monitor
- file tail
- Kafka efficient socket to file transfer
 

```
  +------------+    +-----------+    +------------+    +----------------+
  |NW          |    |NW         |    |NW          |    |                |
  |            |    |           |    |            |    |                |
  |  Front End |    |  Data Bus |    |  Data Pump |    | End Point      |
  |   Emitter  |+-->|           |+-->|            |+-->|- HDFS          |
  |  - Http Get|    |           |    |            |    |- Elastic Search|
  |  - Json    |    |           |    |            |    |                |
  |  - Avro    |    |           |    |            |    |                |
  +------------+    +-----------+    +------------+    +----------------+



  +-----------+     +---------+     +-----+    +--------+
  | Log Stash |     |Sparkngin|     |Kafka|    |Hadoop  |
  |-----------|     |---------|     |-----|    |--------|
  |           |+--->|         |+--->|     |+-->|HCatalog|
  |           |     |         |     |     |    |HBase   |
  +-----------+     +---------+     +-----+    +--------+
                                       +
                                       |        +--------+
                                       +------->|Storm   |
                                                |--------|
                                                |        |
                                                |        |
                                                +--------+
  
  http://www.asciiflow.com/#Draw
```



QUESTIONS
=========
Should a distributed fault tolerant data transport layer from Kafka to hadoop be build on 
- 1) Storm
- 2) Yarn



[ Front End Emmiter ]
- The concept is to have a nginx front end that will ship logs to 
- 

[ log collection (logstash] -> [rest end point (nginx) ] ->  [ data bus (kafka) ] -> [ data pump/transport (storm or yarn) ] -> [ rdbms (hive - data registration live) |  file system (hdfs)  | key store (hbase) ]

Look at developing the protocol prototype with a Avro Producer using zmq and a Avro Consumer communicating through kafka.
-Version/Lineage,Heartbeat,Source,Header/Footer.  Take design aspects from [Camus](https://github.com/linkedin/camus) , must provide built monitoring.  There
needs to be a messaging (source timestamp, system timestamp) and a way to inspect where hour boundries exist on the queue.  Additionally need a way
to register servers and when they come on and offline for log registration.  

Should there be the ability for schema registration, so that schema's can be pushed to downstream?  

Should there be a mapping and general payload support.  Json support 

Should Avro / Thrift / Protobuff / HBase / Hive / Storm - Type Mappings be maintained?

Kafka Resources
=====
- https://github.com/criteo/kafka-ganglia
- Creating Author:  Maxime Brugidou <maxime.brugidou@gmail.com>
- Interested Potential Contributor: Andrew Otto <otto@wikimedia.org>


Four Level Protocol Stack
=====
- Level 0 Raw
- Level 1 Envelop Framework (Leverage Avro/Protocol Buffers/Thrift)
- Level 2 Event Payload
- Level 3 Encrypted Payload
- Events configured to flow by topic and get partitioned by either server timestamp or application supplied
- Sparknginx in-memory encryption layer. 

Preferred Development Tools
- [Ansible] (http://www.ansibleworks.com/)
- [Vangrant] (http://www.vagrantup.com/)
- [Virtualbox] (https://www.virtualbox.org/)
- [Gradle] (http://www.gradle.org/)

Github Pages
-------------
- <http://24ways.org/2013/get-started-with-github-pages/>

Site Examples
-------------
- http://brew.sh/
- http://www.shinken-monitoring.org/
- http://flask.pocoo.org/
- http://phantomjs.org/
- http://showterm.io/
- http://stedolan.github.io/jq/
- http://www.sparkjava.com/
- http://leiningen.org/
- http://vertx.io/
- http://nodejs.org/
- http://hyde.github.io/
- http://www.crossroads.io/
- Maybe use http://jekyllrb.com/
- http://graphite.wikidot.com/
- http://graphite.readthedocs.org/en/1.0/tools.html
- https://github.com/snowplow/snowplow/wiki




Keep your fork updated
====
[Github Fork a Repo Help](https://help.github.com/articles/fork-a-repo)


- Add the remote, call it "upstream":

```
git remote add upstream git@github.com:DemandCube/NeverwinterDP.git
```
- Fetch all the branches of that remote into remote-tracking branches,
- such as upstream/master:

```
git fetch upstream
```
- Make sure that you're on your master branch:

```
git checkout master
```
- Merge upstream changes to your master branch

```
git merge upstream/master
```

Git Workflow to Develop a new Feature
====

- Step 1(New Fork): 
  - # Hit the "Fork" Icon in Github.com web site when on the Repository Page e.g. "DemandCube/NeverwinterDP"

- Step 1(Existing Fork e.g. "YourUserName/NeverwinterDP"):
  - # pull in changes from the master upstream/parent repo
  - # No ff is the way you should since it won't automatically commit and you can comment the merge commit
  - `git pull --no-ff https://github.com/DemandCube/NeverwinterDP.git master`
  - # Now fix any merge conflicts if they exist
   
- Step 2:
  - # Create a branch todo your development "feature/featurename"
  - `git checkout -b feature/featurename master`

- Step 3: 
  - # Do any development that you need todo

- Step 4:
  - # make sure your still in the feature branch
  - `git checkout feature/featurename`

- Step 5 (Optional - but recommended):
  - # squash commits in the feature/featurename when your done and ready to commit
  - # This is in Repo => "YourUserName/NeverwinterDP" on branch "feature/featurename"
  - # Assumes your branch is made from "master"
  - `git rebase -i master`

- Step 6:
  - # Now merge into the master
  - `git checkout master`
  - `git pull --no-ff https://github.com/DemandCube/NeverwinterDP.git master`
  - # resolve any dependency conflicts
  
- Step 7:
  - # Now merge into local master
  - `git checkout master`
  - `git merge feature/featurename`
  - `git push origin master`

How to Contribute
======

There are many ways you can contribute towards the project. A few of these are:

**Jump in on discussions**: It is possible that someone initiates a thread on the [Mailing List](https://groups.google.com/forum/#!forum/sparkngin) describing a problem that you have dealt with in the past. You can help the project by chiming in on that thread and guiding that user to overcome or workaround that problem or limitation.

**File Bugs**: If you notice a problem and are sure it is a bug, then go ahead and file a [GitHub Issue](https://github.com/DemandCube/Sparkngin/issues?state=open). If however, you are not very sure that it is a bug, you should first confirm it by discussing it on the [Mailing List](https://groups.google.com/forum/#!forum/sparkngin).

**Review Code**: If you see that a [GitHub Issue](https://github.com/DemandCube/Sparkngin/issues?state=open) has a "patch available" status, go ahead and review it. The other way is to review code submited with a [pull request](https://github.com/DemandCube/Sparkngin/pulls), it is the prefered way.  It cannot be stressed enough that you must be kind in your review and explain the rationale for your feedback and suggestions. Also note that not all review feedback is accepted - often times it is a compromise between the contributor and reviewer. If you are happy with the change and do not spot any major issues, then +1 it.

**Provide Patches**: We encourage you to assign the relevant [GitHub Issue](https://github.com/DemandCube/Sparkngin/issues?state=open) to yourself and supply a patch or [pull request](https://github.com/DemandCube/Sparkngin/pulls) for it. The patch you provide can be code, documentation, tests, configs, build changes, or any combination of these.

Workflow
======

1. **Fork the Repository**
1. **Create a branch off of master or if you know right branch**
  * `git branch feature/master/my_contribution master`
  * `git branch fix/master/my_contribution master`
  * `git branch issue/master/my_contribution master`
1. **Then checkout the new branch with** 
  *  `git	checkout fix/master/my_contribution`
1. **Do your coding**
1. **Issue Pull Request(On Github website) to the Main Repo**
1. **Stop making changes on that branch** 

How to Submit - Patches/Code
======

1. **Create a patch**
  * Make sure it applies cleanly against trunk
1. **Test**
  * If code supply tests and unit test
1. **Propose New Features or API**
  * Document the new Feature or API in the Wiki, the get consensus by discussing on the mailing list
1. **Open a GitHub Ticket**
  * Create the patch or pull request, attach your patch or pull request to the Issue.
    * Your changes should be well-formated, readable and lots of comments
    * Add tests
    * Add to documentation, especially if it changes configs
    * Add documentation so developers, can understand the feature or API to continue to contribute
  * Document information about the issue and approach you took to fix it and put it in the issue.
  * Send a message on the mailing list requesting a commiter review it.
  * Nag the list if we (commiters) don't review it and followup with us.

1. **How to create a patch file**: 
  * The preferred naming convention for Sparkngin patches is `SPARKNGIN-12345-0.patch` where `12345` is the Issue number and `0` is the version of the patch. 
  * Patch Command:
    * `$ git diff > /path/to/SPARKNGIN-1234-0.patch`

1. **How to apply someone else's patch file**: 
```
$ cd ~/src/Sparkngin # or wherever you keep the root of your Sparkngin source tree 
$ patch -p1 < SPARKNGIN-1234-0.patch # Default when using git diff
$ patch -p0 < SPARKNGIN-1234-0.patch # When using git diff --no-prefix
```

1. Reviewing Patches
  * [Find issues with label "Patch Available"](https://github.com/DemandCube/Sparkngin/issues?labels=patch+avaliable&page=1&state=open), look over and give your feedback in the issue as necessary.  If there are questions discuss in the [Mailing List](https://groups.google.com/forum/#!forum/sparkngin).


1. Pull Request
TODO: add in documentation on how todo this.
https://help.github.com/articles/merging-a-pull-request

## Git Workflow
  * [Suggested Git Workflows](https://cwiki.apache.org/confluence/display/KAFKA/Git+Workflow)

## Github Help
  * [How push from your local repo to github](https://help.github.com/articles/pushing-to-a-remote#pushing-a-branch)
  * [How to send a pull request](https://help.github.com/articles/using-pull-requests)
  * [How to sync a forked repo on github](https://help.github.com/articles/syncing-a-fork)

## TODO Document the recommended workflow in Github 
  * fork repo -> make changes -> sync forked repo local -> push to github forked repo -> do pull request


