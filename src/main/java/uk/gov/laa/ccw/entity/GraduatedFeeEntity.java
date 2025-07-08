package uk.gov.laa.ccw.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "GraduatedFeeEntity")
@Table(name = "graduated_fees", schema = "ccw")
public class GraduatedFeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "description", nullable = false, length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "type", nullable = false, length = Integer.MAX_VALUE)
    private String type;

    @Column(name = "minimum", precision = 7, scale = 2)
    private BigDecimal minimum;

    @Column(name = "maximum", precision = 7, scale = 2)
    private BigDecimal maximum;

    @Column(name = "basic_fee", nullable = false, precision = 7, scale = 2)
    private BigDecimal basicFee;

    @Column(name = "fee_per_unit", precision = 7, scale = 2)
    private BigDecimal feePerUnit;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "proxy_fee", precision = 7, scale = 2)
    private BigDecimal proxyFee;

}