package uk.gov.laa.ccw.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccw.dao.MatterCodesDao;
import uk.gov.laa.ccw.models.MatterCode;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MatterCodesService {

    private final MatterCodesDao matterCodesDao;

    public List<MatterCode> getAllMatterCodes() {
        log.info("return matter codes from dao to controller");

        return matterCodesDao.fetchAllMatterCodes();
    }
}
