package gov.ca.cwds.cares.geo.api;

import gov.ca.cwds.cares.geo.model.GeoAddress;
import java.util.List;

/**
 * CWDS J Team
 */
public interface GeoService {
  List<GeoAddress> validateAddress(GeoAddress request);
}
