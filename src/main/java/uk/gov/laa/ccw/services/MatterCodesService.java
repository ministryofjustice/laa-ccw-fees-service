package uk.gov.laa.ccw.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccw.dao.MatterCodesDao;
import uk.gov.laa.ccw.mapping.MatterCodesGet200ResponseMapping;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MatterCodesService {

    private final MatterCodesDao matterCodesDao;

    public List<String> getAllMatterCodes() {
        log.info("getAllMatterCodes");

        return matterCodesDao.fetchAllMatterCodes().stream()
                .map(MatterCodesGet200ResponseMapping::map)
                .toList();
    }
}
