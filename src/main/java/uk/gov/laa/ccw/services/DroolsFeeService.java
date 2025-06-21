package uk.gov.laa.ccw.services;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;
import uk.gov.laa.ccw.model.Fee;
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

    public List<String> calculateFeesWithDrools(List<Fee> fees, VatRate vatRate, List<FeeCalculateRequestLevelCode> levelCodes) {
        KieSession session = kieContainer.newKieSession("feesSession");

        if (session == null) {
            session = kieContainer.newKieSession();
          //  throw new IllegalStateException("feesSession not found in KIE container!");
        }
        List<String> result = new ArrayList<>();
        session.setGlobal("vat", vatRate.getRatePercentage() / 100.0);
        session.setGlobal("fees", result);
        session.setGlobal("levelCodes", levelCodes);

        for (Fee fee : fees) {
            session.insert(fee);
        }

        session.fireAllRules();
        session.dispose();
        return result;
    }
}
