package uk.gov.laa.ccw.model.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The model class for HTTP 500 error response.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HttpError500Response {
    private String error;
}