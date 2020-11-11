package ru.skillbranch.skillarticles.ui

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_root.*
import kotlinx.android.synthetic.main.layout_bottombar.*
import kotlinx.android.synthetic.main.layout_submenu.*
import kotlinx.android.synthetic.main.search_view_layout.*
import ru.skillbranch.skillarticles.R
import ru.skillbranch.skillarticles.data.repositories.MarkdownElement
import ru.skillbranch.skillarticles.extensions.dpToIntPx
import ru.skillbranch.skillarticles.extensions.hideKeyboard
import ru.skillbranch.skillarticles.extensions.setMarginOptionally
import ru.skillbranch.skillarticles.ui.base.BaseActivity
import ru.skillbranch.skillarticles.ui.base.Binding
import ru.skillbranch.skillarticles.ui.delegates.ObserveProp
import ru.skillbranch.skillarticles.ui.delegates.RenderProp
import ru.skillbranch.skillarticles.viewmodels.RootViewModel
import ru.skillbranch.skillarticles.viewmodels.articles.ArticleState
import ru.skillbranch.skillarticles.viewmodels.articles.ArticleViewModel
import ru.skillbranch.skillarticles.viewmodels.base.IViewModelState
import ru.skillbranch.skillarticles.viewmodels.base.NavigationCommand
import ru.skillbranch.skillarticles.viewmodels.base.Notify
import ru.skillbranch.skillarticles.viewmodels.base.ViewModelFactory

class RootActivity : BaseActivity<RootViewModel>() {
    override val layout: Int = R.layout.activity_root
    override val viewModel: RootViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_articles,
                R.id.nav_bookmarks,
                R.id.nav_transcriptions,
                R.id.nav_profile
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        nav_view.setOnNavigationItemSelectedListener {
            viewModel.navigate(NavigationCommand.To(it.itemId))
            true
        }

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            nav_view.selectDestination(destination)
        }
    }

    override fun renderNotification(notify: Notify) {
        val snackbar = Snackbar.make(container, notify.message, Snackbar.LENGTH_LONG)

        if (bottombar != null) snackbar.anchorView = bottombar
        else snackbar.anchorView = nav_view

        when (notify) {
            is Notify.ActionMessage -> {
                val (_, label, handler) = notify
                with(snackbar) {
                    setActionTextColor(getColor(R.color.color_accent_dark))
                    setAction(label) { handler.invoke() }
                }
            }

            is Notify.ErrorMessage -> {
                val (_, label, handler) = notify
                with(snackbar) {
                    setBackgroundTint(getColor(R.color.design_default_color_error))
                    setTextColor(getColor(android.R.color.white))
                    setActionTextColor(getColor(android.R.color.white))
                    handler ?: return@with
                    setAction(label) { handler.invoke() }
                }
            }
        }
        snackbar.show()
    }

    override fun subscribeOnState(state: IViewModelState) {

    }

}

//
//@SuppressLint("UseCompatLoadingForDrawables")
//class ArticleBinding() : Binding() {
//    var isFocusedSearch: Boolean = false
//    var searchQuery: String? = null
//
//    private var isLoadingContent by ObserveProp(true)
//
//    // если эти проперти будут изменены, они автоматически будут перерисованы на нашем UI
//    private var isLike: Boolean by RenderProp(false) { btn_like.isChecked = it }
//    private var isBookmark: Boolean by RenderProp(false) { btn_bookmark.isChecked = it }
//    private var isShowMenu: Boolean by RenderProp(false) {
//        btn_settings.isChecked = it
//        if (it) submenu.open() else submenu.close()
//    }
//    private var title: String by RenderProp("loading") { toolbar.title = it }
//    private var category: String by RenderProp("loading") { toolbar.subtitle = it }
//    private var categoryIcon: Int by RenderProp(R.drawable.logo_placeholder) {
//        toolbar.logo = getDrawable(it)
//    }
//    private var isBigText: Boolean by RenderProp(false) {
//        if (it) {
//            tv_text_content.textSize = 18f
//            btn_text_up.isChecked = true
//            btn_text_down.isChecked = false
//        } else {
//            tv_text_content.textSize = 14f
//            btn_text_up.isChecked = false
//            btn_text_down.isChecked = true
//        }
//    }
//    private var isDarkMode: Boolean by RenderProp(false, false) {
//        switch_mode.isChecked = it
//        delegate.localNightMode =
//            if (it) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
//    }
//
//    var isSearch: Boolean by ObserveProp(false) {
//        if (it) {
//            showSearchBar()
//            with(toolbar) {
//                (layoutParams as AppBarLayout.LayoutParams).scrollFlags =
//                    AppBarLayout.LayoutParams.SCROLL_FLAG_NO_SCROLL
//            }
//        } else {
//            hideSearchBar()
//            with(toolbar) {
//                (layoutParams as AppBarLayout.LayoutParams).scrollFlags =
//                    AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or
//                            AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED
//            }
//        }
//    }
//
//    private var searchResults: List<Pair<Int, Int>> by ObserveProp(emptyList<Pair<Int, Int>>())
//    private var searchPosition: Int by ObserveProp(0)
//
//    private var content: List<MarkdownElement> by ObserveProp(emptyList<MarkdownElement>()) {
//        tv_text_content.isLoading = it.isEmpty()
//        tv_text_content.setContent(it)
//        if (it.isNotEmpty()) setupCopyListener()
//
//    }
//
//    override fun onFinishInflate() {
//        dependsOn<Boolean, Boolean, List<Pair<Int, Int>>, Int>(
//            ::isLoadingContent,
//            ::isSearch,
//            ::searchResults,
//            ::searchPosition
//        ) { ilc, iss, sr, sp ->
//            if (!ilc && iss) {
//                tv_text_content.renderSearchResult(sr)
//                tv_text_content.renderSearchPosition(sr.getOrNull(sp))
//            }
//            if (!ilc && !iss) {
//                tv_text_content.clearSearchResult()
//            }
//
//            bottombar.bindSearchInfo(sr.size, sp)
//        }
//    }
//
//    override fun bind(data: IViewModelState) {
//        data as ArticleState
//
//        isLike = data.isLike
//        isBookmark = data.isBookmark
//        isShowMenu = data.isShowMenu
//        isBigText = data.isBigText
//        isDarkMode = data.isDarkMode
//
//        if (data.title != null) title = data.title
//        if (data.category != null) category = data.category
//        if (data.categoryIcon != null) categoryIcon = data.categoryIcon as Int
//        content = data.content
//
//        isLoadingContent = data.isLoadingContent
//        isSearch = data.isSearch
//        searchQuery = data.searchQuery
//        searchPosition = data.searchPosition
//        searchResults = data.searchResults
//    }
//
//    override fun saveUi(outState: Bundle) {
//        outState.putBoolean(::isFocusedSearch.name, search_view?.hasFocus() ?: false)
//    }
//
//    override fun restoreUi(savedState: Bundle) {
//        isFocusedSearch = savedState.getBoolean(::isFocusedSearch.name)
//    }
//}