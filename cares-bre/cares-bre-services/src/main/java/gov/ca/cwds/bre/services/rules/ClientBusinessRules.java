package gov.ca.cwds.bre.services.rules;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.ca.cwds.bre.interfaces.exception.BreException;
import gov.ca.cwds.bre.interfaces.model.BreRequest;
import gov.ca.cwds.cics.model.Client;
import gov.ca.cwds.cics.model.ClientData;

/**
 * @author CWDS J Team
 */
@Component("ClientBusinessRules")
public class ClientBusinessRules extends DroolsBusinessRuleBase<ClientData> {

  @Autowired
  private ObjectMapper jacksonObjectMapper;

  @Override
  protected Class<ClientData> getFactType() {
    System.out.println(ClientData.class.toString());
    return ClientData.class;
  }

  @Override
  protected BreRequest getSampleBreRequest() {
    BreRequest breRequest = null;
    try {
      String sampleRequest = IOUtils.toString(getClass().getResourceAsStream("/fixtures/rules/ClientBusinessRules-sample-request.json"), StandardCharsets.UTF_8);    
      breRequest = jacksonObjectMapper.readValue(sampleRequest, BreRequest.class);
    } catch (IOException e) {
      throw new BreException(e.getMessage(), e);
    }
    
    return breRequest;
  }

}
