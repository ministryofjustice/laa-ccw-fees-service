package uk.gov.laa.ccw.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.laa.ccw.services.MatterCodesService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MatterCodesController {

    private final MatterCodesService service;

    @GetMapping("/v1/matter-codes")
    public List<String> getAllMatterCodeOnes(){
        log.info("retrieve all matter codes");
        return service.getAllMatterCodes();
    }

    @PostMapping("/v1/matter-codes/{code}/mc-combinations")
    public List<String> getMatterCodeTwosForSpecificCode(@PathVariable(value = "code") String code) {
        log.info("retrieve all matter code 2 for a specific code");
        return List.of("X", "Y", "Z");
    }
}
