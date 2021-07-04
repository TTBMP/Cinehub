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
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Fabio Buracchi
 */
@EqualsAndHashCode(callSuper = true)
public class CustomerProxy extends Customer {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    private boolean isCustomerNameLoaded = false;
    private boolean isCustomerSurnameLoaded = false;
    private boolean isCustomerEmailLoaded = false;
    private boolean isCustomerRoleListLoaded = false;
    private boolean isCustomerTicketListLoaded = false;

    public CustomerProxy(ServiceLocator serviceLocator, String id) {
        super(id, null, null, null, null, null);
        this.userRepository = serviceLocator.getService(UserRepository.class);
        this.ticketRepository = serviceLocator.getService(TicketRepository.class);
    }

    @Override
    public String getName() {
        if (!isCustomerNameLoaded) {
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
        isCustomerNameLoaded = true;
        super.setName(name);
    }

    @Override
    public String getSurname() {
        if (!isCustomerSurnameLoaded) {
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
        isCustomerSurnameLoaded = true;
        super.setSurname(surname);
    }

    @Override
    public String getEmail() {
        if (!isCustomerEmailLoaded) {
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
        isCustomerEmailLoaded = true;
        super.setEmail(email);
    }

    @Override
    public List<Role> getRoleList() {
        if (!isCustomerRoleListLoaded) {
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
        isCustomerRoleListLoaded = true;
        super.setRoleList(roleList);
    }

    @Override
    public boolean hasPermission(Permission requiredPermission) {
        if (!isCustomerRoleListLoaded) {
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
        if (!isCustomerTicketListLoaded) {
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
        isCustomerTicketListLoaded = true;
        super.setOwnedTicketList(ownedTicketList);
    }

}
