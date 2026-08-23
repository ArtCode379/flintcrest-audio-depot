package flintcresttrade.musical.flintcrestaudiodepot.di

import flintcresttrade.musical.flintcrestaudiodepot.ui.viewmodel.AppViewModel
import flintcresttrade.musical.flintcrestaudiodepot.ui.viewmodel.CartViewModel
import flintcresttrade.musical.flintcrestaudiodepot.ui.viewmodel.CheckoutViewModel
import flintcresttrade.musical.flintcrestaudiodepot.ui.viewmodel.WHDBNOnboardingVM
import flintcresttrade.musical.flintcrestaudiodepot.ui.viewmodel.OrderViewModel
import flintcresttrade.musical.flintcrestaudiodepot.ui.viewmodel.ProductDetailsViewModel
import flintcresttrade.musical.flintcrestaudiodepot.ui.viewmodel.ProductViewModel
import flintcresttrade.musical.flintcrestaudiodepot.ui.viewmodel.WHDBNSplashVM
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        AppViewModel(
            cartRepository = get()
        )
    }

    viewModel {
        WHDBNSplashVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        WHDBNOnboardingVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        ProductViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        ProductDetailsViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        CheckoutViewModel(
            cartRepository = get(),
            productRepository = get(),
            orderRepository = get(),
        )
    }

    viewModel {
        CartViewModel(
            cartRepository = get(),
            productRepository = get(),
        )
    }

    viewModel {
        OrderViewModel(
            orderRepository = get(),
        )
    }
}