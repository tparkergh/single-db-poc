package gov.ca.cwds.cares.interfaces.api;

import java.util.Collection;
import gov.ca.cwds.cares.interfaces.model.Hearing;

public interface HearingService {

    Hearing getHearing(String hearingId);

    Collection<Hearing> getAllHearings();

}
