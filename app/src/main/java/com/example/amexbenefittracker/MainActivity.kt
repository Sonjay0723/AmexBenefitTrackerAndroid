package com.example.amexbenefittracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.amexbenefittracker.ui.dashboard.DashboardScreen
import com.example.amexbenefittracker.ui.dashboard.DashboardViewModel
import com.example.amexbenefittracker.ui.theme.AmexBenefitTrackerTheme

class MainActivity : ComponentActivity() {
    
    private val viewModel: DashboardViewModel by viewModels {
        DashboardViewModel.Factory((application as AmexApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AmexBenefitTrackerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = com.example.amexbenefittracker.ui.theme.AmexDarkBlue
                ) {
                    DashboardScreen(viewModel)
                }
            }
        }
    }
}
