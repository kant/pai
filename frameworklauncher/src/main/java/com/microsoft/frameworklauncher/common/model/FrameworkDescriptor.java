// Copyright (c) Microsoft Corporation
// All rights reserved. 
//
// MIT License
//
// Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
// documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
// the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and 
// to permit persons to whom the Software is furnished to do so, subject to the following conditions:
// The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED *AS IS*, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
// BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
// NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
// DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 

package com.microsoft.frameworklauncher.common.model;

import com.microsoft.frameworklauncher.common.ModelValidation;
import com.microsoft.frameworklauncher.common.exceptions.BadRequestException;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Map;

public class FrameworkDescriptor implements Serializable {
  @Valid
  private String description;

  @Valid
  @NotNull
  // version change will trigger the whole Framework NonRolling Upgrade
  private Integer version;

  @Valid
  private RetryPolicyDescriptor retryPolicy = new RetryPolicyDescriptor();

  @Valid
  private ParentFrameworkDescriptor parentFramework;

  @Valid
  @NotNull
  private UserDescriptor user = new UserDescriptor();

  @Valid
  @NotEmpty
  private Map<String, TaskRoleDescriptor> taskRoles;

  @Valid
  private PlatformSpecificParametersDescriptor platformSpecificParameters = new PlatformSpecificParametersDescriptor();

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public RetryPolicyDescriptor getRetryPolicy() {
    return retryPolicy;
  }

  public void setRetryPolicy(RetryPolicyDescriptor retryPolicy) {
    this.retryPolicy = retryPolicy;
  }

  public ParentFrameworkDescriptor getParentFramework() {
    return parentFramework;
  }

  public void setParentFramework(ParentFrameworkDescriptor parentFramework) {
    this.parentFramework = parentFramework;
  }

  public UserDescriptor getUser() {
    return user;
  }

  public void setUser(UserDescriptor user) {
    this.user = user;
  }

  public Map<String, TaskRoleDescriptor> getTaskRoles() {
    return taskRoles;
  }

  public void setTaskRoles(Map<String, TaskRoleDescriptor> taskRoles) throws BadRequestException {
    for (String taskRoleName : taskRoles.keySet()) {
      ModelValidation.validate(taskRoleName);
    }

    this.taskRoles = taskRoles;
  }

  public PlatformSpecificParametersDescriptor getPlatformSpecificParameters() {
    return platformSpecificParameters;
  }

  public void setPlatformSpecificParameters(PlatformSpecificParametersDescriptor platformSpecificParameters) {
    this.platformSpecificParameters = platformSpecificParameters;
  }
}

