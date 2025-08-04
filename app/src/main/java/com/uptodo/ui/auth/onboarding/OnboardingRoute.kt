package com.uptodo.ui.auth.onboarding

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.uptodo.R
import com.uptodo.model.OnboardingPage
import com.uptodo.ui.theme.AppColor
import com.uptodo.ui.theme.AppTypography
import com.uptodo.util.Constants
import kotlinx.coroutines.launch

@Composable
fun OnboardingRoute(modifier: Modifier = Modifier) {
    OnboardingScreen(modifier)
}

@Composable
fun OnboardingScreen(modifier: Modifier = Modifier) {
    val pages = getOnboardingPages()

    val pagerState = rememberPagerState(pageCount = { pages.size })
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier,
        containerColor = Color.Black,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = dimensionResource(R.dimen.dp_16),
                        vertical = dimensionResource(R.dimen.dp_8)
                    ),
                horizontalArrangement = Arrangement.Start
            ) {
                TextButton(onClick = {}) {
                    Text(
                        text = stringResource(R.string.txt_skip).uppercase(),
                        color = AppColor.Grey,
                        style = AppTypography.bodyNormal
                    )
                }
            }
        },
        bottomBar = {
            // Bottom navigation - Fixed at bottom
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.dp_16)),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Back button
                TextButton(
                    onClick = {
                        scope.launch {
                            if (pagerState.currentPage > Constants.FIRST_PAGE) {
                                pagerState.animateScrollToPage(
                                    page = pagerState.currentPage - Constants.PAGE_DECREMENT,
                                    animationSpec = tween(
                                        durationMillis = Constants.ANIMATION_DURATION,
                                        easing = androidx.compose.animation.core.FastOutSlowInEasing
                                    )
                                )
                            }
                        }
                    },
                    enabled = pagerState.currentPage > Constants.FIRST_PAGE
                ) {
                    Text(
                        text = stringResource(R.string.txt_back).uppercase(),
                        color = if (pagerState.currentPage > Constants.FIRST_PAGE) AppColor.Grey else Color.Transparent,
                        style = AppTypography.bodyNormal
                    )
                }

                // Next button
                Button(
                    onClick = {
                        scope.launch {
                            if (pagerState.currentPage < pages.size - Constants.PAGE_INCREMENT) {
                                pagerState.animateScrollToPage(
                                    page = pagerState.currentPage + Constants.PAGE_INCREMENT,
                                    animationSpec = tween(
                                        durationMillis = Constants.ANIMATION_DURATION,
                                        easing = androidx.compose.animation.core.FastOutSlowInEasing
                                    )
                                )
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AppColor.Purple
                    ),
                    shape = RoundedCornerShape(dimensionResource(R.dimen.dp_4)),
                ) {
                    Text(
                        text = if (pagerState.currentPage == pages.size - 1) stringResource(R.string.txt_get_started).uppercase() else stringResource(
                            R.string.btn_next
                        ).uppercase(),
                        color = Color.White,
                        style = AppTypography.bodyNormal
                    )
                }
            }
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Single HorizontalPager containing both image and text
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(1f)
            ) { page ->
                // Simple animation states - much easier to understand!
                val isCurrentPage = page == pagerState.currentPage

                // Animate scale when page becomes active/inactive
                val scale by animateFloatAsState(
                    targetValue = if (isCurrentPage) Constants.SCALE_NORMAL else Constants.SCALE_SMALL,
                    animationSpec = tween(durationMillis = Constants.ANIMATION_DURATION),
                    label = "page_scale"
                )

                // Animate alpha for fade effect
                val alpha by animateFloatAsState(
                    targetValue = if (isCurrentPage) Constants.ALPHA_VISIBLE else Constants.ALPHA_SEMI_TRANSPARENT,
                    animationSpec = tween(durationMillis = Constants.ANIMATION_DURATION),
                    label = "page_alpha"
                )

                OnboardingPage(
                    scale = scale,
                    alpha = alpha,
                    page = page,
                    pages = pages
                )
            }

            // Page Indicator below the pager
            Row(
                modifier = Modifier
                    .padding(bottom = dimensionResource(R.dimen.dp_24)),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.dp_8))
            ) {
                repeat(pages.size) { index ->
                    Box(
                        modifier = Modifier
                            .size(
                                width = if (index == pagerState.currentPage) dimensionResource(R.dimen.dp_24) else dimensionResource(
                                    R.dimen.dp_8
                                ),
                                height = dimensionResource(R.dimen.dp_8)
                            )
                            .background(
                                color = if (index == pagerState.currentPage) AppColor.Purple else AppColor.Grey,
                                shape = RoundedCornerShape(dimensionResource(R.dimen.dp_4))
                            )
                    )
                }
            }
        }
    }
}

@Composable
private fun getOnboardingPages(): List<OnboardingPage> {
    return listOf(
        OnboardingPage(
            image = R.drawable.ic_manage_task,
            title = stringResource(R.string.onboarding_title_1),
            description = stringResource(R.string.onboarding_description_1)
        ),
        OnboardingPage(
            image = R.drawable.ic_daily_routine,
            title = stringResource(R.string.onboarding_title_2),
            description = stringResource(R.string.onboarding_description_2)
        ),
        OnboardingPage(
            image = R.drawable.ic_org_task,
            title = stringResource(R.string.onboarding_title_3),
            description = stringResource(R.string.onboarding_description_3)
        )
    )
}

@Composable
fun OnboardingPage(scale: Float, alpha: Float, page: Int, pages: List<OnboardingPage>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = dimensionResource(R.dimen.dp_16))
            .scale(scale)
            .alpha(alpha),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Image
        Image(
            painter = painterResource(id = pages[page].image),
            contentDescription = null,
            modifier = Modifier
                .size(dimensionResource(R.dimen.onboarding_image))
                .padding(bottom = dimensionResource(R.dimen.dp_32))
        )

        // Title
        Text(
            text = pages[page].title,
            color = Color.White,
            style = AppTypography.bodyBold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.dp_16))
        )

        // Description
        Text(
            text = pages[page].description,
            color = AppColor.Grey,
            style = AppTypography.bodyNormal,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.dp_24))
        )
    }
}

@Preview
@Composable
fun OnboardingScreenPreview() {
    OnboardingScreen()
}