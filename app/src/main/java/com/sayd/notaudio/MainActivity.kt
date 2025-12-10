package com.sayd.notaudio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sayd.notaudio.ui.login.LoginScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.sayd.notaudio.Navigation.NavGraph
import com.sayd.notaudio.ui.theme.NotaudioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotaudioTheme {
                ConfiguracionScreen()
            }
        }
    }
}
                LoginScreen()
            }
        }
    }
}
                NavGraph()
            }
        }
    }
}


@Preview
@Composable
fun DefaultPreview() {
    NotaudioTheme {
        NavGraph()
    }
}
