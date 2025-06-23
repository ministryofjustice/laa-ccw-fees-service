package uk.gov.laa.ccw.services;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccw.model.Fee;
import uk.gov.laa.ccw.model.FeeElement;
import uk.gov.laa.ccw.model.VatRate;
import uk.gov.laa.ccw.model.api.FeeCalculateRequestLevelCode;

import java.util.ArrayList;
import java.util.List;

@Service
public class DroolsFeeService {
    private final KieContainer kieContainer;

    public DroolsFeeService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public List<FeeElement> calculateFeesWithDrools(List<Fee> fees, VatRate vatRate, List<FeeCalculateRequestLevelCode> levelCodes) {
        List<FeeElement> feeElements;
        try (KieSession session = kieContainer.newKieSession("feesSession")) {

            if (session == null) {
                throw new IllegalStateException("feesSession not found in KIE container!");
            }
            feeElements = new ArrayList<>();
            session.setGlobal("vat", vatRate.getRatePercentage() / 100.0);
            session.setGlobal("fees", fees);
            session.setGlobal("levelCodes", levelCodes);
            session.setGlobal("feeElements", feeElements);

            for (Fee fee : fees) {
                session.insert(fee);
            }

            session.fireAllRules();
            session.dispose();
        }
        return feeElements;
    }
}
