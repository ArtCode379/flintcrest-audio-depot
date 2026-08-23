package flintcresttrade.musical.flintcrestaudiodepot

//[FIREBASE|APPSFLYER][import_Intent]
//[FIREBASE][import_URI]
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
//[FIREBASE][imports_workmanager_settings]
import flintcresttrade.musical.flintcrestaudiodepot.ui.composable.approot.AppRoot
import flintcresttrade.musical.flintcrestaudiodepot.ui.theme.ProductAppWHDBNTheme
//[FIREBASE][import_VisitRequestWorker]

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProductAppWHDBNTheme {
                AppRoot()
            }
        }

        //[FIREBASE][onCreate_handleNotificationIntent]
    }

    //[FIREBASE|APPSFLYER][onNewIntent]

    //[FIREBASE][handleNotificationIntent]

    //[FIREBASE][scheduleClickTracking]

    //[FIREBASE][openExternalBrowser]
}