Log.ic
=====================================

An app to collect logs from a log4j socket, allowing users to easily view, filter, and perform aggregations on logs.

Runs with Play on Scala

Utilizes MongoDB and Casbah to drive mongo and Salat to serialize/map scala objects

At the core, Log.ic is software to retrieve logs from various applications via
<a href="http://logging.apache.org/log4j/2.x/">log4j</a> web <a href="http://logging.apache.org/log4j/2.x/manual/appenders.html#SocketAppender">sockets</a> and allow
users to view, filter, and aggregate those logs based on various criteria.

Logs are data.  Unlike measuring the circumference of a circle, there is a large interpretation space for ideas and methods
clarifying and analyzing data. Just as there are many ways to measure a central tendency, Log.ic provides many ways for users
to measure and understand logs as well as customize their own views and metrics.

Log.ic seeks to provide a tool to analyze <i>logs as data</i>, both quantitatively and qualitatively, in order to provide a very
critical service: <i>forecasting failure</i>.

In many organizations, downtime of an application can cost on the order of millions of dollars for every few seconds
an application is unavailable.  This criticality makes predicting failure a must, so action can be taken before
it becomes very costly.

