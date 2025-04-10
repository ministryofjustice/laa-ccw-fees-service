package uk.gov.laa.ccw.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccw.exceptions.MatterCodeNotFoundException;
import uk.gov.laa.ccw.mapper.dao.MatterCodeMapper;
import uk.gov.laa.ccw.model.MatterCode;
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
    public List<MatterCode> getAllMatterCodes(String lawType) {
        log.info("return matter codes from dao to controller");

        List<MatterCode> matterCodes =  matterCodesRepository.findByLawType(lawType).stream()
                .map(matterCodeMapper::toMatterCode).toList();

        if (matterCodes.isEmpty()) {
            throw new MatterCodeNotFoundException("Unable to find Matter Code for law type " + lawType);
        }

        return matterCodes;
    }

    /**
     * Gets all the matters two codes for the given matter code one.
     *
     * @return the list of matter two codes.
     */
    public List<MatterCode> getAllMatterTwosForMatterCodeOne(String matterCodeOne) {
        log.info("return matter codes twos for given matter code one from dao to controller");

        matterCodesRepository
                .findById(matterCodeOne)
                .orElseThrow(() -> new MatterCodeNotFoundException("Unable to find Matter Code " + matterCodeOne));

        List<MatterCode> matterCodes =  matterCodesRepository.findMatterCodesTwosByMatterCodeOne(matterCodeOne)
                .stream().map(matterCodeMapper::toMatterCode).toList();

        if (matterCodes.isEmpty()) {
            throw new MatterCodeNotFoundException("Unable to find Matter Code 2 for matter code 1 " + matterCodeOne);
        }
        return matterCodes;
    }
}