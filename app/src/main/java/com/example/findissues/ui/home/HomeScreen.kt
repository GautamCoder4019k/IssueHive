package com.example.findissues.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.core.ui.components.AppTopBar
import com.example.core.ui.components.IconWithText
import com.example.findissues.R
import com.example.findissues.models.home.User
import com.example.findissues.ui.theme.FindIssueTheme
import com.example.findissues.utils.Constants

@Composable
fun HomeScreen(
    user: User,
    onFollowersClick: () -> Unit = {},
    onFollowingClick: () -> Unit = {},
    onRepositoriesClick: () -> Unit = {},
    onStarredClick: () -> Unit = {},
    goToTwitter: (String) -> Unit = {}
) {
    val scrollState = rememberScrollState()
    Scaffold(topBar = { AppTopBar(title = R.string.home) }, modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .verticalScroll(scrollState)
                .background(color = MaterialTheme.colorScheme.primary)
        ) {
            UserDetails(
                user = user,
                onFollowersClick = onFollowersClick,
                onFollowingClick = onFollowingClick,
                goToTwitter = goToTwitter,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(
                modifier = Modifier
                    .height(18.dp)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.secondary)
            )
            UserLinks(
                onRepositoriesClick = onRepositoriesClick,
                onStarredClick = onStarredClick,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}

@Composable
fun UserLinks(
    modifier: Modifier = Modifier,
    onRepositoriesClick: () -> Unit,
    onStarredClick: () -> Unit
) {
    Column(modifier = modifier) {
        IconWithText(
            text = stringResource(R.string.pinned),
            iconId = R.drawable.pinned,
            modifier = Modifier.padding(8.dp)
        )
        IconWithText(
            text = stringResource(R.string.repositories),
            iconId = R.drawable.repo,
            modifier = Modifier
                .padding(8.dp)
                .clickable { onRepositoriesClick() },
            textColor = Color.White
        )
        IconWithText(
            text = stringResource(R.string.starred),
            iconId = R.drawable.star,
            modifier = Modifier
                .padding(8.dp)
                .clickable { onStarredClick() },
            textColor = Color.White
        )
    }
}

@Composable
fun UserDetails(
    user: User,
    onFollowersClick: () -> Unit,
    onFollowingClick: () -> Unit,
    modifier: Modifier = Modifier,
    goToTwitter: (String) -> Unit
) {
    Column(modifier = modifier) {
        Row {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(user.avatar_url).build(),
                contentDescription = "avatar",
                modifier = Modifier
                    .size(128.dp)
                    .padding(16.dp)
                    .clip(
                        RoundedCornerShape(100.dp)
                    )
            )

            Column(modifier = Modifier.padding(top = 16.dp, end = 16.dp)) {
                Text(
                    text = user.name,
                    color = MaterialTheme.colorScheme.tertiary,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = user.login,
                    color = MaterialTheme.colorScheme.tertiary,
                    fontSize = 19.sp
                )
            }
        }

        Text(
            text = user.bio,
            color = MaterialTheme.colorScheme.tertiary,
            fontSize = 20.sp,
            modifier = Modifier.padding(8.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            IconWithText(text = user.company, iconId = R.drawable.company)
            Spacer(modifier = Modifier.padding(12.dp))
            IconWithText(text = user.location, iconId = R.drawable.location)

        }
        IconWithText(
            text = user.twitter_username,
            iconId = R.drawable.twitter,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 8.dp)
                .clickable { goToTwitter(user.twitter_username) }
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            IconWithText(
                text = stringResource(
                    R.string.number_with_string,
                    user.followers,
                    Constants.FOLLOWERS
                ),
                iconId = R.drawable.followers,
                modifier = Modifier.clickable { onFollowersClick() }
            )
            Spacer(modifier = Modifier.padding(12.dp))
            IconWithText(
                text = stringResource(
                    R.string.number_with_string,
                    user.following,
                    Constants.FOLLOWING
                ),
                iconId = R.drawable.following,
                modifier = Modifier.clickable { onFollowingClick() }
            )

        }
    }

}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    FindIssueTheme {
        HomeScreen(User())
    }
}