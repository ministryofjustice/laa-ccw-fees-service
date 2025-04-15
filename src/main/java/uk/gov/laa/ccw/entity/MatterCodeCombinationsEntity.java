package uk.gov.laa.ccw.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The entity class for Matter Codes Two.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "VW_MATTER_CODE_COMBINATIONS")
public class MatterCodeCombinationsEntity {
    @Id
    @Column(name = "MATTER_CODE_ID")
    private String matterCodeId;
    private String description;
    @Column(name = "MATTER_CODE_1")
    private String matterCode1;

}
