package uk.gov.laa.ccw.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "PpeCutoffEntity")
@Table(name = "ppe_cutoffs", schema = "ccw")
public class PpeCutoffEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "length", nullable = false)
    private Integer id;

    @Column(name = "a", nullable = false)
    private Integer a;

    @Column(name = "b", nullable = false)
    private Integer b;

    @Column(name = "c", nullable = false)
    private Integer c;

}