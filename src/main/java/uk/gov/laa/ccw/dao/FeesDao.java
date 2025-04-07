package uk.gov.laa.ccw.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import uk.gov.laa.ccw.exceptions.FeesException;
import uk.gov.laa.ccw.mapper.dao.FeeMapper;
import uk.gov.laa.ccw.model.FixedFee;
import uk.gov.laa.ccw.repository.FeeRepository;

import java.util.List;

/**
 * Dao class for fees.
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class FeesDao {
    private final FeeRepository repository;
    private final FeeMapper feeMapper;

    /**
     * Fetches the fees for a given location.
     *
     * @param providerLocation the provider location
     * @return the list of matter codes
     */
    public List<FixedFee> fetchFeesForLocationAndCaseStage(
            String providerLocation,
            String caseStage) {
        log.info("fetch fees from database for {}", providerLocation);

        List<FixedFee> fixedFees =  repository.findAllByProviderLocationAndCaseStage(
                        providerLocation, caseStage).stream()
                .map(feeMapper::toFee).toList();

        if (fixedFees.isEmpty()) {
            throw new FeesException(
                    "Unable to find fixed fees for location "
                            + providerLocation
                            + " and case stage "
                            + caseStage);
        }

        return fixedFees;
    }
}