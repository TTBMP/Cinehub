package com.ttbmp.cinehub.core.services;

import com.ttbmp.cinehub.core.entity.session.Session;

/**
 * @author Fabio Buracchi
 */
public interface AuthenticationService {

    Session getCurrentSession();

}
