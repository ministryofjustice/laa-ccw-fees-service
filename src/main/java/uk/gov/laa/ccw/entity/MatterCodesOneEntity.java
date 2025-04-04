package uk.gov.laa.ccw.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The entity class for Matter Codes One.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MATTER_CODES_1")
public class MatterCodesOneEntity {
    @Id
    private String matterCodeId;

    private String description;

    private String lawType;
}
