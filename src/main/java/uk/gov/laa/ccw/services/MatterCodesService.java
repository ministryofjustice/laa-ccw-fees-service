package uk.gov.laa.ccw.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccw.mapper.dao.MatterCodeMapper;
import uk.gov.laa.ccw.models.MatterCode;
import uk.gov.laa.ccw.repository.MatterCodesRepository;

import java.util.List;

/**
 * Service class for the matter codes.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MatterCodesService {

    private final MatterCodesRepository matterCodesRepository;
    private final MatterCodeMapper matterCodeMapper;

    /**
     * Gets all the matters codes.
     *
     * @return the list of matter codes.
     */
    public List<MatterCode> getAllMatterCodes() {
        log.info("return matter codes from dao to controller");

        return matterCodesRepository.findAll().stream().map(matterCodeMapper::toMatterCode).toList();
    }

    /**
     * Gets all the matters two codes for the given matter code one.
     *
     * @return the list of matter two codes.
     */
    public List<MatterCode> getAllMatterTwosForMatterCodeOne(String matterCodeOne) {
        log.info("return matter codes twos for given matter code one from dao to controller");

        return matterCodesRepository.findMatterCodesTwosByMatterCodeOne(matterCodeOne)
                .stream().map(matterCodeMapper::toMatterCode).toList();
    }
}
