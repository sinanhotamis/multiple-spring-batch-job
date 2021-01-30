# Read Me First

* snnLab project that is related Spring Batch Job(Chunk oriented File to File, and Database to File) on Local Terminal.
* Local OS:Ubuntu 20.04.1 LTS , Project Management Tool: Maven , Language Platform: JDK_11
* Local terminal is integrated embedded H2 database.
* Primary Datasource -> Embedded H2 (Job Related DB)
* Secondary Datasource -> What ever you want.
* Jobs can be operated on HTTP. JobOperatorController provides it according to your domain url.
* Jobs metadata (Execution Context, Step Execution Context, parameters, etc) can be observed on H2 db.
* Some base model, configuration, controller advice, and dependency management must be extract other project package (such as snnlab-starter-batch)

# Running on Local Terminal

* Run MultipleJobApplication  main class.
* Job is running on embedded Tomcat Server scheduled.
* The job executions can be operated over LobLauncherController endpoints.
* Embedded H2 Database can be observed on local machine  http://localhost:8080/h2-console
* You can open scheduled jobs on ScheduledJobTask class.

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.2/maven-plugin/reference/html/)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/2.4.2/reference/htmlsingle/#configuration-metadata-annotation-processor)
* [Spring Batch](https://docs.spring.io/spring-boot/docs/2.4.2/reference/htmlsingle/#howto-batch-applications)
* [Creating a Batch Service](https://spring.io/guides/gs/batch-processing/)
