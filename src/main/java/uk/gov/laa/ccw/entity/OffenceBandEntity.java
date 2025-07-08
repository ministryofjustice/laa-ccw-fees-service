package uk.gov.laa.ccw.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "OffenceBandEntity")
@Table(name = "offence_bands", schema = "ccw")
public class OffenceBandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "label", nullable = false, length = Integer.MAX_VALUE)
    private String label;

    @Column(name = "description", nullable = false, length = Integer.MAX_VALUE)
    private String description;

}