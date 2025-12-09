package com.modulesixassesment.assesment;

import com.modulesixassesment.assesment.domain.enums.AffiliatedStatus;
import com.modulesixassesment.assesment.domain.enums.RequestStatus;
import com.modulesixassesment.assesment.domain.enums.RiskLevel;
import com.modulesixassesment.assesment.domain.model.Affiliated;
import com.modulesixassesment.assesment.domain.model.Request;
import com.modulesixassesment.assesment.domain.model.RiskEvaluation;
import com.modulesixassesment.assesment.domain.port.in.request.CreateRequestUseCase;
import com.modulesixassesment.assesment.domain.port.out.AffiliatedRepositoryPort;
import com.modulesixassesment.assesment.domain.port.out.RequestRepositoryPort;
import com.modulesixassesment.assesment.domain.port.out.RiskClientPort;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@RequiredArgsConstructor
class MakeRequest {

    private AffiliatedRepositoryPort afiliadoRepo;
    private RequestRepositoryPort solicitudRepo;
    private RiskClientPort riskClient;
    private CreateRequestUseCase service;

    @BeforeEach
    void setUp() {
        afiliadoRepo = mock(AffiliatedRepositoryPort.class);
        solicitudRepo = mock(RequestRepositoryPort.class);
        riskClient = mock(RiskClientPort.class);
    }

    @Test
    void shouldApproveSolicitudLowRisk() {
        Affiliated afiliado = new Affiliated(1L, "123", "Juan", LocalDate.now().minusMonths(12), 5000.0, AffiliatedStatus.ACTIVE);
        Request solicitud = new Request(null, "123", 20000, 12, 5.0, null, null, null);
        RiskEvaluation eval = new RiskEvaluation(700, RiskLevel.LOW, "Buen historial", null);

        when(afiliadoRepo.findByDocument("123")).thenReturn(Optional.of(afiliado));
        when(solicitudRepo.save(any())).thenAnswer(i -> i.getArgument(0));
        when(riskClient.evaluateRisk("123", 20000, 12)).thenReturn(eval);

        Request result = service.save(solicitud);

        assertEquals(RequestStatus.APPROVED, result.getStatus());
        assertNotNull(result.getEvaluation());
        assertEquals("Aprobado por reglas internas", result.getEvaluation().getMotive());
        verify(solicitudRepo, times(2)).save(any());
    }

}

