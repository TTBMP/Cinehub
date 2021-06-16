package com.ttbmp.cinehub.app.repository.projection;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.utilities.repository.JdbcRepository;
import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.Hall;
import com.ttbmp.cinehub.domain.Movie;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.shift.ProjectionistShift;
import com.ttbmp.cinehub.domain.ticket.component.Ticket;
import com.ttbmp.cinehub.service.persistence.dao.ProjectionDao;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import java.util.List;
import java.util.stream.Collectors;

public class JdbcProjectionRepository extends JdbcRepository implements ProjectionRepository {

    public JdbcProjectionRepository(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Override
    public Projection getProjection(int id) throws RepositoryException {
        try {
            var projection = getDao(ProjectionDao.class).getProjectionById(id);
            return new ProjectionProxy(
                    getServiceLocator(),
                    projection.getId(),
                    projection.getDate(),
                    projection.getStartTime(),
                    (long) projection.getBasePrice()
            );
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public List<Projection> getProjectionList(Cinema cinema, Movie movie, String date) throws RepositoryException {
        try {
            var projectionList = getDao(ProjectionDao.class).getProjectionListByCinemaAndMovieAndDate(cinema.getId(), movie.getId(), date);
            return projectionList.stream()
                    .map(projection -> new ProjectionProxy(
                            getServiceLocator(),
                            projection.getId(),
                            projection.getDate(),
                            projection.getStartTime(),
                            (long) projection.getBasePrice()
                    )).collect(Collectors.toList());
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }


    @Override
    public Projection getProjection(String date, String time, Hall hall) throws RepositoryException {
        try {
            var projection = getDao(ProjectionDao.class).getProjectionByDateAndTimeAndHallId(date, time, hall.getId());
            return new ProjectionProxy(
                    getServiceLocator(),
                    projection.getId(),
                    projection.getDate(),
                    projection.getStartTime(),
                    (long) projection.getBasePrice()
            );
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public Projection getProjection(Ticket ticket) throws RepositoryException {
        try {
            var projection = getDao(ProjectionDao.class).getProjectionByTicketId(ticket.getId());
            return new ProjectionProxy(
                    getServiceLocator(),
                    projection.getId(),
                    projection.getDate(),
                    projection.getStartTime(),
                    (long) projection.getBasePrice()
            );
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public List<Projection> getProjectionList(ProjectionistShift shift) throws RepositoryException {
        try {
            var projectionList = getDao(ProjectionDao.class).getProjectionListByProjectionistShift(shift.getId());
            return projectionList.stream()
                    .map(projection -> new ProjectionProxy(
                            getServiceLocator(),
                            projection.getId(),
                            projection.getDate(),
                            projection.getStartTime(),
                            (long) projection.getBasePrice()
                    )).collect(Collectors.toList());
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

}
