package com.tiffzy.app.ui.customer.profile

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tiffzy.app.data.model.CustomerWallet
import com.tiffzy.app.data.model.TopupStep
import com.tiffzy.app.data.model.WalletTransaction
import com.tiffzy.app.ui.components.TiffzyLoadingIndicator
import com.tiffzy.app.ui.theme.Dimens
import com.tiffzy.app.ui.theme.TiffzyOrange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WalletScreen(
    onBack: () -> Unit,
    walletViewModel: WalletViewModel = viewModel()
) {
    val walletState by walletViewModel.walletState.collectAsState()
    val topupStep by walletViewModel.topupStep.collectAsState()
    val isAddMoneyVisible by walletViewModel.isAddMoneyVisible.collectAsState()
    val statusBanner by walletViewModel.statusBanner.collectAsState()
    val isBannerSuccess by walletViewModel.isBannerSuccess.collectAsState()

    LaunchedEffect(Unit) {
        walletViewModel.loadWallet()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Tiffzy Wallet",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { walletViewModel.loadWallet() }) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Refresh",
                            tint = TiffzyOrange
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        },
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = Dimens.PaddingLarge)
        ) {
            // Status banner for feedback
            statusBanner?.let { message ->
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = if (isBannerSuccess) Color(0xFF10B981).copy(alpha = 0.12f) else MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.3f),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(
                                imageVector = if (isBannerSuccess) Icons.Default.CheckCircle else Icons.Default.ErrorOutline,
                                contentDescription = null,
                                tint = if (isBannerSuccess) Color(0xFF10B981) else MaterialTheme.colorScheme.error,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = message,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.SemiBold,
                                color = if (isBannerSuccess) Color(0xFF047857) else MaterialTheme.colorScheme.error
                            )
                        }
                        IconButton(
                            onClick = { walletViewModel.clearBanner() },
                            modifier = Modifier.size(20.dp)
                        ) {
                            Icon(Icons.Default.Close, contentDescription = "Dismiss", modifier = Modifier.size(14.dp))
                        }
                    }
                }
            }

            when (val state = walletState) {
                is WalletUiState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        TiffzyLoadingIndicator()
                    }
                }
                is WalletUiState.Error -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = state.message,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Button(
                            onClick = { walletViewModel.loadWallet() },
                            colors = ButtonDefaults.buttonColors(containerColor = TiffzyOrange)
                        ) {
                            Text("Retry")
                        }
                    }
                }
                is WalletUiState.Success -> {
                    WalletMainContent(
                        wallet = state.wallet,
                        transactions = state.transactions,
                        onAddMoneyClick = { walletViewModel.openAddMoney() }
                    )
                }
                WalletUiState.Idle -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        TiffzyLoadingIndicator()
                    }
                }
            }
        }
    }

    if (isAddMoneyVisible) {
        AddMoneyDialog(
            viewModel = walletViewModel,
            onDismiss = { walletViewModel.dismissAddMoney() },
            onSuccessfulTopup = {
                walletViewModel.loadWallet()
            }
        )
    }
}

@Composable
private fun WalletMainContent(
    wallet: CustomerWallet,
    transactions: List<WalletTransaction>,
    onAddMoneyClick: () -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 12.dp)
    ) {
        // Compact Balance Section
        item {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = Color(0xFFFAFAFA),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFEEEEEE))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "CURRENT BALANCE",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = if (wallet.status == "ACTIVE") Color(0xFF10B981).copy(alpha = 0.12f) else MaterialTheme.colorScheme.errorContainer
                        ) {
                            Text(
                                text = wallet.status ?: "ACTIVE",
                                style = MaterialTheme.typography.labelSmall,
                                color = if (wallet.status == "ACTIVE") Color(0xFF10B981) else MaterialTheme.colorScheme.error,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "₹${"%.2f".format(wallet.balance)}",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Black,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = onAddMoneyClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(44.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = TiffzyOrange
                        ),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "ADD MONEY",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            letterSpacing = 0.5.sp
                        )
                    }
                }
            }
        }

        // Transactions Header
        item {
            Text(
                text = "RECENT TRANSACTIONS",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp,
                modifier = Modifier.padding(start = 2.dp, top = 4.dp)
            )
        }

        // Transactions List
        if (transactions.isEmpty()) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Default.AccountBalanceWallet,
                            contentDescription = null,
                            tint = Color.LightGray,
                            modifier = Modifier.size(44.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "No wallet transactions yet",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                    }
                }
            }
        } else {
            items(transactions, key = { it.id }) { txn ->
                WalletTransactionRow(txn = txn)
            }
        }
    }
}

@Composable
fun WalletTransactionRow(txn: WalletTransaction) {
    val isCredit = txn.type.equals("CREDIT", ignoreCase = true)

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        color = Color(0xFFFAFAFA),
        border = androidx.compose.foundation.BorderStroke(0.5.dp, Color(0xFFEEEEEE))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(if (isCredit) Color(0xFF10B981).copy(alpha = 0.12f) else Color(0xFFEF4444).copy(alpha = 0.12f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (isCredit) Icons.Default.ArrowDownward else Icons.Default.ArrowUpward,
                        contentDescription = null,
                        tint = if (isCredit) Color(0xFF10B981) else Color(0xFFEF4444),
                        modifier = Modifier.size(18.dp)
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Column {
                    Text(
                        text = txn.description ?: if (isCredit) "Wallet Top-up" else "Order Payment",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black,
                        maxLines = 1
                    )
                    Text(
                        text = txn.createdAt?.take(16)?.replace("T", " ") ?: "Recent",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Gray,
                        fontSize = 10.sp
                    )
                }
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "${if (isCredit) "+" else "-"}₹${"%.2f".format(txn.amount)}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (isCredit) Color(0xFF10B981) else Color(0xFFEF4444)
                )
                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = when (txn.status?.uppercase()) {
                        "SUCCESS" -> Color(0xFF10B981).copy(alpha = 0.12f)
                        "FAILED" -> Color(0xFFEF4444).copy(alpha = 0.12f)
                        else -> Color(0xFFF59E0B).copy(alpha = 0.12f)
                    }
                ) {
                    Text(
                        text = txn.status ?: "SUCCESS",
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        color = when (txn.status?.uppercase()) {
                            "SUCCESS" -> Color(0xFF10B981)
                            "FAILED" -> Color(0xFFEF4444)
                            else -> Color(0xFFF59E0B)
                        },
                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                    )
                }
            }
        }
    }
}
