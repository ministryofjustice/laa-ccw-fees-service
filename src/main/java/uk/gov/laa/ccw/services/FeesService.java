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
    public static final String TOTALS = "totals";
    public static final String FEES_PATTERN = "#0.00";
    public static final DecimalFormat numberFormatter = new DecimalFormat(FEES_PATTERN);
    private final FeesRepository repository;
    private final FeeDetailsRepository feeDetailsRepository;
    private final FeeMapper feeMapper;

    private final VatRateRepository vatRateRepository;
    private final VatRateMapper vatRateMapper;
    private final DroolsFeeService droolsFeeService;

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

        List<Fee> feeList = getFeesForLocationAndCaseStage(location, caseStage);

        VatRate vatRate = vatRateRepository.findAll().stream()
                .map(vatRateMapper::toVatRate)
                .findFirst()
                .orElseThrow(() -> new VatRateNotFoundException("Unable to retrieve VAT rate from database"));

        List<FeeElement> feeElementList =  droolsFeeService.calculateFeesWithDrools(feeList, vatRate, levelCodes);
        return getFeeElementList(feeElementList);
    }

    private List<FeeElement> getFeeElementList(List<FeeElement> feeElementList) {

        double totalFees = 0.0;
        double totalVatAmount = 0.0;
        double totalPlusVat;
        for(FeeElement feeElement : feeElementList) {
            totalFees = totalFees + Double.parseDouble(feeElement.getAmount());
            totalVatAmount = totalVatAmount + Double.parseDouble(feeElement.getVat());
        }
        totalPlusVat = totalFees + totalVatAmount;

        feeElementList.add(
                FeeElement.builder()
                        .feeType(TOTALS)
                        .unit("1.0")
                        .amount(numberFormatter.format(totalFees))
                        .vat(numberFormatter.format(totalVatAmount))
                        .total(numberFormatter.format(totalPlusVat))
                        .build()
        );
        return feeElementList;
    }

}