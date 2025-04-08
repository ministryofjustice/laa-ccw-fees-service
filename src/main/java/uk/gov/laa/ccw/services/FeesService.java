package uk.gov.laa.ccw.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccw.dao.FeesDao;
import uk.gov.laa.ccw.dao.VatRatesDao;
import uk.gov.laa.ccw.model.Fee;
import uk.gov.laa.ccw.model.FixedFee;
import uk.gov.laa.ccw.model.api.FeeCalculateRequestLevelCode;

import java.util.List;

/**
 * Service class for the fees.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FeesService {

    private static final String FEE_TYPE_OPTIONAL = "O";
    private static final String FEE_TYPE_OPTIONAL_FIXED_AMOUNT = "OF";
    private static final String FEE_TYPE_OPTIONAL_PER_UNIT = "OU";

    private final FeesDao feesDao;
    private final VatRatesDao vatRatesDao;

    /**
     * Gets the fees for the given location and case stage.
     *
     * @param location the location
     * @param caseStage the case stage
     * @return the fee record
     */
    public List<FixedFee> getFeesForLocationAndCaseStage(
            String location,
            String caseStage) {

        log.info("get fees for location {} and case stage {}", location, caseStage);
        return feesDao.fetchFeesForLocationAndCaseStage(location, caseStage);
    }

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

        List<FixedFee> feesForLocationAndCaseStage = getFeesForLocationAndCaseStage(location, caseStage);

        Double totalFees = 0.0;
        for (FixedFee f : feesForLocationAndCaseStage) {

            switch (f.getLevelCodeType()) {
                case FEE_TYPE_OPTIONAL:
                case FEE_TYPE_OPTIONAL_FIXED_AMOUNT:
                case FEE_TYPE_OPTIONAL_PER_UNIT:
                    List<FeeCalculateRequestLevelCode> levelCodesOfSameCode =
                            levelCodes.stream()
                                    .filter(l -> l.getLevelCode().contentEquals(f.getLevelCode()))
                                    .toList();

                    if (!levelCodesOfSameCode.isEmpty()) {
                        switch (f.getLevelCodeType()) {
                            case FEE_TYPE_OPTIONAL_PER_UNIT:
                                totalFees += (levelCodesOfSameCode.getFirst().getUnits() * f.getAmount());
                                break;
                            case FEE_TYPE_OPTIONAL_FIXED_AMOUNT:
                                totalFees += levelCodesOfSameCode.getFirst().getFee();
                                break;
                            default:
                                totalFees += f.getAmount();
                                break;
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

        Double vatAmount = vat / 100.0;
        vatAmount *= totalFees;
        Double totalPlusVat = totalFees + vatAmount;

        return Fee.builder()
                .amount(totalFees)
                .vat(vatAmount)
                .total(totalPlusVat)
                .build();
    }
}