package gov.ca.cwds.cares.services.mapping;

import static org.junit.Assert.assertEquals;

import gov.ca.cwds.cares.interfaces.model.SystemMeta;
import gov.ca.cwds.cares.persistence.entity.SystemMetaEntity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class SystemMetaMapperTest {
  @Test
  public void shouldMapSystemMetaEntityToSystemMeta(){
    SystemMetaEntity metaEntity = buildSystemMeta(1, "entity", "meta entry");
    SystemMeta meta = SystemMetaMapper.INSTANCE.mapToSystemMeta(metaEntity);

    assertEquals(metaEntity.getDefaultSystemId(), meta.getDefaultSystemId());
    assertEquals(metaEntity.getLongDescriptionIndicator(), meta.getLongDescriptionIndicator());
    assertEquals(metaEntity.getLastBroadcastTimestamp(), meta.getLastBroadcastTimestamp());
    assertEquals(metaEntity.getLastUpdatedById(), meta.getLastUpdatedById());
    assertEquals(metaEntity.getLastUpdatedTimeStamp(), meta.getLastUpdatedTimeStamp());
    assertEquals(metaEntity.getOtherCodeName(), meta.getOtherCodeName());
    assertEquals(metaEntity.getRelationshipToCategoryIndicator(), meta.getRelationshipToCategoryIndicator());
    assertEquals(metaEntity.getUserDefinedLogicalIdIndicator(), meta.getUserDefinedLogicalIdIndicator());
    assertEquals(metaEntity.getUserDefinedIdLength(), meta.getUserDefinedIdLength());
    assertEquals(metaEntity.getUserTableName(), meta.getUserTableName());
    assertEquals(metaEntity.getLongDescription(), meta.getLongDescription());
    assertEquals(metaEntity.getShortDescription(), meta.getShortDescription());
  }

  @Test
  public void shouldMapCollectionOfSystemMetaEntitiesToCollectionOfSystemMeta() {
    SystemMetaEntity metaEntity1 = buildSystemMeta(1, "first", "entry # 1");
    SystemMetaEntity metaEntity2 = buildSystemMeta(2, "second", "entry # 2");

    List metaEntities = new ArrayList();
    metaEntities.add(metaEntity1);
    metaEntities.add(metaEntity2);

    Collection<SystemMeta> metas = SystemMetaMapper.INSTANCE.mapToSystemMetas(metaEntities);

    Map<Integer, SystemMeta> metasBySystemId = new HashMap();
    metas.forEach((meta)->  metasBySystemId.put(meta.getDefaultSystemId(), meta));

    SystemMeta meta1 = metasBySystemId.get(metaEntity1.getDefaultSystemId());
    assertEquals(metaEntity1.getDefaultSystemId(), meta1.getDefaultSystemId());
    assertEquals(metaEntity1.getMetaName(), meta1.getMetaName());
    assertEquals(metaEntity1.getShortDescription(), meta1.getShortDescription());

    SystemMeta meta2 = metasBySystemId.get(metaEntity2.getDefaultSystemId());
    assertEquals(metaEntity2.getDefaultSystemId(), meta2.getDefaultSystemId());
    assertEquals(metaEntity2.getMetaName(), meta2.getMetaName());
    assertEquals(metaEntity2.getShortDescription(), meta2.getShortDescription());
  }

  private SystemMetaEntity buildSystemMeta(int systemId, String metaName, String shortDescription ){
    SystemMetaEntity meta = new SystemMetaEntity();
    meta.setDefaultSystemId(systemId);
    meta.setMetaName(metaName);
    meta.setShortDescription(shortDescription);

    meta.setLongDescriptionIndicator("02");
    meta.setLastBroadcastTimestamp(LocalDateTime.of(2010,10,30,12,59,00));
    meta.setLastUpdatedById("OXW");
    meta.setLastUpdatedTimeStamp(LocalDateTime.now());
    meta.setOtherCodeName("03");
    meta.setRelationshipToCategoryIndicator("Y");
    meta.setUserDefinedLogicalIdIndicator("N");
    meta.setUserDefinedIdLength(4);
    meta.setUserTableName("tablename");
    meta.setLongDescription("long description");

    return meta;
  }
}
