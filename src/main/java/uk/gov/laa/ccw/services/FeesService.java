package uk.gov.laa.ccw.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccw.dao.FeesDao;
import uk.gov.laa.ccw.dao.VatRatesDao;
import uk.gov.laa.ccw.models.Fee;
import uk.gov.laa.ccw.models.FeeRecord;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeesService {

    private final FeesDao feesDao;
    private final VatRatesDao vatRatesDao;

    public Fee calculateFees(String location,
                             String matterCode1,
                             String matterCode2,
                             String caseStage) {

        log.info("get fees for location {}", location);
        List<FeeRecord> feesForLocation = feesDao.fetchFeesForLocation(location);

        log.info("get fees for case stage {}", caseStage);
        List<FeeRecord> feesForCaseStages = feesForLocation
                                    .stream()
                                    .filter(c-> c.getCaseStage().contentEquals(caseStage))
                                    .toList();
        Double totalFees = 0.0;
        for (FeeRecord f : feesForCaseStages) {
            totalFees += f.getAmount();
        }

        Double vat = vatRatesDao.fetchVat();
        log.info("add in vat of {}%", vat);

        return Fee.builder()
                .amount(totalFees)
                .vat(vat)
                .total(totalFees * (1.0 + (vat/100.0)))
                .build();
    }
}
