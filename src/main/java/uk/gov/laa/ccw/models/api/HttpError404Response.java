package uk.gov.laa.ccw.models.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The model class for HTTP 404 error response.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HttpError404Response {
    public String error;
}
