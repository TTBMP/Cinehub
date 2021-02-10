package com.ttbmp.cinehub.ui.desktop.buyticket.choosemovie;


import com.ttbmp.cinehub.app.dto.MovieDto;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.Activity;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.ViewController;
import com.ttbmp.cinehub.ui.desktop.utilities.ui.navigation.NavController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * @author Palmieri Ivan
 */
public class ChooseMovieItemViewController extends ViewController {


    private MovieDto movieDto;
    @FXML
    private Label overviewMovieLabel;

    @FXML
    private Label voteMovieLabel;

    @FXML
    private Label releasedMovieLabel;

    @FXML
    private ImageView imageMovieImageView;

    @FXML
    private Label nameMovieLabel;

    @Override
    protected void onLoad() {
        Objects.requireNonNull(movieDto);
        nameMovieLabel.setText(movieDto.getName());
        imageMovieImageView.setImage(new Image(movieDto.getMovieUrl()));
        overviewMovieLabel.setText(movieDto.getOverview());
        voteMovieLabel.setText(movieDto.getVote());
        releasedMovieLabel.setText(movieDto.getReleases());
    }

    public void load(Activity activity, NavController navController, MovieDto movieDto) {
        this.movieDto = movieDto;
        load(activity, navController);
    }

}
