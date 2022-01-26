package br.com.android.ppm.apidouglasmota

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.android.ppm.apidouglasmota.data.Models
import br.com.android.ppm.apidouglasmota.data.seriesService
import br.com.android.ppm.apidouglasmota.ui.theme.ApiDouglasMotaTheme

class MainActivity : ComponentActivity() {

        private  val  viewModel by viewModels<MainViewModel> {
            object  : ViewModelProvider.Factory{
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return MainViewModel(seriesService) as T
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApiDouglasMotaTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                   // Greeting("Android")
                    Episode(episodeData = viewModel.episodesData)
                }
            }
        }
        viewModel.getSeries()
    }
}

@Composable
fun Episode(episodeData: LiveData<List<Models.Series>>){
    val episodes by episodeData.observeAsState()
    Text(text = episodes.toString())

}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ApiDouglasMotaTheme {
        Greeting("Android")
    }
}