package ru.grabovsky.recordkeeping.services.abstaract;

import ru.grabovsky.recordkeeping.models.dto.RegisterRequestDto;

public interface RegisterService {
    void register(RegisterRequestDto request);
}
