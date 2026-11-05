package com.app.aisheildme.bytrix.feature.retail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.aisheildme.bytrix.core.designsystem.AppColors
import com.app.aisheildme.bytrix.core.designsystem.component.PagedList
import com.app.aisheildme.bytrix.core.designsystem.component.RecordRow
import com.app.aisheildme.bytrix.core.designsystem.component.SimpleAppBar
import com.app.aisheildme.bytrix.data.remote.dto.Money
import com.app.aisheildme.bytrix.domain.model.RetailOrder

@Composable
fun RetailScreen(
    onBack: () -> Unit,
    onOpenOrder: (RetailOrder) -> Unit,
    viewModel: RetailViewModel = hiltViewModel(),
) {
    val paging by viewModel.paging.collectAsStateWithLifecycle()

    Scaffold(
        topBar = { SimpleAppBar(title = "Retail", onBack = onBack) },
        containerColor = AppColors.LightBackground,
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            Box(modifier = Modifier.weight(1f)) {
                PagedList(
                    state = paging,
                    onRetry = viewModel::loadFirstPage,
                    onLoadMore = viewModel::loadNextPage,
                    key = { it.id },
                    emptyText = "No orders yet",
                ) { order ->
                    RecordRow(
                        title = order.brandName,
                        subtitle = order.shortOrderId,
                        trailingText = Money.dollars(order.total),
                        imageUrl = order.logoUrl.takeIf { it.isNotEmpty() },
                        onClick = { onOpenOrder(order) },
                    )
                }
            }
        }
    }
}
