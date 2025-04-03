package uk.gov.laa.ccw.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccw.exceptions.MatterCodeNotFoundException;
import uk.gov.laa.ccw.mapper.MatterCodesOneMapper;
import uk.gov.laa.ccw.mapper.MatterCodesTwoMapper;
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
    private final MatterCodesOneMapper matterCodesOneMapper;
    private final MatterCodesTwoMapper matterCodesTwoMapper;

    /**
     * Gets all the matters codes.
     *
     * @return the list of matter codes.
     */
    public List<MatterCode> getAllMatterCodes() {
        log.info("return matter codes from dao to controller");

        return matterCodesRepository.findAll().stream().map(matterCodesOneMapper::toMatterCode).toList();
    }

    /**
     * Gets all the matters two codes for the given matter code one.
     *
     * @return the list of matter two codes.
     */
    public List<MatterCode> getAllMatterTwosForMatterCodeOne(String matterCodeOne) {
        log.info("return matter codes for given matter code one from dao to controller");

        matterCodesRepository
                .findById(matterCodeOne)
                .orElseThrow(() -> new MatterCodeNotFoundException("Unable to find Matter Code " + matterCodeOne));

        return matterCodesRepository.findMatterCodesTwosByMatterCodeOne(matterCodeOne)
                .stream().map(matterCodesTwoMapper::toMatterCode).toList();
    }
}
