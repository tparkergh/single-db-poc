package gov.ca.cwds.cares.services;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gov.ca.cwds.cares.persistence.entity.HearingEntity;
import gov.ca.cwds.cares.persistence.repository.HearingRepository;
import gov.ca.cwds.cares.interfaces.api.HearingService;
import gov.ca.cwds.cares.interfaces.model.Hearing;
import gov.ca.cwds.cares.services.mapping.HearingMapper;

@Service
public class HearingServiceImpl implements HearingService {

    @Autowired
    private HearingRepository hearingRepository;
    private static final String[] HEARING_IDS = new String[]{
            "AgLPH9f00R",
            "Ai3VQBU0Um"
    };

    @Override
    public Collection<Hearing> getAllHearings() {
        // White listed Hearing ids
        Collection<HearingEntity> hearingEntities =
                hearingRepository.findAllById(Arrays.asList(HEARING_IDS));
        return HearingMapper.INSTANCE.mapToHearings(hearingEntities);
    }

    @Override
    public Hearing getHearing(String hearingId) {
        HearingEntity refEntity = null;
        Optional<HearingEntity> Hearing = hearingRepository.findById(hearingId);
        if(Hearing.isPresent()){
            refEntity = Hearing.get();
        }
        return HearingMapper.INSTANCE.mapToHearing(refEntity);
    }
}
