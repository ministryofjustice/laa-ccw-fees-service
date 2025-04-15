package uk.gov.laa.ccw.model.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The model class for HTTP 400 error response.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HttpError400Response {
    private String error;
}