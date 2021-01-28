package com.ttbmp.cinehub.app.client.desktop.ui.buyticket;


import com.ttbmp.cinehub.app.client.desktop.utilities.ObjectBindings;
import com.ttbmp.cinehub.app.client.desktop.utilities.ui.ViewModel;
import com.ttbmp.cinehub.core.dto.*;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Palmieri Ivan
 */

public class BuyTicketViewModel implements ViewModel {


    private final StringProperty emailUser = new SimpleStringProperty();
    private final StringProperty nameUser = new SimpleStringProperty();
    private final StringProperty surnameUser = new SimpleStringProperty();
    private final StringProperty numberOfCardUser = new SimpleStringProperty();
    private final StringProperty txtCvv = new SimpleStringProperty();
    private final StringProperty freeSeats = new SimpleStringProperty();

    private final StringProperty totalSeats = new SimpleStringProperty();
    private final StringProperty buysSeats = new SimpleStringProperty();
    private final ObservableList<SeatDto> seatList = FXCollections.observableArrayList();
    private final ObservableList<MovieDto> movieList = FXCollections.observableArrayList();
    private final ObservableList<CinemaDto> cinemaList = FXCollections.observableArrayList();
    private final ObservableList<String> timeOfProjectionList = FXCollections.observableArrayList();
    private final ObjectProperty<TicketDto> selectedTicket = new SimpleObjectProperty<>();
    private final ObjectProperty<MovieDto> selectedMovie = new SimpleObjectProperty<>();
    private final ObjectProperty<String> selectedTime = new SimpleObjectProperty<>();
    private final StringProperty selectedSeats = new SimpleStringProperty();
    private final ObjectProperty<CinemaDto> selectedCinema = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> selectedDate = new SimpleObjectProperty<>();
    private final ObjectProperty<ProjectionDto> selectedProjection = new SimpleObjectProperty<>();
    private final StringProperty selectedTicketPosition = new SimpleStringProperty();
    private final StringProperty selectedMovieName = new SimpleStringProperty();
    private final StringProperty selectedCinemaName = new SimpleStringProperty();
    private final LongProperty selectedTicketPrice = new SimpleLongProperty();
    private final IntegerProperty selectedPositionSeatInteger = new SimpleIntegerProperty();

    private final ObjectProperty<LocalDate> fieldExpirationDatePicker = new SimpleObjectProperty<>();

    private final StringProperty emailError = new SimpleStringProperty();
    private final StringProperty movieError = new SimpleStringProperty();
    private final StringProperty cinemaError = new SimpleStringProperty();
    private final StringProperty seatError = new SimpleStringProperty();
    private final StringProperty paymentError = new SimpleStringProperty();
    private final StringProperty selectedSeatPrice = new SimpleStringProperty();


    private final BooleanProperty skipLineOption = new SimpleBooleanProperty();
    private final BooleanProperty heatedArmchairOption = new SimpleBooleanProperty();
    private final BooleanProperty foldingArmchairOption = new SimpleBooleanProperty();



    private final IntegerProperty numberOfToggle = new SimpleIntegerProperty(0);
    private final List<BooleanProperty> toggleState = new ArrayList<>(60);
    private final IntegerProperty positionSelectedToggle = new SimpleIntegerProperty();
    private final BooleanProperty valueFalseForToggleState = new SimpleBooleanProperty(false);
    private final BooleanProperty valueTrueForToggleState = new SimpleBooleanProperty(true);


    public BooleanProperty valueTrueForToggleStateProperty() {
        return valueTrueForToggleState;
    }


    public BooleanProperty valueFalseForToggleStateProperty() {
        return valueFalseForToggleState;
    }


    public IntegerProperty positionSelectedToggleProperty() {
        return positionSelectedToggle;
    }

    public List<BooleanProperty> getToggleState() {
        return toggleState;
    }

    public IntegerProperty numberOfToggleProperty() {
        return numberOfToggle;
    }



    public BuyTicketViewModel() {
        selectedMovieName.bind(ObjectBindings.map(selectedMovie, MovieDto::getName));
        StringProperty selectedCinemaAddress = new SimpleStringProperty();
        selectedCinemaAddress.bind(ObjectBindings.map(selectedCinema, CinemaDto::getAddress));
        StringProperty selectedCinemaCity = new SimpleStringProperty();
        selectedCinemaCity.bind(ObjectBindings.map(selectedCinema, CinemaDto::getCity));
        selectedCinemaName.bind(ObjectBindings.map(selectedCinema, CinemaDto::getName));
        selectedTicketPrice.bind(ObjectBindings.map(selectedTicket, TicketDto::getPrice));
        selectedTicketPosition.bind(ObjectBindings.map(selectedTicket, TicketDto::getPosition));
        StringProperty selectedMovieRating = new SimpleStringProperty();
        selectedMovieRating.bind(ObjectBindings.map(selectedMovie, MovieDto::getVote));
        StringProperty selectedMovieDuration = new SimpleStringProperty();
        selectedMovieDuration.bind(ObjectBindings.map(selectedMovie, MovieDto::getDuration));
        StringProperty selectedMovieOverview = new SimpleStringProperty();
        selectedMovieOverview.bind(ObjectBindings.map(selectedMovie, MovieDto::getOverview));
        StringProperty selectedMovieReleased = new SimpleStringProperty();
        selectedMovieReleased.bind(ObjectBindings.map(selectedMovie, MovieDto::getReleases));
        selectedDateProperty().setValue(LocalDate.now());


    }

    public BooleanProperty skipLineOptionProperty() {
        return skipLineOption;
    }

    public BooleanProperty heatedArmchairOptionProperty() {
        return heatedArmchairOption;
    }

    public BooleanProperty foldingArmchairOptionProperty() {
        return foldingArmchairOption;
    }

    public StringProperty selectedSeatPriceProperty() {
        return selectedSeatPrice;
    }

    public StringProperty emailErrorProperty() {
        return emailError;
    }

    public StringProperty movieErrorProperty() {
        return movieError;
    }

    public StringProperty cinemaErrorProperty() {
        return cinemaError;
    }

    public StringProperty seatErrorProperty() {
        return seatError;
    }

    public StringProperty paymentErrorProperty() {
        return paymentError;
    }

    public ObjectProperty<LocalDate> fieldExpirationDatePickerProperty() {
        return fieldExpirationDatePicker;
    }

    public ObjectProperty<ProjectionDto> selectedProjectionProperty() {
        return selectedProjection;
    }

    public IntegerProperty selectedPositionSeatIntegerProperty() {
        return selectedPositionSeatInteger;
    }

    public ObjectProperty<TicketDto> selectedTicketProperty() {
        return selectedTicket;
    }


    public ObjectProperty<String> selectedTimeProperty() {
        return selectedTime;
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

    public StringProperty selectedTicketPositionProperty() {
        return selectedTicketPosition;
    }

    public LongProperty selectedTicketPriceProperty() {
        return selectedTicketPrice;
    }

    public ObjectProperty<MovieDto> selectedMovieProperty() {

        return selectedMovie;
    }

    public ObservableList<MovieDto> getMovieList() {
        return movieList;
    }

    public ObjectProperty<CinemaDto> selectedCinemaProperty() {
        return selectedCinema;
    }

    public ObservableList<CinemaDto> getCinemaList() {
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

    public StringProperty selectedSeatsProperty() {
        return selectedSeats;
    }

    public StringProperty emailUserProperty() {
        return emailUser;
    }

    public StringProperty nameUserProperty() {
        return nameUser;
    }

    public StringProperty surnameUserProperty() {
        return surnameUser;
    }

    public StringProperty numberOfCardUserProperty() {
        return numberOfCardUser;
    }

    public StringProperty txtCvvProperty() {
        return txtCvv;
    }

    public ObservableList<String> getTimeOfProjectionList() {
        return timeOfProjectionList;
    }

    public ObservableList<SeatDto> getSeatList() {
        return seatList;
    }


}
