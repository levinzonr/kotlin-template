package dk.eboks.app.presentation.ui.components.start.welcome

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dk.eboks.app.BuildConfig
import dk.eboks.app.R
import dk.eboks.app.domain.config.Config
import dk.eboks.app.domain.models.Translation
import dk.eboks.app.presentation.base.BaseFragment
import dk.eboks.app.presentation.ui.components.debug.DebugOptionsComponentFragment
import dk.eboks.app.presentation.ui.components.start.signup.NameMailComponentFragment
import dk.eboks.app.presentation.ui.components.start.login.LoginComponentFragment
import dk.eboks.app.presentation.ui.components.start.login.UserCarouselComponentFragment
import dk.eboks.app.presentation.ui.screens.debug.user.DebugUserActivity
import dk.eboks.app.presentation.ui.screens.start.StartActivity
import kotlinx.android.synthetic.main.fragment_welcome_component.*

/**
 * Created by bison on 09-02-2018.
 */
class WelcomeComponentFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater?.inflate(R.layout.fragment_welcome_component, container, false)
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //(activity as StartActivity).showLogo(true)
        signupBtn.setOnClickListener {
            getBaseActivity()?.addFragmentOnTop(R.id.containerFl, NameMailComponentFragment(), true)
        }
        logonBtn.setOnClickListener {
            getBaseActivity()?.addFragmentOnTop(R.id.containerFl, LoginComponentFragment(), true)
        }
        if(BuildConfig.DEBUG) {
            debugCreateBtn.visibility = View.VISIBLE
            debugCreateBtn.setOnClickListener {
                (activity as StartActivity).startMain()
            }
            debugOptionsTv.visibility = View.VISIBLE
            debugOptionsTv.setOnClickListener {
                getBaseActivity()?.openComponentDrawer(DebugOptionsComponentFragment::class.java)
            }
            debugCreateUserTv.visibility = View.VISIBLE
            debugCreateUserTv.setOnClickListener {
                activity.startActivity(Intent(activity, DebugUserActivity::class.java))
            }
        }
        /*
        if(BuildConfig.DEBUG)
            Toast.makeText(activity, "Press volume down 3 times for debug menu", Toast.LENGTH_SHORT).show()
            */
        (activity as StartActivity).enableFragmentCheapFades()
    }

    override fun onResume() {
        super.onResume()
        logoIv.setImageResource(Config.getLogoResourceId())
        if(shouldGotoUsersOnResume)
        {
            shouldGotoUsersOnResume = false
            getBaseActivity()?.setRootFragment(R.id.containerFl, UserCarouselComponentFragment())
        }
    }

    override fun setupTranslations() {
    }

    companion object {
        var shouldGotoUsersOnResume = false
    }

}