package com.goofy.nutsaver.screens

import android.annotation.SuppressLint
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.goofy.nutsaver.R
import com.goofy.nutsaver.features.getCurrentMonthDays
import com.goofy.nutsaver.ui.theme.OtherSquirrelBrown
import com.goofy.nutsaver.ui.theme.PrettyGreen
import com.goofy.nutsaver.ui.theme.PrettyRed
import com.goofy.nutsaver.ui.theme.SquirrelBrown
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.YearMonth
import java.time.ZoneId
import java.time.ZonedDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashBoard() {
    var openDialog by remember { mutableStateOf(false) }
    var monthlyBudget by remember { mutableStateOf("") }
    val bouncy = remember { Animatable(0f) }

    val daysInMonth = remember { getCurrentMonthDays() }


    Scaffold(
        topBar = { TopAppBar(title = { Text("Nut Saver") }) },
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                LaunchedEffect(Unit) {
                    // Bouncy animation for overall content (optional, can keep or remove)
                    bouncy.animateTo(
                        targetValue = 1f,
                        animationSpec = tween(
                            durationMillis = 1000,
                            easing = { OvershootInterpolator(1f).getInterpolation(it) }
                        )
                    )
                }


                Image(
                    painter = painterResource(id = R.drawable.detail_header),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .scale(bouncy.value),
                    alignment = Alignment.TopCenter
                )

                Row(
                    modifier = Modifier
                        .graphicsLayer { translationY = 40.dp.toPx() }
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Nut Saver",
                        color = SquirrelBrown,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.acorn_icon),
                        contentDescription = "Nut Saver",
                        modifier = Modifier.size(25.dp),
                        tint = SquirrelBrown
                    )
                }

                BudgetCard(
                    monthlyBudget = monthlyBudget,
                    openDialog = { openDialog = true },
                    modifier = Modifier.graphicsLayer { translationY = 70.dp.toPx() }
                )
            }
            OneAtATimeLazyRow(days = daysInMonth)
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun OneAtATimeLazyRow(days: List<String>) {
    val listState: LazyListState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val today = LocalDate.now().dayOfMonth
    scope.launch {
        listState.scrollToItem(today-1)
    }
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        state = listState,
        flingBehavior = rememberSnapFlingBehavior(lazyListState = listState),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items (days) { day ->
            DayItem(day, Modifier.fillParentMaxWidth())
        }
    }
}

@Composable
fun DayItem(day: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .width(150.dp)
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start) {
                Text(
                    text = day,
                    fontWeight = Bold
                )
                Text(
                    text = "Spent Today: $",
                    color = Color.Blue
                )
            }

            HorizontalDivider(modifier = Modifier.padding(top = 4.dp, bottom = 4.dp))

            val context = LocalContext.current
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        Toast.makeText(
                            context,
                            "Add button Clicked",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Log-In Arrow",
                    modifier = Modifier
                        .background(Color.Gray, RoundedCornerShape(30))
                        .size(35.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun BudgetCard(monthlyBudget: String, openDialog: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .border(2.dp, SquirrelBrown, RoundedCornerShape(10.dp))
            .background(OtherSquirrelBrown, RoundedCornerShape(10.dp))
            .clickable { openDialog() }
            .padding(16.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Budget",
                color = SquirrelBrown,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            BudgetRow(
                label = "Total Monthly Budget:",
                amount = "$$monthlyBudget",
                amountColor = PrettyGreen
            )
            BudgetRow(label = "Remaining Monthly Budget:", amount = "$-", amountColor = PrettyRed)
        }
    }
}

@Composable
fun BudgetRow(label: String, amount: String, amountColor: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, color = Color.Black, fontSize = 14.sp)
        Text(text = amount, color = amountColor, fontWeight = FontWeight.Bold, fontSize = 20.sp)
    }
}