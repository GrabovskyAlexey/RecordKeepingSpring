package ru.grabovsky.recordkeeping.services.abstaract;

import ru.grabovsky.recordkeeping.models.dto.RegisterRequestDto;

/**
 * RegisterService interface
 *
 * @author GrabovskyAlexey
 */
public interface RegisterService {
    void register(RegisterRequestDto request);
}
