package uk.gov.laa.ccw.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import uk.gov.laa.ccw.exceptions.MatterCodeNotFoundException;
import uk.gov.laa.ccw.mapper.dao.MatterCodeMapper;
import uk.gov.laa.ccw.model.MatterCode;
import uk.gov.laa.ccw.repository.MatterCodesRepository;

import java.util.List;

/**
 * Dao class for the matter codes.
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class MatterCodeDao {

    private final MatterCodesRepository matterCodesRepository;
    private final MatterCodeMapper matterCodeMapper;

    /**
     * Dao function for getting the matter codes.
     */
    public List<MatterCode> findMatterCodesByLawType(String lawType) {
        log.info("return matter codes from dao to controller");

        List<MatterCode> matterCodes =  matterCodesRepository.findByLawType(lawType).stream()
                .map(matterCodeMapper::toMatterCode).toList();

        if (matterCodes.isEmpty()) {
            throw new MatterCodeNotFoundException("Unable to find Matter Code for law type " + lawType);
        }
        return matterCodes;
    }

    /**
     * Dao function for getting the matter codes 2.
     */
    public List<MatterCode> findMatterCodeTwosByMatterCodeOne(String matterCodeOne) {
        matterCodesRepository
                .findById(matterCodeOne)
                .orElseThrow(() -> new MatterCodeNotFoundException("Unable to find Matter Code 1 " + matterCodeOne));

        List<MatterCode> matterCodes = matterCodesRepository.findMatterCodesTwosByMatterCodeOne(matterCodeOne)
                .stream().map(matterCodeMapper::toMatterCode).toList();

        if (matterCodes.isEmpty()) {
            throw new MatterCodeNotFoundException("Unable to find Matter Code 2 for matter code 1 " + matterCodeOne);
        }
        return matterCodes;
    }
}