package uk.gov.laa.ccw.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccw.dao.FeesDao;
import uk.gov.laa.ccw.dao.VatRatesDao;
import uk.gov.laa.ccw.models.Fee;
import uk.gov.laa.ccw.models.FeeRecord;

import java.util.List;

/**
 * Service class for the fees.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FeesService {

    private final FeesDao feesDao;
    private final VatRatesDao vatRatesDao;


    /**
     * Calculates the fee for a given location and case stage.
     *
     * @param location the location
     * @param caseStage the case stage
     * @return the fee
     */
    public Fee calculateFees(String location,
                             String caseStage) {

        log.info("get fees for location {}", location);
        List<FeeRecord> feesForLocation = feesDao.fetchFeesForLocation(location);

        log.info("get fees for case stage {}", caseStage);
        List<FeeRecord> feesForCaseStages = feesForLocation
                .stream()
                .filter(c -> c.getCaseStage().contentEquals(caseStage))
                .toList();
        Double totalFees = 0.0;
        for (FeeRecord f : feesForCaseStages) {
            totalFees += f.getAmount();
        }

        Double vat = vatRatesDao.fetchVat();
        log.info("add in vat of {}%", vat);

        Double vatAmount = (vat / 100.0) * totalFees;
        Double totalPlusVat = totalFees + vatAmount;

        return Fee.builder()
                .amount(totalFees)
                .vat(vatAmount)
                .total(totalPlusVat)
                .build();
    }
}
