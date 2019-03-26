package gov.ca.cwds.cares.services.mapping;

import static org.junit.Assert.assertEquals;

import gov.ca.cwds.cares.interfaces.model.SystemCode;
import gov.ca.cwds.cares.persistence.entity.SystemCodeEntity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class SystemCodeMapperTest {
  @Test
  public void shouldMapSystemCodeEntityToSystemCode(){
    SystemCodeEntity entity = buildSystemCodeEntity(1, 2, "short description", "AZCV132345");


    SystemCode sysCode = SystemCodeMapper.INSTANCE.mapToSystemCode(entity);

    assertEquals(entity.getSystemId(), sysCode.getSystemId());
    assertEquals(entity.getCategoryId(), sysCode.getCategoryId());
    assertEquals(entity.getInactiveIndicator(), sysCode.getInactiveIndicator());
    assertEquals(entity.getUserDefinedLogicalId(), sysCode.getUserDefinedLogicalId());
    assertEquals(entity.getLongDescription(), sysCode.getLongDescription());
    assertEquals(entity.getLastUpdatedById(), sysCode.getLastUpdatedById());
    assertEquals(entity.getLastUpdatedTimeStamp(), sysCode.getLastUpdatedTimeStamp());
    assertEquals(entity.getOtherCode(), sysCode.getOtherCode());
    assertEquals(entity.getShortDescription(), sysCode.getShortDescription());
    assertEquals(entity.getThirdId(), sysCode.getThirdId());
    assertEquals(entity.getMetaName(), sysCode.getMetaName());
  }

  @Test
  public void shouldMapCollectionSystemCodeEntitiesToCollectionOfSystemCodes(){
    SystemCodeEntity entity1 = buildSystemCodeEntity(1, 2, "short description", "AZCV132345");
    SystemCodeEntity entity2 = buildSystemCodeEntity(99, 98, "shorter description", "ZXCFYT6543");

    List entities = new ArrayList();
    entities.add(entity1);
    entities.add(entity2);

    Collection<SystemCode> sysCodes = SystemCodeMapper.INSTANCE.mapToSystemCodes(entities);

    Map<Integer, SystemCode> codesBySysCodes = new HashMap();
    sysCodes.forEach((code) -> codesBySysCodes.put(code.getSystemId(), code));

    SystemCode sysCode1 = codesBySysCodes.get(entity1.getSystemId());
    assertEquals(entity1.getSystemId(),sysCode1.getSystemId());
    assertEquals(entity1.getCategoryId(),sysCode1.getCategoryId());
    assertEquals(entity1.getShortDescription(),sysCode1.getShortDescription());
    assertEquals(entity1.getThirdId(),sysCode1.getThirdId());

    SystemCode sysCode2 = codesBySysCodes.get(entity2.getSystemId());
    assertEquals(entity2.getSystemId(),sysCode2.getSystemId());
    assertEquals(entity2.getCategoryId(),sysCode2.getCategoryId());
    assertEquals(entity2.getShortDescription(),sysCode2.getShortDescription());
    assertEquals(entity2.getThirdId(),sysCode2.getThirdId());
  }

  private SystemCodeEntity buildSystemCodeEntity(int sysId, int catId, String shortDescription, String thirdId) {
    SystemCodeEntity entity = new SystemCodeEntity();

    entity.setSystemId(sysId);
    entity.setCategoryId(catId);
    entity.setShortDescription(shortDescription);
    entity.setThirdId(thirdId);
    entity.setInactiveIndicator("02");
    entity.setUserDefinedLogicalId("03");
    entity.setLongDescription("A long description");
    entity.setLastUpdatedById("OXB");
    entity.setLastUpdatedTimeStamp(LocalDateTime.now());
    entity.setOtherCode("04");
    entity.setMetaName("test");
    return entity;
  }
}
