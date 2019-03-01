package gov.ca.cwds.cares.services;

import gov.ca.cwds.cics.model.CicsResponse;

/**
 * CWDS J Team
 */
public interface CicsServiceCallExecutor<Req> {
  CicsResponse executeServiceCall(Req request);
}
