package com.modulesixassesment.assesment.aplication.usecases.affiliated;

import com.modulesixassesment.assesment.domain.model.Affiliated;
import com.modulesixassesment.assesment.domain.port.in.affiliated.UpdateAffiliatedUseCase;
import com.modulesixassesment.assesment.domain.port.out.AffiliatedRepositoryPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateAffiliatedService implements UpdateAffiliatedUseCase {

    private final AffiliatedRepositoryPort affiliatedRepositoryPort;

    @Override
    public Affiliated update(String document, Affiliated data) {
        Affiliated exists = affiliatedRepositoryPort.findByDocument(document)
                .orElseThrow(() -> new RuntimeException("Member no exists"));
        // Update allowed data
        if (data.getSalary() <= 0 ) throw new IllegalArgumentException("Salary must be greater than zero. ");
        exists.setName(data.getName());
        exists.setSalary(data.getSalary());
        // The use can't change the document
        exists.setDateAffiliated(data.getDateAffiliated() != null ? data.getDateAffiliated() : exists.getDateAffiliated());
        exists.setStatus((data.getStatus() != null ? data.getStatus() : exists.getStatus()));
        return affiliatedRepositoryPort.save(exists);
    }
}
