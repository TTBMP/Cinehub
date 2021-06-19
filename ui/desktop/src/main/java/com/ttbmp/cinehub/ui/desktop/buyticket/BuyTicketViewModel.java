package com.ttbmp.cinehub.ui.desktop.buyticket;

import com.ttbmp.cinehub.app.dto.*;
import com.ttbmp.cinehub.ui.desktop.utilities.ObjectBindings;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.ViewModel;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Value;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivan Palmieri
 */
@Value
public class BuyTicketViewModel implements ViewModel {

    StringProperty errorMessageProperty = new SimpleStringProperty();

    @Accessors(fluent = false)
    ObservableList<SeatDto> seatList = FXCollections.observableArrayList();
    @Accessors(fluent = false)
    ObservableList<MovieDto> movieList = FXCollections.observableArrayList();
    @Accessors(fluent = false)
    ObservableList<CinemaDto> cinemaList = FXCollections.observableArrayList();
    @Accessors(fluent = false)
    ObservableList<String> timeOfProjectionList = FXCollections.observableArrayList();
    @Accessors(fluent = false)
    ObservableList<ProjectionDto> projectionList = FXCollections.observableArrayList();

    ObjectProperty<TicketDto> selectedTicketProperty = new SimpleObjectProperty<>();
    ObjectProperty<MovieDto> selectedMovieProperty = new SimpleObjectProperty<>();
    ObjectProperty<CinemaDto> selectedCinemaProperty = new SimpleObjectProperty<>();
    ObjectProperty<LocalDate> selectedDateProperty = new SimpleObjectProperty<>();
    ObjectProperty<ProjectionDto> selectedProjectionProperty = new SimpleObjectProperty<>();
    ObjectProperty<SeatDto> selectedSeatProperty = new SimpleObjectProperty<>();

    StringProperty selectedSeatsNameProperty = new SimpleStringProperty();
    StringProperty selectedSeatPositionProperty = new SimpleStringProperty();
    StringProperty selectedMovieNameProperty = new SimpleStringProperty();
    StringProperty selectedCinemaNameProperty = new SimpleStringProperty();
    LongProperty selectedTicketPriceProperty = new SimpleLongProperty();
    IntegerProperty seatSelectedPositionProperty = new SimpleIntegerProperty();

    StringProperty emailUserProperty = new SimpleStringProperty();
    StringProperty numberCardProperty = new SimpleStringProperty();
    StringProperty cvvProperty = new SimpleStringProperty();
    StringProperty freeSeatsProperty = new SimpleStringProperty();
    StringProperty totalSeatsProperty = new SimpleStringProperty();
    StringProperty buysSeatsProperty = new SimpleStringProperty();
    BooleanProperty skipLineOptionProperty = new SimpleBooleanProperty();
    BooleanProperty magicBoxOptionProperty = new SimpleBooleanProperty();
    BooleanProperty openBarOptionProperty = new SimpleBooleanProperty();
    IntegerProperty counterForToggleProperty = new SimpleIntegerProperty(0);
    ObjectProperty<LocalDate> expirationDateProperty = new SimpleObjectProperty<>();
    List<BooleanProperty> seatStateProperty = new ArrayList<>(60);
    IntegerProperty indexToggleSelectedProperty = new SimpleIntegerProperty();
    BooleanProperty falseBooleanProperty = new SimpleBooleanProperty(false);
    BooleanProperty trueBooleanProperty = new SimpleBooleanProperty(true);
    BooleanProperty loginRequestedProperty = new SimpleBooleanProperty(false);

    public BuyTicketViewModel() {
        selectedMovieNameProperty.bind(ObjectBindings.map(selectedMovieProperty, MovieDto::getName));
        StringProperty selectedCinemaAddress = new SimpleStringProperty();
        selectedCinemaAddress.bind(ObjectBindings.map(selectedCinemaProperty, CinemaDto::getAddress));
        StringProperty selectedCinemaCity = new SimpleStringProperty();
        selectedCinemaCity.bind(ObjectBindings.map(selectedCinemaProperty, CinemaDto::getCity));
        selectedCinemaNameProperty.bind(ObjectBindings.map(selectedCinemaProperty, CinemaDto::getName));
        selectedTicketPriceProperty.bind(ObjectBindings.map(selectedTicketProperty, TicketDto::getPrice));
        selectedSeatPositionProperty.bind(ObjectBindings.map(selectedTicketProperty, ticket -> ticket.getSeatDto().getPosition()));
        StringProperty selectedMovieRating = new SimpleStringProperty();
        selectedMovieRating.bind(ObjectBindings.map(selectedMovieProperty, MovieDto::getVote));
        StringProperty selectedMovieOverview = new SimpleStringProperty();
        selectedMovieOverview.bind(ObjectBindings.map(selectedMovieProperty, MovieDto::getOverview));
        StringProperty selectedMovieReleased = new SimpleStringProperty();
        selectedMovieReleased.bind(ObjectBindings.map(selectedMovieProperty, MovieDto::getReleases));
        selectedDateProperty().setValue(LocalDate.now());
    }

}
