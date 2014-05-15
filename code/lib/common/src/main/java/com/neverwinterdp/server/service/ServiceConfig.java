package com.neverwinterdp.server.service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Tuan Nguyen
 * @email tuan08@gmail.com
 */
public class ServiceConfig {
  private String              serviceId;
  private String              name;
  private float               version;
  private String              className;
  private Map<String, String> properties = new HashMap<String, String>();
  private String              description;

  public String getServiceId() {
    return this.serviceId;
  }

  public void setServiceId(String id) {
    this.serviceId = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public float getVersion() {
    return version;
  }

  public void setVersion(float version) {
    this.version = version;
  }

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public Map<String, String> getProperties() {
    return properties;
  }

  public void setProperties(Map<String, String> properties) {
    this.properties = properties;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
