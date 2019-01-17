package gov.ca.cwds.cares.rest.controller;

import static org.mockito.ArgumentMatchers.any;
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

import gov.ca.cwds.cares.services.interfaces.api.SystemCodeService;
import gov.ca.cwds.cares.services.interfaces.model.SystemCode;
import gov.ca.cwds.cares.services.interfaces.model.SystemMeta;

@RunWith(MockitoJUnitRunner.class)
public class SystemCodeControllerTest {

  private MockMvc mockMvc;

  @Mock
  private SystemCodeService systemCodeService;

  @InjectMocks
  private SystemCodeController systemCodeController;

  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(systemCodeController).build();
  }

  @Test
  public void testGetAllMetas() throws Exception {
    String meta_1 = "testMetaName_1";
    String meta_2 = "testMetaName_2";

    SystemMeta systemMeta_1 = new SystemMeta();
    systemMeta_1.setMetaName(meta_1);

    SystemMeta systemMeta_2 = new SystemMeta();
    systemMeta_2.setMetaName(meta_2);

    Collection<SystemMeta> systemMetas = Arrays.asList(systemMeta_1, systemMeta_2);

    when(systemCodeService.getAllMetas()).thenReturn(systemMetas);

    ObjectMapper objMapper = new ObjectMapper();
    String json = objMapper.writeValueAsString(systemMetas);

    mockMvc.perform(get("/system_metas").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andExpect(content().json(json));
  }


  @Test
  public void testGetSystemCodes() throws Exception {
    Integer code_1 = 1234;
    Integer code_2 = 5678;

    SystemCode systemCode_1 = new SystemCode();
    systemCode_1.setSystemId(code_1);

    SystemCode systemCode_2 = new SystemCode();
    systemCode_2.setSystemId(code_2);

    Collection<SystemCode> systemCodes = Arrays.asList(systemCode_1, systemCode_2);

    when(systemCodeService.getSystemCodes(any())).thenReturn(systemCodes);

    ObjectMapper objMapper = new ObjectMapper();
    String json = objMapper.writeValueAsString(systemCodes);

    mockMvc.perform(get("/system_codes/metaName").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andExpect(content().json(json));
  }
}
