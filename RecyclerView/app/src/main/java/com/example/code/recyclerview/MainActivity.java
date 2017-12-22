package com.example.code.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateMovieList();

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MoviesAdapter(this, movieList));
    }

    private void populateMovieList() {
        movieList = new ArrayList<>();
        Movie zikinaDinastija =
                new Movie("https://static.titlovi.com/img/0227/227471-tt0181182.jpg",
                        "Zikina dinastija",
                        "Komedija",
                        "1978");
        movieList.add(zikinaDinastija);

        Movie movie = new Movie(
                "http://t0.gstatic.com/images?q=tbn:ANd9GcQuK41mExh1Qv3kbXoxohWYGlcstOQ6zEnnNdSI2BGIKywQwgRI",
                "Mad Max: Fury Road",
                "Action & Adventure", "2015");
        movieList.add(movie);

        movie = new Movie(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRpmg4-_LYwgf9TKFVedSvkyG5Ic4AMiZnDZARB8W0nhdhK0wUz",
                "Inside Out", "Animation, Kids & Family",
                "2015");
        movieList.add(movie);

        movie = new Movie(
                "http://www.twogomers.com/podcasts/2017/11/https-blueprint-api-production.s3.amazonaws.com-uploads-card-image-617746-325adb0a-23f7-4808-9de2-45cb56ee8cb7.jpg",
                "Star Wars: Episode VII - The Force Awakens", "Action", "2015");
        movieList.add(movie);

        movie = new Movie( "https://kiosk-cdn.hoyts.com.au/pictures/f/Resize___Shaun_The_Sheep.jpg",
                "Shaun the Sheep", "Animation", "2015");
        movieList.add(movie);

        movie = new Movie(
                "https://theavalonpost.com/wp-content/uploads/2016/05/G3NewspaperTheMartian.jpg",
                "The Martian",
                "Science Fiction & Fantasy", "2015");
        movieList.add(movie);

        movie = new Movie( "https://images-na.ssl-images-amazon.com/images/M/MV5BMTc3NjI2MjU0Nl5BMl5BanBnXkFtZTgwNDk3ODYxMTE@._V1_UX182_CR0,0,182,268_AL_.jpg",
                "Mission: Impossible Rogue Nation", "Action", "2015");
        movieList.add(movie);

        movie = new Movie( "https://static.rogerebert.com/uploads/movie/movie_poster/up-2009/large_zh9DXJhBdHVVaWiDURTipADamcK.jpg",
                "Up", "Animation", "2009");
        movieList.add(movie);

        movie = new Movie( "https://pgtipsonfilms.files.wordpress.com/2013/06/star-trek-2-title-banner.jpg",
                "Star Trek", "Science Fiction", "2009");
        movieList.add(movie);

        movie = new Movie( "https://images-na.ssl-images-amazon.com/images/M/MV5BMTg4MDk1ODExN15BMl5BanBnXkFtZTgwNzIyNjg3MDE@._V1_UY1200_CR90,0,630,1200_AL_.jpg",
                "The LEGO Movie", "Animation", "2014");
        movieList.add(movie);

        movie = new Movie( "https://upload.wikimedia.org/wikipedia/sr/thumb/2/20/Ironmanposter.jpg/800px-Ironmanposter.jpg"
                ,"Iron Man", "Action & Adventure", "2008");
        movieList.add(movie);

        movie = new Movie("https://s-media-cache-ak0.pinimg.com/originals/97/0d/52/970d52be42b5ed8c8223b6de18f2fa60.jpg","Aliens", "Science Fiction", "1986");
        movieList.add(movie);

        movie = new Movie( "https://images-na.ssl-images-amazon.com/images/M/MV5BY2UyYjFkNzAtYzIyMC00MGI1LTlkNDktNzUyOGQ5NTI2ZGFjXkEyXkFqcGdeQXVyNTUyMzE4Mzg@._V1_.jpg"
                ,"Chicken Run", "Animation", "2000");
        movieList.add(movie);

        movie = new Movie( "https://images-na.ssl-images-amazon.com/images/I/81vaoAl5E1L._SL1500_.jpg",
                "Back to the Future", "Science Fiction", "1985");
        movieList.add(movie);

        movie = new Movie( "http://t1.gstatic.com/images?q=tbn:ANd9GcT2C2lWlKuyeSIoWpLt0EI8AxnqoeiBiMDjsqaeO4jJxK1a2brO",
                "Raiders of the Lost Ark", "Action & Adventure", "1981");
        movieList.add(movie);

        movie = new Movie( "https://images-na.ssl-images-amazon.com/images/M/MV5BMTQ2MzE0OTU3NV5BMl5BanBnXkFtZTcwNjQxNTgzNA@@._V1_UX182_CR0,0,182,268_AL_.jpg",
                "Goldfinger", "Action & Adventure", "1965");
        movieList.add(movie);

        movie = new Movie( "https://images-na.ssl-images-amazon.com/images/M/MV5BMTAwMjU5OTgxNjZeQTJeQWpwZ15BbWU4MDUxNDYxODEx._V1_UY1200_CR90,0,630,1200_AL_.jpg",
                "Guardians of the Galaxy", "Science Fiction & Fantasy", "2014");
        movieList.add(movie);
    }
}
