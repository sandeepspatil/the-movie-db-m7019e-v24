package com.ltu.m7019e.movidedb.v24

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ltu.m7019e.movidedb.v24.database.Movies
import com.ltu.m7019e.movidedb.v24.model.Movie
import com.ltu.m7019e.movidedb.v24.ui.theme.TheMovideDBV24Theme
import com.ltu.m7019e.movidedb.v24.utils.Constants

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheMovideDBV24Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TheMovieDBApp()
                }
            }
        }
    }
}

@Composable
fun TheMovieDBApp() {
    MovieList(movieList = Movies().getMovies())
}

@Composable
fun MovieList(movieList: List<Movie>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(movieList) { movie ->
            MovieListItemCard(
                movie = movie,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun MovieListItemCard(movie: Movie, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
       Row {
         Box {
             AsyncImage(
                 model = Constants.POSTER_IMAGE_BASE_URL + Constants.POSTER_IMAGE_WIDTH + movie.posterPath,
                 contentDescription = movie.title,
                 modifier = modifier
                     .width(92.dp)
                     .height(138.dp),
                 contentScale = ContentScale.Crop
             )
         }
         Column {
             Text(
                 text = movie.title,
                 style = MaterialTheme.typography.headlineSmall
             )
             Spacer(modifier = Modifier.size(8.dp))
             Text(
                 text = movie.releaseDate,
                 style = MaterialTheme.typography.bodySmall
             )
             Spacer(modifier = Modifier.size(8.dp))
             Text(
                 text = movie.overview,
                 style = MaterialTheme.typography.bodySmall,
                 maxLines = 3,
                 overflow = TextOverflow.Ellipsis,
             )
             Spacer(modifier = Modifier.size(8.dp))
         }
       }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TheMovideDBV24Theme {
        MovieListItemCard(
            movie = Movie(
                1,
                "Raya and the Last Dragon",
                "/lPsD10PP4rgUGiGR4CCXA6iY0QQ.jpg",
                "/9xeEGUZjgiKlI69jwIOi0hjKUIk.jpg",
                "2021-03-03",
                "Long ago, in the fantasy world of Kumandra, humans and dragons lived together in harmony. But when an evil force threatened the land, the dragons sacrificed themselves to save humanity. Now, 500 years later, that same evil has returned and it’s up to a lone warrior, Raya, to track down the legendary last dragon to restore the fractured land and its divided people."
            )
        )
    }
}