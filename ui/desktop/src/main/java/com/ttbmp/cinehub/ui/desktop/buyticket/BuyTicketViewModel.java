package com.ttbmp.cinehub.ui.desktop.buyticket;


import com.ttbmp.cinehub.app.dto.*;
import com.ttbmp.cinehub.ui.desktop.utilities.ObjectBindings;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.ViewModel;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Ivan Palmieri
 */
public class BuyTicketViewModel implements ViewModel {


    private final StringProperty errorMessage = new SimpleStringProperty();

    private final ObservableList<SeatDto> seatList = FXCollections.observableArrayList();
    private final ObservableList<MovieDto> movieList = FXCollections.observableArrayList();
    private final ObservableList<CinemaDto> cinemaList = FXCollections.observableArrayList();
    private final ObservableList<String> timeOfProjectionList = FXCollections.observableArrayList();
    private final ObservableList<ProjectionDto> projectionOfProjectionTimeList = FXCollections.observableArrayList();
    private final ObjectProperty<TicketDto> selectedTicket = new SimpleObjectProperty<>();
    private final ObjectProperty<MovieDto> selectedMovie = new SimpleObjectProperty<>();
    private final ObjectProperty<CinemaDto> selectedCinema = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> selectedDate = new SimpleObjectProperty<>();
    private final ObjectProperty<ProjectionDto> selectedProjection = new SimpleObjectProperty<>();
    private final ObjectProperty<SeatDto> selectedSeat = new SimpleObjectProperty<>();

    private final StringProperty selectedSeatsName = new SimpleStringProperty();
    private final StringProperty selectedTicketPosition = new SimpleStringProperty();
    private final StringProperty selectedMovieName = new SimpleStringProperty();
    private final StringProperty selectedCinemaName = new SimpleStringProperty();
    private final LongProperty selectedTicketPrice = new SimpleLongProperty();
    private final IntegerProperty selectedPositionSeatInteger = new SimpleIntegerProperty();

    private final StringProperty emailUser = new SimpleStringProperty();
    private final StringProperty numberOfCard = new SimpleStringProperty();
    private final StringProperty cvv = new SimpleStringProperty();
    private final StringProperty freeSeats = new SimpleStringProperty();
    private final StringProperty totalSeats = new SimpleStringProperty();
    private final StringProperty buysSeats = new SimpleStringProperty();
    private final BooleanProperty skipLineOption = new SimpleBooleanProperty();
    private final BooleanProperty heatedArmchairOption = new SimpleBooleanProperty();
    private final BooleanProperty foldingArmchairOption = new SimpleBooleanProperty();
    private final IntegerProperty numberOfSeats = new SimpleIntegerProperty(0);
    private final ObjectProperty<LocalDate> expirationDate = new SimpleObjectProperty<>();
    private final List<BooleanProperty> seatState = new ArrayList<>(60);
    private final IntegerProperty positionSelectedSeat = new SimpleIntegerProperty();
    private final BooleanProperty valueFalseForSeatState = new SimpleBooleanProperty(false);
    private final BooleanProperty valueTrueForSeatState = new SimpleBooleanProperty(true);
    private final BooleanProperty loginRequested = new SimpleBooleanProperty(false);


    public BuyTicketViewModel() {
        selectedMovieName.bind(ObjectBindings.map(selectedMovie, MovieDto::getName));
        StringProperty selectedCinemaAddress = new SimpleStringProperty();
        selectedCinemaAddress.bind(ObjectBindings.map(selectedCinema, CinemaDto::getAddress));
        StringProperty selectedCinemaCity = new SimpleStringProperty();
        selectedCinemaCity.bind(ObjectBindings.map(selectedCinema, CinemaDto::getCity));
        selectedCinemaName.bind(ObjectBindings.map(selectedCinema, CinemaDto::getName));
        selectedTicketPrice.bind(ObjectBindings.map(selectedTicket, TicketDto::getPrice));
        selectedTicketPosition.bind(ObjectBindings.map(selectedTicket, ticket -> ticket.getSeatDto().getPosition()));
        StringProperty selectedMovieRating = new SimpleStringProperty();
        selectedMovieRating.bind(ObjectBindings.map(selectedMovie, MovieDto::getVote));
        StringProperty selectedMovieOverview = new SimpleStringProperty();
        selectedMovieOverview.bind(ObjectBindings.map(selectedMovie, MovieDto::getOverview));
        StringProperty selectedMovieReleased = new SimpleStringProperty();
        selectedMovieReleased.bind(ObjectBindings.map(selectedMovie, MovieDto::getReleases));
        selectedDateProperty().setValue(LocalDate.now());
    }

    public BooleanProperty loginRequestedProperty() {
        return loginRequested;
    }

    public ObjectProperty<LocalDate> expirationDateProperty() {
        return expirationDate;
    }

    public ObservableList<ProjectionDto> projectionTimeListProperty() {
        return projectionOfProjectionTimeList;
    }

    public BooleanProperty trueBooleanProperty() {
        return valueTrueForSeatState;
    }

    public BooleanProperty falseBooleanProperty() {
        return valueFalseForSeatState;
    }

    public IntegerProperty indexToggleSelectedProperty() {
        return positionSelectedSeat;
    }

    public List<BooleanProperty> seatStateProperty() {
        return seatState;
    }

    public IntegerProperty counterForToggleProperty() {
        return numberOfSeats;
    }

    public BooleanProperty skipLineOptionProperty() {
        return skipLineOption;
    }

    public BooleanProperty magicBoxOptionProperty() {
        return heatedArmchairOption;
    }

    public BooleanProperty openBarOptionProperty() {
        return foldingArmchairOption;
    }

    public StringProperty errorMessageProperty() {
        return errorMessage;
    }

    public ObjectProperty<ProjectionDto> selectedProjectionProperty() {
        return selectedProjection;
    }

    public IntegerProperty seatSelectedPositionProperty() {
        return selectedPositionSeatInteger;
    }

    public ObjectProperty<TicketDto> selectedTicketProperty() {
        return selectedTicket;
    }

    public ObjectProperty<LocalDate> selectedDateProperty() {
        return selectedDate;
    }

    public StringProperty selectedMovieNameProperty() {
        return selectedMovieName;
    }

    public StringProperty selectedCinemaNameProperty() {
        return selectedCinemaName;
    }

    public StringProperty selectedSeatPositionProperty() {
        return selectedTicketPosition;
    }

    public LongProperty selectedTicketPriceProperty() {
        return selectedTicketPrice;
    }

    public ObjectProperty<MovieDto> selectedMovieProperty() {
        return selectedMovie;
    }

    public ObservableList<MovieDto> movieListProperty() {
        return movieList;
    }

    public ObjectProperty<CinemaDto> selectedCinemaProperty() {
        return selectedCinema;
    }

    public ObservableList<CinemaDto> cinemaListProperty() {
        return cinemaList;
    }

    public StringProperty freeSeatsProperty() {
        return freeSeats;
    }

    public StringProperty totalSeatsProperty() {
        return totalSeats;
    }

    public StringProperty buysSeatsProperty() {
        return buysSeats;
    }

    public StringProperty selectedSeatsNameProperty() {
        return selectedSeatsName;
    }

    public StringProperty emailUserProperty() {
        return emailUser;
    }

    public StringProperty numberCardProperty() {
        return numberOfCard;
    }

    public StringProperty cvvProperty() {
        return cvv;
    }

    public ObservableList<String> timeOfProjectionListProperty() {
        return timeOfProjectionList;
    }

    public ObservableList<SeatDto> seatListProperty() {
        return seatList;
    }


    public ObjectProperty<SeatDto> selectedSeatProperty() {
        return selectedSeat;
    }


}
