package com.ttbmp.cinehub.app.repository.customer;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.LazyLoadingException;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.ticket.TicketRepository;
import com.ttbmp.cinehub.app.repository.user.UserRepository;
import com.ttbmp.cinehub.domain.Customer;
import com.ttbmp.cinehub.domain.security.Permission;
import com.ttbmp.cinehub.domain.security.Role;
import com.ttbmp.cinehub.domain.ticket.Ticket;

import java.util.List;

/**
 * @author Fabio Buracchi
 */
public class CustomerProxy extends Customer {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    private boolean isNameLoaded = false;
    private boolean isSurnameLoaded = false;
    private boolean isEmailLoaded = false;
    private boolean isRoleListLoaded = false;
    private boolean isTicketListLoaded = false;

    public CustomerProxy(ServiceLocator serviceLocator, String id) {
        super(id, null, null, null, null, null);
        this.userRepository = serviceLocator.getService(UserRepository.class);
        this.ticketRepository = serviceLocator.getService(TicketRepository.class);
    }

    @Override
    public String getName() {
        if (!isNameLoaded) {
            try {
                setName(userRepository.getUser(getId()).getName());
            } catch (RepositoryException e) {
                throw new LazyLoadingException(e.getMessage());
            }
        }
        return super.getName();
    }

    @Override
    public void setName(String name) {
        isNameLoaded = true;
        super.setName(name);
    }

    @Override
    public String getEmail() {
        if (!isEmailLoaded) {
            try {
                setEmail(userRepository.getUser(getId()).getEmail());
            } catch (RepositoryException e) {
                throw new LazyLoadingException(e.getMessage());
            }
        }
        return super.getEmail();
    }

    @Override
    public void setEmail(String email) {
        isEmailLoaded = true;
        super.setEmail(email);
    }

    @Override
    public String getSurname() {
        if (!isSurnameLoaded) {
            try {
                setSurname(userRepository.getUser(getId()).getSurname());
            } catch (RepositoryException e) {
                throw new LazyLoadingException(e.getMessage());
            }
        }
        return super.getSurname();
    }

    @Override
    public void setSurname(String surname) {
        isSurnameLoaded = true;
        super.setSurname(surname);
    }

    @Override
    public boolean hasPermission(Permission requiredPermission) {
        if (!isRoleListLoaded) {
            try {
                setRoleList(userRepository.getUser(getId()).getRoleList());
            } catch (RepositoryException e) {
                throw new LazyLoadingException(e.getMessage());
            }
        }
        return super.hasPermission(requiredPermission);
    }

    @Override
    public List<Ticket> getOwnedTicketList() {
        if (!isTicketListLoaded) {
            try {
                setOwnedTicketList(ticketRepository.getTicketList(this));
            } catch (RepositoryException e) {
                throw new LazyLoadingException(e.getMessage());
            }
        }
        return super.getOwnedTicketList();
    }

    @Override
    public void setOwnedTicketList(List<Ticket> ownedTicketList) {
        isTicketListLoaded = true;
        super.setOwnedTicketList(ownedTicketList);
    }

    @Override
    public List<Role> getRoleList() {
        if (!isRoleListLoaded) {
            try {
                setRoleList(userRepository.getUser(getId()).getRoleList());
            } catch (RepositoryException e) {
                throw new LazyLoadingException(e.getMessage());
            }
        }
        return super.getRoleList();
    }

    @Override
    public void setRoleList(List<Role> roleList) {
        isRoleListLoaded = true;
        super.setRoleList(roleList);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
