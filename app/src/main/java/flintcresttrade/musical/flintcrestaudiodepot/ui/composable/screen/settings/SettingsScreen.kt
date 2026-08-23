package flintcresttrade.musical.flintcrestaudiodepot.ui.composable.screen.settings

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.HeadsetMic
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import flintcresttrade.musical.flintcrestaudiodepot.R

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val supportUrl = stringResource(R.string.whdbn_customer_support_link)
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        Text(stringResource(R.string.whdbn_about), style = MaterialTheme.typography.titleLarge)
        SettingsRow(Icons.Default.Business, stringResource(R.string.whdbn_settings_screen_company_label), stringResource(R.string.whdbn_company_name))
        SettingsRow(Icons.Default.Info, stringResource(R.string.whdbn_settings_screen_version_label), stringResource(R.string.whdbn_app_version))
        Card(shape = RoundedCornerShape(14.dp), modifier = Modifier.fillMaxWidth()) {
            Column(Modifier.padding(18.dp)) {
                Text(stringResource(R.string.whdbn_support_copy), color = MaterialTheme.colorScheme.onSurfaceVariant)
                Button(
                    onClick = { context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(supportUrl))) },
                    modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
                ) {
                    Icon(Icons.Default.HeadsetMic, null)
                    Text(stringResource(R.string.whdbn_settings_screen_customer_support_label), Modifier.padding(start = 8.dp))
                }
            }
        }
    }
}

@Composable
private fun SettingsRow(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, value: String) {
    Card(shape = RoundedCornerShape(14.dp), modifier = Modifier.fillMaxWidth()) {
        Row(Modifier.padding(18.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, null, tint = MaterialTheme.colorScheme.primary)
            Column(Modifier.padding(start = 14.dp)) {
                Text(label, style = MaterialTheme.typography.labelLarge)
                Text(value, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}
