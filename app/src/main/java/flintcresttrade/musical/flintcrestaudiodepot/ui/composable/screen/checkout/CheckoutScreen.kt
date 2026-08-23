package flintcresttrade.musical.flintcrestaudiodepot.ui.composable.screen.checkout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import flintcresttrade.musical.flintcrestaudiodepot.R
import flintcresttrade.musical.flintcrestaudiodepot.ui.state.DataUiState
import flintcresttrade.musical.flintcrestaudiodepot.ui.viewmodel.CheckoutViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CheckoutScreen(
    modifier: Modifier = Modifier,
    viewModel: CheckoutViewModel = koinViewModel(),
    onNavigateToOrdersScreen: () -> Unit,
) {
    val orderState by viewModel.orderState.collectAsStateWithLifecycle()
    val emailInvalid by viewModel.emailInvalidState.collectAsStateWithLifecycle()
    val enabled = viewModel.customerFirstName.isNotBlank() && viewModel.customerLastName.isNotBlank() && viewModel.customerEmail.isNotBlank()

    if (orderState is DataUiState.Populated) {
        CheckoutDialog(onConfirm = onNavigateToOrdersScreen)
    }
    CheckoutContent(
        firstName = viewModel.customerFirstName,
        lastName = viewModel.customerLastName,
        email = viewModel.customerEmail,
        isEmailInvalid = emailInvalid,
        enabled = enabled,
        modifier = modifier,
        focusManager = LocalFocusManager.current,
        onFirstNameChanged = viewModel::updateCustomerFirstName,
        onLastNameChanged = viewModel::updateCustomerLastName,
        onEmailChanged = viewModel::updateCustomerEmail,
        onPlaceOrder = viewModel::placeOrder,
    )
}

@Composable
private fun CheckoutContent(
    firstName: String,
    lastName: String,
    email: String,
    isEmailInvalid: Boolean,
    enabled: Boolean,
    modifier: Modifier,
    focusManager: FocusManager,
    onFirstNameChanged: (String) -> Unit,
    onLastNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPlaceOrder: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        Text(stringResource(R.string.whdbn_reserve_title), style = MaterialTheme.typography.headlineMedium)
        Text(stringResource(R.string.whdbn_reserve_description), color = MaterialTheme.colorScheme.onSurfaceVariant)
        CheckoutTextField(firstName, onFirstNameChanged, stringResource(R.string.whdbn_checkout_text_field_first_name), Modifier.fillMaxWidth())
        CheckoutTextField(lastName, onLastNameChanged, stringResource(R.string.whdbn_checkout_text_field_last_name), Modifier.fillMaxWidth())
        CheckoutTextField(
            input = email,
            onInputChange = onEmailChanged,
            labelText = stringResource(R.string.whdbn_checkout_text_field_email),
            modifier = Modifier.fillMaxWidth(),
            isError = isEmailInvalid,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        )
        if (isEmailInvalid) Text(stringResource(R.string.whdbn_email_error), color = MaterialTheme.colorScheme.error)
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp)) {
                Text(stringResource(R.string.whdbn_pickup_summary), style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(8.dp))
                Text(stringResource(R.string.whdbn_pickup_details), style = MaterialTheme.typography.bodyMedium)
            }
        }
        Button(onClick = onPlaceOrder, enabled = enabled, modifier = Modifier.fillMaxWidth()) {
            Text(stringResource(R.string.whdbn_button_confirm_order_label))
        }
    }
}

@Composable
fun CheckoutTextField(
    input: String,
    onInputChange: (String) -> Unit,
    labelText: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    OutlinedTextField(
        value = input,
        onValueChange = onInputChange,
        label = { Text(labelText) },
        modifier = modifier,
        enabled = enabled,
        isError = isError,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = true,
    )
}
