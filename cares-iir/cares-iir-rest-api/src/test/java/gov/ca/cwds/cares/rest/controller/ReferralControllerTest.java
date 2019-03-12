package gov.ca.cwds.cares.rest.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.ca.cwds.cares.interfaces.api.ReferralService;
import gov.ca.cwds.cares.interfaces.model.Allegation;
import gov.ca.cwds.cares.interfaces.model.Referral;

@RunWith(MockitoJUnitRunner.class)
public class ReferralControllerTest {
  
  private MockMvc mockMvc;
  
  @Mock
  private ReferralService referralService;
  
  @InjectMocks
  private ReferralController referralController; 

  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(referralController).build();
  }
  
  @Test
  public void testGetAllReferrals() throws Exception {
    String id_1 = "testId_1";
    String id_2 = "testId_2";
    
    Referral referral_1 = new Referral();
    referral_1.setIdentifier(id_1);
    
    Referral referral_2 = new Referral();
    referral_2.setIdentifier(id_2);
    
    Collection<Referral> referrals = Arrays.asList(referral_1, referral_2);
    
    when(referralService.getAllReferrals()).thenReturn(referrals);
    
    ObjectMapper objMapper = new ObjectMapper();
    String json = objMapper.writeValueAsString(referrals);
    
    mockMvc.perform(get("/referrals").accept(MediaType.APPLICATION_JSON))
    .andExpect(status().isOk()).andExpect(content().json(json));
  }
  
  @Test
  public void testGetReferral() throws Exception {
    String id = "testId";
    
    Referral referral = new Referral();
    referral.setIdentifier(id);
    
    Allegation allegation = new Allegation();
    allegation.setVictimClientId(id);
    allegation.setIdentifier("alleghationId");
    
    referral.addAllegation(allegation);
    
    when(referralService.getReferral(id)).thenReturn(referral);
    
    ObjectMapper objMapper = new ObjectMapper();
    String json = objMapper.writeValueAsString(referral);
    
    mockMvc.perform(get("/referrals/" + id).accept(MediaType.APPLICATION_JSON))
    .andExpect(status().isOk()).andExpect(content().json(json));
  }
}
