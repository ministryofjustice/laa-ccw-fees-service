package uk.gov.laa.ccw.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccw.dao.FeesDao;
import uk.gov.laa.ccw.dao.VatRatesDao;
import uk.gov.laa.ccw.models.Fee;
import uk.gov.laa.ccw.models.FeeRecord;
import uk.gov.laa.ccw.models.api.FeeCalculateRequestLevelCode;

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
                             String caseStage,
                             List<FeeCalculateRequestLevelCode> levelCodes) {

        log.info("get fees for location {} and case stage {}", location, caseStage);
        List<FeeRecord> feesForLocationAndCastStage = feesDao.fetchFeesForLocationAndCaseStage(location, caseStage);

        Double totalFees = 0.0;
        for (FeeRecord f : feesForLocationAndCastStage) {

            switch (f.getLevelCodeType()) {
                case "O":
                case "OM":
                    List<FeeCalculateRequestLevelCode> levelCodesOfSameCode =
                            levelCodes.stream()
                                .filter(l -> l.getLevelCode().contentEquals(f.getLevelCode()))
                                .toList();

                    if (!levelCodesOfSameCode.isEmpty()) {
                        if (f.getLevelCodeType().contentEquals("O")) {
                            totalFees += f.getAmount();
                        } else {
                            totalFees += levelCodesOfSameCode.getFirst().getFee();
                        }
                    }
                    break;
                default:
                    totalFees += f.getAmount();
                    break;
            }
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
