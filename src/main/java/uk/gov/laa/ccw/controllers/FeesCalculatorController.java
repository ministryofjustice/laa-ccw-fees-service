package uk.gov.laa.ccw.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;

import static org.springframework.http.ResponseEntity.badRequest;

/**
 * Controller for handling the fees requests.
 */
@Slf4j
@RestController
public class FeesCalculatorController {

    /**
     * Doubles a given number.
     *
     * @param number the number
     * @return the result
     */
    @PostMapping("/v1/fees/{number}")
    String doubleGivenNumber(@PathVariable(value = "number") String number) {
        log.info("Supplied value is {}", number);
        float numberAsFloat = Float.parseFloat(number);
        DecimalFormat currencyFormat = new DecimalFormat("###0.00");
        return currencyFormat.format(numberAsFloat * 2);
    }

    /**
     * Handles NumberFormatException.
     *
     * @param exception the number format exception
     * @return the Bad Request response
     */
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<String> handleNumberFormatException(NumberFormatException exception) {
        String message = "Should be supplied a number:" + exception.getMessage();
        log.error(message);
        return badRequest().body(message);
    }

}
