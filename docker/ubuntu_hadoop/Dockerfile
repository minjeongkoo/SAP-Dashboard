FROM ubuntu

RUN apt-get update
RUN apt-get install openjdk-8-jdk -y
RUN apt-get install wget -y
RUN apt-get install vim -y
RUN apt-get install ssh -y

RUN mkdir    /hadoop_home
RUN wget -P  /hadoop_home http://mirrors.sonic.net/apache/hadoop/common/hadoop-2.7.7/hadoop-2.7.7.tar.gz
RUN tar xvzf /hadoop_home/hadoop-2.7.7.tar.gz  -C /hadoop_home

RUN ssh-keygen -t rsa -P '' -f ~/.ssh/id_dsa        && \
    cat ~/.ssh/id_dsa.pub >> ~/.ssh/authorized_keys && \
    mkdir /var/run/sshd

ENV JAVA_HOME    /usr/lib/jvm/java-8-openjdk-amd64
ENV HADOOP_HOME  /hadoop_home/hadoop-2.7.7
ENV PATH         $PATH:$HADOOP_HOME/bin:$HADOOP_HOME/sbin

RUN mkdir /hadoop_home/temp
RUN mkdir /hadoop_home/namenode_home
RUN mkdir /hadoop_home/datanode_home
RUN echo "export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64" >> $HADOOP_HOME/etc/hadoop/hadoop-env.sh

ADD ./core-site.xml   $HADOOP_HOME/etc/hadoop
ADD ./hdfs-site.xml   $HADOOP_HOME/etc/hadoop
ADD ./mapred-site.xml $HADOOP_HOME/etc/hadoop

EXPOSE 9000 50010 50020 50030 50060 50070 50075 50090

