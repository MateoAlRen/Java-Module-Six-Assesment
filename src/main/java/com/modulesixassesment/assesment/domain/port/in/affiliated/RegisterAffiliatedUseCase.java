package com.modulesixassesment.assesment.domain.port.in.affiliated;

import com.modulesixassesment.assesment.domain.model.Affiliated;

public interface RegisterAffiliatedUseCase {
    Affiliated save(Affiliated affiliated);
}
