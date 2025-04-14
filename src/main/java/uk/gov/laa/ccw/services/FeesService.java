package uk.gov.laa.ccw.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccw.exceptions.FeesException;
import uk.gov.laa.ccw.exceptions.VatRateNotFoundException;
import uk.gov.laa.ccw.mapper.dao.FeeMapper;
import uk.gov.laa.ccw.mapper.dao.VatRateMapper;
import uk.gov.laa.ccw.model.FeeTotals;
import uk.gov.laa.ccw.model.FeeDetails;
import uk.gov.laa.ccw.model.FixedFee;
import uk.gov.laa.ccw.model.VatRate;
import uk.gov.laa.ccw.model.api.FeeCalculateRequestLevelCode;
import uk.gov.laa.ccw.repository.FeesRepository;
import uk.gov.laa.ccw.repository.VatRateRepository;

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

    private final FeesRepository repository;
    private final FeeMapper feeMapper;

    private final VatRateRepository vatRateRepository;
    private final VatRateMapper vatRateMapper;

    /**
     * Gets the fees for the given location and case stage.
     *
     * @param location the location
     * @param caseStage the case stage
     * @return the fee record
     */
    public List<FeeDetails> getFeeDetailsForLocationAndCaseStage(
            String location,
            String caseStage) {

        log.info("get fees for location {} and case stage {}", location, caseStage);
        List<FeeDetails> feeDetails =  repository.findAllByProviderLocationAndCaseStage(
                        location, caseStage).stream()
                .map(feeMapper::toFeeDetails).toList();

        if (feeDetails.isEmpty()) {
            throw new FeesException(
                    "Unable to find fixed fees for location "
                            + location
                            + " and case stage "
                            + caseStage);
        }

        return feeDetails;

    }

    /**
     * Gets the fees for the given location and case stage.
     *
     * @param location the location
     * @param caseStage the case stage
     * @return the fee record
     */
    private List<FixedFee> getFeesForLocationAndCaseStage(
            String location,
            String caseStage) {

        log.info("get fees for location {} and case stage {}", location, caseStage);
        List<FixedFee> fixedFees =  repository.findByProviderLocationAndCaseStage(
                        location, caseStage).stream()
                .map(feeMapper::toFee).toList();

        if (fixedFees.isEmpty()) {
            throw new FeesException(
                    "Unable to find fixed fees for location "
                            + location
                            + " and case stage "
                            + caseStage);
        }

        return fixedFees;

    }

    /**
     * Calculates the fee for a given location and case stage.
     *
     * @param location the location
     * @param caseStage the case stage
     * @return the fee
     */
    public FeeTotals calculateFees(String location,
                                   String caseStage,
                                   List<FeeCalculateRequestLevelCode> levelCodes) {

        List<FixedFee> feesForLocationAndCaseStage = getFeesForLocationAndCaseStage(location, caseStage);

        VatRate vatRate = vatRateRepository.findAll().stream()
                .map(vatRateMapper::toVatRate)
                .findFirst()
                .orElseThrow(() -> new VatRateNotFoundException("Unable to retrieve VAT rate from database"));

        double vat = vatRate.getRatePercentage() / 100.0;

        double totalFees = 0.0;
        double totalVatAmount = 0.0;
        double totalPlusVat = 0.0;

        for (FixedFee f : feesForLocationAndCaseStage) {

            Double feeAmount = 0.0;

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
                                feeAmount = (levelCodesOfSameCode.getFirst().getUnits() * f.getAmount());
                                break;
                            case FEE_TYPE_OPTIONAL_FIXED_AMOUNT:
                                feeAmount = levelCodesOfSameCode.getFirst().getFee();
                                break;
                            default:
                                feeAmount= f.getAmount();
                                break;
                        }
                    }
                    break;
                default:
                    feeAmount = f.getAmount();
                    break;
            }

            totalFees +=  feeAmount;
            double vatAmountForFee = feeAmount * vat;
            totalVatAmount += vatAmountForFee;
            double totalPlusVatForFee = feeAmount + vatAmountForFee;
            totalPlusVat  += totalPlusVatForFee;
        }

        return FeeTotals.builder()
                .amount(totalFees)
                .vat(totalVatAmount)
                .total(totalPlusVat)
                .build();
    }
}