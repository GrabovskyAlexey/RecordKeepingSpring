package ru.grabovsky.recordkeeping.services.abstaract;

import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * UserService interface
 *
 * @author GrabovskyAlexey
 */
public interface UserService extends UserDetailsService {
    String getTokenByUsername(String username);
}
