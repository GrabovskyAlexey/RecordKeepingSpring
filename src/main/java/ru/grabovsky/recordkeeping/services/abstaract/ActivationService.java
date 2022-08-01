package ru.grabovsky.recordkeeping.services.abstaract;

import ru.grabovsky.recordkeeping.models.dto.ActivationRequestDto;

public interface ActivationService {
    void activate(ActivationRequestDto request);
}
