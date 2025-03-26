package uk.gov.laa.ccw.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;

import static org.springframework.http.ResponseEntity.badRequest;

@Slf4j
@RestController
public class FeesCalculatorController {

    @PostMapping("/v1/fees/{number}")
    String doubleANumber(@PathVariable(value = "number") String number) {
        log.info("Supplied value is {}", number);
        float numberAsFloat = Float.parseFloat(number);
        DecimalFormat currencyFormat = new DecimalFormat("###0.00");
        return currencyFormat.format(numberAsFloat * 2);
    }


    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<String> shouldBeANumber(NumberFormatException exception) {
        String message = "Should be supplied a number:" + exception.getMessage();
        log.error(message);
        return badRequest().body(message);
    }

}
