package uk.gov.laa.ccw.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccw.dao.MatterCodesDao;
import uk.gov.laa.ccw.models.MatterCode;

import java.util.List;

/**
 * Service class for the matter codes.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MatterCodesService {

    private final MatterCodesDao matterCodesDao;

    /**
     * Gets all the matters codes.
     *
     * @return the list of matter codes.
     */
    public List<MatterCode> getAllMatterCodes() {
        log.info("return matter codes from dao to controller");

        return matterCodesDao.fetchAllMatterCodes();
    }

    /**
     * Gets all the matters two codes for the given matter code one.
     *
     * @return the list of matter two codes.
     */
    public List<MatterCode> getAllMatterTwosForMatterCodeOne(String matterCodeOne) {
        log.info("return matter codes from dao to controller");

        return matterCodesDao.fetchMatterCodeTwos(matterCodeOne);
    }
}
