package com.example.findissues.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.findissues.R
import com.example.findissues.models.home.User
import com.example.findissues.ui.adapters.PinnedRepoAdapter
import com.example.findissues.ui.followers.FollowersFragment
import com.example.findissues.ui.following.FollowingFragment
import com.example.findissues.ui.home.HomeScreen
import com.example.findissues.ui.theme.FindIssueTheme
import com.example.findissues.utils.Browser
import com.example.findissues.utils.Constants.TWITTER_BASE_URL
import com.example.findissues.viewmodels.PinnedRepoViewModel
import com.example.findissues.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var user by mutableStateOf(User())

    private lateinit var userViewModel: UserViewModel
    private lateinit var pinnedRepoViewModel: PinnedRepoViewModel

    @Inject
    lateinit var pinnedRepoAdapter: PinnedRepoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        pinnedRepoViewModel = ViewModelProvider(this)[PinnedRepoViewModel::class.java]

        userViewModel.getUserDetail()
        userViewModel.observeUserLiveData().observe(viewLifecycleOwner) {
            user = it
        }

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                pinnedRepoViewModel.getPinnedRepos()
            }
        }

        pinnedRepoViewModel.observePinnedRepoLiveData().observe(viewLifecycleOwner) {
            pinnedRepoAdapter.setUpPinnedRepoList(it)
        }

        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                FindIssueTheme {
                    HomeScreen(
                        user = user,
                        onFollowersClick = { setUpFragment(FollowersFragment()) },
                        onFollowingClick = { setUpFragment(FollowingFragment()) },
                        onRepositoriesClick = { setUpFragment(RepositoryFragment()) },
                        onStarredClick = { setUpFragment(StarredFragment()) },
                        goToTwitter = { Browser(requireContext()).launch(goToTwitter(it)) }
                    )
                }
            }
        }
    }

    private fun goToTwitter(userName: String): String {
        return TWITTER_BASE_URL + userName
    }

    private fun setUpFragment(fragment: Fragment) {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.nav_host_fragment_activity_dashboard, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

}