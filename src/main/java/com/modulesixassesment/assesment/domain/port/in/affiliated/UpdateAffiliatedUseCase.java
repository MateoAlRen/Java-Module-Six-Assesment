package com.modulesixassesment.assesment.domain.port.in.affiliated;

import com.modulesixassesment.assesment.domain.model.Affiliated;

public interface UpdateAffiliatedUseCase {
    Affiliated update(String document, Affiliated data);
}
