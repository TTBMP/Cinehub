package com.ttbmp.cinehub.app.repository.projection;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.app.repository.cinema.CinemaRepository;
import com.ttbmp.cinehub.app.repository.employee.projectionist.ProjectionistRepository;
import com.ttbmp.cinehub.app.repository.hall.HallRepository;
import com.ttbmp.cinehub.app.repository.movie.MovieRepository;
import com.ttbmp.cinehub.app.repository.ticket.TicketRepository;
import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.Movie;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.shift.ProjectionistShift;
import com.ttbmp.cinehub.service.persistence.CinemaDatabase;
import com.ttbmp.cinehub.service.persistence.dao.ProjectionDao;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.datasource.JdbcDataSourceProvider;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;
import java.util.List;
import java.util.stream.Collectors;

public class JdbcProjectionRepository implements ProjectionRepository{

    private final ServiceLocator serviceLocator;

    private ProjectionDao projectionDao = null;



    public JdbcProjectionRepository(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Override
    public List<Projection> getProjectionList(Cinema cinema, Movie movie, String date) throws RepositoryException {
        try {
            List<com.ttbmp.cinehub.service.persistence.entity.Projection> projectionList = getProjectionDao().getProjectionListByCinemaAndMovieAndDate(cinema.getId(), movie.getId(), date);
            return projectionList.stream()
                    .map(projection -> new ProjectionProxy(projection.getId(), projection.getDate(), projection.getStartTime(),
                            serviceLocator.getService(MovieRepository.class),
                            serviceLocator.getService(HallRepository.class),
                            serviceLocator.getService(CinemaRepository.class),
                            serviceLocator.getService(ProjectionistRepository.class),
                            serviceLocator.getService(TicketRepository.class)
                    )).collect(Collectors.toList());
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public List<Projection> getProjectionList(String date) throws RepositoryException {
        try {
            List<com.ttbmp.cinehub.service.persistence.entity.Projection> projectionList = getProjectionDao().getProjectionListByDate(date);
            return projectionList.stream()
                    .map(projection -> new ProjectionProxy(projection.getId(), projection.getDate(), projection.getStartTime(),
                            serviceLocator.getService(MovieRepository.class),
                            serviceLocator.getService(HallRepository.class),
                            serviceLocator.getService(CinemaRepository.class),
                            serviceLocator.getService(ProjectionistRepository.class),
                            serviceLocator.getService(TicketRepository.class)
                    )).collect(Collectors.toList());
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public List<Projection> getProjectionList(Movie movie, String date) throws RepositoryException {
        try {
            List<com.ttbmp.cinehub.service.persistence.entity.Projection> projectionList = getProjectionDao().getProjectionListByDateAndMovie(movie.getId(), date);
            return projectionList.stream()
                    .map(projection -> new ProjectionProxy(projection.getId(), projection.getDate(), projection.getStartTime(),
                            serviceLocator.getService(MovieRepository.class),
                            serviceLocator.getService(HallRepository.class),
                            serviceLocator.getService(CinemaRepository.class),
                            serviceLocator.getService(ProjectionistRepository.class),
                            serviceLocator.getService(TicketRepository.class)
                    )).collect(Collectors.toList());
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public List<Projection> getProjectionList(ProjectionistShift shift) throws RepositoryException {
        try {
            List<com.ttbmp.cinehub.service.persistence.entity.Projection> projectionList = getProjectionDao().getProjectionListByProjectionist(shift.getEmployee().getId());
            return projectionList.stream()
                    .map(projection -> new ProjectionProxy(projection.getId(), projection.getDate(), projection.getStartTime(),
                            serviceLocator.getService(MovieRepository.class),
                            serviceLocator.getService(HallRepository.class),
                            serviceLocator.getService(CinemaRepository.class),
                            serviceLocator.getService(ProjectionistRepository.class),
                            serviceLocator.getService(TicketRepository.class)
                    )).collect(Collectors.toList());
        } catch (DaoMethodException e) {
            throw new RepositoryException(e.getMessage());
        }
    }


    private ProjectionDao getProjectionDao() {
        if (projectionDao == null) {
            try {
                this.projectionDao = JdbcDataSourceProvider.getDataSource(CinemaDatabase.class).getProjectionDao();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        return projectionDao;
    }
}
