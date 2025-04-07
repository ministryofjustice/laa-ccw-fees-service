package uk.gov.laa.ccw.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import uk.gov.laa.ccw.exceptions.VatRateNotFoundException;
import uk.gov.laa.ccw.mapper.dao.VatRateMapper;
import uk.gov.laa.ccw.model.VatRate;
import uk.gov.laa.ccw.repository.VatRateRepository;

/**
 * Dao class for vat rates.
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class VatRatesDao {

    private final VatRateRepository vatRateRepository;
    private final VatRateMapper vatRateMapper;

    /**
     * Fetches the VAT.
     *
     * @return the VAT.
     */
    public Double fetchVat() {
        log.info("fetch vat from database");

        VatRate rate = vatRateRepository.findAll().stream()
                .map(vatRateMapper::toVatRate)
                .findFirst()
                .orElseThrow(() -> new VatRateNotFoundException("Unable to retrieve VAT rate from database"));

        return rate.getRatePercentage();

    }

}