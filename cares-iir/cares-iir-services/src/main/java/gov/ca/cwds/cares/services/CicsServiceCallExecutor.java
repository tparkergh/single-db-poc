package gov.ca.cwds.cares.services;

import java.time.LocalDateTime;

import gov.ca.cwds.cics.model.CicsResponse;

/**
 * CWDS J Team
 */
public interface CicsServiceCallExecutor<Req> {
  CicsResponse executeServiceCall(Req request);

  CicsResponse executeServiceCallForUpdate(Req request, LocalDateTime lastUpdateTimestamp);
}
