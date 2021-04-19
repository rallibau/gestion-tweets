package com.rallibau.shared.infrastructure;

import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.UuidGenerator;

import java.util.UUID;

@Service
public final class JavaUuidGenerator implements UuidGenerator {
    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
