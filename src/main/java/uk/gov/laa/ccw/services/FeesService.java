package uk.gov.laa.ccw.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccw.exceptions.FeesException;
import uk.gov.laa.ccw.exceptions.VatRateNotFoundException;
import uk.gov.laa.ccw.mapper.dao.FeeMapper;
import uk.gov.laa.ccw.mapper.dao.VatRateMapper;
import uk.gov.laa.ccw.model.Fee;
import uk.gov.laa.ccw.model.FeeDetails;
import uk.gov.laa.ccw.model.FeeElement;
import uk.gov.laa.ccw.model.VatRate;
import uk.gov.laa.ccw.model.api.FeeCalculateRequestLevelCode;
import uk.gov.laa.ccw.repository.FeeDetailsRepository;
import uk.gov.laa.ccw.repository.FeesRepository;
import uk.gov.laa.ccw.repository.VatRateRepository;

import java.text.DecimalFormat;
import java.util.ArrayList;
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
    private final FeeDetailsRepository feeDetailsRepository;
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
        List<FeeDetails> feeDetails =  feeDetailsRepository.findByProviderLocationAndCaseStage(
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
    private List<Fee> getFeesForLocationAndCaseStage(
            String location,
            String caseStage) {

        log.info("get fees for location {} and case stage {}", location, caseStage);
        List<Fee> fixedFees =  repository.findByProviderLocationAndCaseStage(
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
    public List<FeeElement> calculateFees(String location,
                                          String caseStage,
                                          List<FeeCalculateRequestLevelCode> levelCodes) {

        List<Fee> feesForLocationAndCaseStage = getFeesForLocationAndCaseStage(location, caseStage);

        VatRate vatRate = vatRateRepository.findAll().stream()
                .map(vatRateMapper::toVatRate)
                .findFirst()
                .orElseThrow(() -> new VatRateNotFoundException("Unable to retrieve VAT rate from database"));

        double vat = vatRate.getRatePercentage() / 100.0;

        double totalFees = 0.0;
        double totalVatAmount = 0.0;
        double totalPlusVat = 0.0;

        DecimalFormat numberFormatter = new DecimalFormat("#0.00");
        List<FeeElement> result = new ArrayList<>();

        for (Fee f : feesForLocationAndCaseStage) {

            Double feeAmount = 0.0;
            Double feeUnits = 1.0;
            boolean feeCalculated = false;

            switch (f.getLevelCodeType()) {
                case FEE_TYPE_OPTIONAL:
                case FEE_TYPE_OPTIONAL_FIXED_AMOUNT:
                case FEE_TYPE_OPTIONAL_PER_UNIT:
                    List<FeeCalculateRequestLevelCode> levelCodesOfSameCode =
                            levelCodes.stream()
                                    .filter(l -> l.getLevelCode().contentEquals(f.getLevelCode()))
                                    .toList();

                    if (!levelCodesOfSameCode.isEmpty()) {
                        feeCalculated = true;

                        switch (f.getLevelCodeType()) {
                            case FEE_TYPE_OPTIONAL_PER_UNIT:
                                feeUnits = levelCodesOfSameCode.getFirst().getUnits();
                                feeAmount = (feeUnits * f.getAmount());
                                break;
                            case FEE_TYPE_OPTIONAL_FIXED_AMOUNT:
                                feeAmount = levelCodesOfSameCode.getFirst().getFee();
                                break;
                            default:
                                feeAmount = f.getAmount();
                                break;
                        }
                    }
                    break;
                default:
                    feeAmount = f.getAmount();
                    feeCalculated = true;
                    break;
            }

            if (feeCalculated) {
                totalFees +=  feeAmount;
                double vatAmountForFee = feeAmount * vat;
                totalVatAmount += vatAmountForFee;
                double totalPlusVatForFee = feeAmount + vatAmountForFee;
                totalPlusVat  += totalPlusVatForFee;

                result.add(
                        FeeElement.builder()
                                .feeType(f.getLevelCode())
                                .unit(numberFormatter.format(feeUnits))
                                .amount(numberFormatter.format(feeAmount))
                                .vat(numberFormatter.format(vatAmountForFee))
                                .total(numberFormatter.format(totalPlusVatForFee))
                                .build()
                );
            }
        }

        result.add(
                FeeElement.builder()
                .feeType("totals")
                .unit("1.0")
                .amount(numberFormatter.format(totalFees))
                .vat(numberFormatter.format(totalVatAmount))
                .total(numberFormatter.format(totalPlusVat))
                .build()
        );

        return result;
    }
}