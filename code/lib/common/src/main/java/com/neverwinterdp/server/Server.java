package com.neverwinterdp.server;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neverwinterdp.server.cluster.Cluster;
import com.neverwinterdp.server.cluster.ClusterEvent;
import com.neverwinterdp.server.cluster.ClusterMember;
import com.neverwinterdp.server.cluster.hazelcast.HazelcastCluster;
import com.neverwinterdp.server.config.ServerConfig;
import com.neverwinterdp.server.service.ServiceRegistration;

/**
 * @author Tuan Nguyen
 * @email tuan08@gmail.com
 * 
 * The server can be understood as a single container or a machine that
 * contains an unlimited number of the services.
 */
public class Server {
  private Logger           logger;

  private ServerConfig     config;
  private Cluster          cluster;
  private ServiceContainer serviceContainer;
  private ActivityLogs     activityLogs = new ActivityLogs();
  private ServerState      serverState  = null;

  /**
   * The configuration for the server such name, group, version, description,
   * listen port. It also may contain the service configurations
   * */
  public ServerConfig getConfig() {
    return config;
  }

  public void setConfig(ServerConfig config) {
    this.config = config;
  }

  public Cluster getCluster() {
    return cluster;
  }

  public ServerState getServerState() {
    return serverState;
  }

  public void setServerState(ServerState state) {
    this.serverState = state;
  }

  public ActivityLogs getActivityLogs() {
    return this.activityLogs;
  }

  public ServiceContainer getServiceContainer() {
    return serviceContainer;
  }

  public ServerRegistration getServerRegistration() {
    List<ServiceRegistration> list = serviceContainer.getServiceRegistrations();
    ClusterMember member = cluster.getMember();
    return new ServerRegistration(member, serverState, list);
  }

  public Logger getLogger() {
    return this.logger;
  }

  public Logger getLogger(String name) {
    String address = cluster.getMember().toString();
    return LoggerFactory.getLogger("[" + address + "][NeverwinterDP] " + name);
  }

  /**
   * This lifecycle method be called after the configuration is set. The method
   * should: 1. Compute the configuration and add the services with the service
   * configuration. 2. Loop through the services and call service.onInit() 3.
   * Set the state of the services to init. 4. Set the state of the server to
   * init. 5. Add and configure the cluster services, start the cluster services
   */
  public void onInit() {
    long start = System.currentTimeMillis();
    cluster = new HazelcastCluster();
    cluster.onInit(this);
    logger = getLogger(getClass().getSimpleName());
    serviceContainer = new ServiceContainer();
    serviceContainer.onInit(this);
    logger.info("Start onInit()");
    setServerState(ServerState.INIT);
    long end = System.currentTimeMillis();
    activityLogs.add(new ActivityLog("Init", ActivityLog.Type.Auto, start, end, null));
    logger.info("Finish onInit()");
  }

  /**
   * This method is called after the stopServices is called. This method should:
   * 1. Stop and destroy all the cluster services 2. Release all the resources
   * if necessary, save the monitor or profile information.
   */
  public void onDestroy() {
    logger.info("Start onDestroy()");
    serviceContainer.onDestroy(this);
    cluster.onDestroy(this);
    logger.info("Finish onDestroy()");
  }

  /**
   * This method lifecycle should be called after the method onInit() is called.
   * This method should be called by the external service that can access the
   * Server instance or the cluster services.
   * 
   * This method should: 1. Check the state of the server, if the state of the
   * server is already START, then return 2. Loop through all the services, call
   * service.start().
   */
  public void start() {
    logger.info("Start start()");
    if (ServerState.RUNNING.equals(getServerState())) return;
    serviceContainer.start();
    setServerState(ServerState.RUNNING);
    cluster.getClusterRegistration().update(getServerRegistration());
    ClusterEvent clusterEvent = new ClusterEvent(ClusterEvent.ServerStateChange, getServerState());
    cluster.broadcast(clusterEvent);
    logger.info("Finish start()");
  }

  /**
   * This method is used to stop all the services usually it is used to
   * simmulate the server shutdown or suspend.
   */
  public void shutdown() {
    logger.info("Start shutdown()");
    if (ServerState.SHUTDOWN.equals(getServerState()))
      return;
    cluster.getClusterRegistration().remove(cluster.getMember());
    serviceContainer.stop();
    setServerState(ServerState.SHUTDOWN);
    ClusterEvent clusterEvent = new ClusterEvent(ClusterEvent.ServerStateChange, getServerState());
    cluster.broadcast(clusterEvent);
    logger.info("Finish shutdown()");
  }
}