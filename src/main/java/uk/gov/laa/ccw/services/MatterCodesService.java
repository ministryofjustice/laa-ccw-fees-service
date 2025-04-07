package uk.gov.laa.ccw.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccw.dao.MatterCodeDao;
import uk.gov.laa.ccw.model.MatterCode;

import java.util.List;

/**
 * Service class for the matter codes.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MatterCodesService {

    private final MatterCodeDao matterCodeDao;

    /**
     * Gets all the matters codes.
     *
     * @return the list of matter codes.
     */
    public List<MatterCode> getAllMatterCodes(String lawType) {
        log.info("return matter codes from dao to controller");

        return matterCodeDao.findMatterCodesByLawType(lawType);
    }

    /**
     * Gets all the matters two codes for the given matter code one.
     *
     * @return the list of matter two codes.
     */
    public List<MatterCode> getAllMatterTwosForMatterCodeOne(String matterCodeOne) {
        log.info("return matter codes twos for given matter code one from dao to controller");

        return matterCodeDao.findMatterCodeTwosByMatterCodeOne(matterCodeOne);
    }
}