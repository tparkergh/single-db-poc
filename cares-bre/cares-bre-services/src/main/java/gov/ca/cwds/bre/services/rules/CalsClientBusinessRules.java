package gov.ca.cwds.bre.services.rules;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import gov.ca.cwds.bre.interfaces.model.BreRequest;
import gov.ca.cwds.bre.interfaces.exception.BreException;
import gov.ca.cwds.cms.data.access.dto.ClientEntityAwareDTO;

/**
 * CWDS J Team
 */
@Component("CalsClientBusinessRules")
public class CalsClientBusinessRules extends DroolsBusinessRuleBase<ClientEntityAwareDTO> {
  @Override
  protected Class<ClientEntityAwareDTO> getFactType() {
    return ClientEntityAwareDTO.class;
  }

  @Override
  protected BreRequest getSampleBreRequest() {
    BreRequest breRequest;
    try {
      String sampleRequest = IOUtils.toString(getClass().getResourceAsStream("/fixtures/rules/CalsClientBusinessRules-sample-request.json"), StandardCharsets.UTF_8);
      breRequest = jacksonObjectMapper.readValue(sampleRequest, BreRequest.class);
    } catch (IOException e) {
      throw new BreException(e.getMessage(), e);
    }

    return breRequest;
  }
}
