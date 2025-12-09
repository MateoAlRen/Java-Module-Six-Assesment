package com.modulesixassesment.assesment.aplication.usecases.affiliated;

import com.modulesixassesment.assesment.domain.enums.AffiliatedStatus;
import com.modulesixassesment.assesment.domain.model.Affiliated;
import com.modulesixassesment.assesment.domain.port.in.affiliated.RegisterAffiliatedUseCase;
import com.modulesixassesment.assesment.domain.port.out.AffiliatedRepositoryPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
public class RegisterAffiliatedService implements RegisterAffiliatedUseCase {
    private final AffiliatedRepositoryPort affiliatedRepositoryPort;

    @Override
    public Affiliated save(Affiliated affiliated) {
        if (affiliated.getSalary() <= 0){
            throw new IllegalArgumentException("The salary must be greater than zero.");
        }
        if (affiliatedRepositoryPort.existsByDocument(affiliated.getDocument())){
            throw new IllegalArgumentException("Document already exists!");
        }

        if(affiliated.getDateAffiliated() == null){
            affiliated.setDateAffiliated(LocalDate.now());
        }
        affiliated.setStatus(AffiliatedStatus.ACTIVE);
        return affiliatedRepositoryPort.save(affiliated);
    }
}
