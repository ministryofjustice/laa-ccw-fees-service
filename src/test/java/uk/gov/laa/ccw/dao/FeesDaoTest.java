package uk.gov.laa.ccw.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.laa.ccw.model.FixedFee;
import uk.gov.laa.ccw.entity.FeeEntity;
import uk.gov.laa.ccw.exceptions.FeesException;
import uk.gov.laa.ccw.mapper.dao.FeeMapper;
import uk.gov.laa.ccw.repository.FeeRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FeesDaoTest {
    @Mock
    private FeeRepository repository;

    @Mock
    private FeeMapper mapper;

    @InjectMocks
    private FeesDao classUnderTest;

    @Test
    void shouldFetchFeesForLocation() {

        when(repository.findAllByProviderLocationAndCaseStage(anyString(), anyString()))
                .thenReturn(
                        List.of(
                                FeeEntity.builder()
                                        .feeId(1)
                                        .amount(16.0)
                                        .description("LEVEL 1")
                                        .levelCodeType("A")
                                        .levelCode("LV1")
                                        .build(),
                                FeeEntity.builder()
                                        .feeId(2)
                                        .amount(32.0)
                                        .description("LEVEL 2")
                                        .levelCodeType("A")
                                        .levelCode("LV2")
                                        .build(),
                                FeeEntity.builder()
                                        .feeId(3)
                                        .amount(64.0)
                                        .description("LEVEL 3")
                                        .levelCodeType("O")
                                        .levelCode("LV3")
                                        .build(),
                                FeeEntity.builder()
                                        .feeId(4)
                                        .amount(128.0)
                                        .description("LEVEL 4")
                                        .levelCodeType("OM")
                                        .levelCode("LV4")
                                        .build()
                        )
                );

        List<FixedFee> dataReturned = classUnderTest.fetchFeesForLocationAndCaseStage(
                "LOC1", "CS1");

        assertEquals(4, dataReturned.size());
    }

    @Test
    void shouldThrowExceptionIfFeesNotReadCorrectly() {

        doThrow(new FeesException(""){})
                .when(repository).findAllByProviderLocationAndCaseStage(anyString(), anyString());

        assertThrows(FeesException.class,
                () -> classUnderTest.fetchFeesForLocationAndCaseStage("LOC1", "CS2"));
    }

}