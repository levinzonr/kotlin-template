package dk.eboks.app.presentation.ui.components.start.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dk.eboks.app.BuildConfig
import dk.eboks.app.R
import dk.eboks.app.domain.models.Translation
import dk.eboks.app.presentation.base.BaseFragment
import dk.eboks.app.presentation.ui.components.start.signup.NameMailComponentFragment
import dk.eboks.app.presentation.ui.components.start.login.LoginComponentFragment
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
            (activity as StartActivity).replaceFragment(NameMailComponentFragment())
        }
        logonBtn.setOnClickListener {
            /*
            val move = TransitionInflater.from(activity).inflateTransition(android.R.transition.move)
            val fade = TransitionInflater.from(activity).inflateTransition(android.R.transition.fade)

            val fragment = UserCarouselComponentFragment()

            sharedElementReturnTransition = move
            exitTransition = fade

            fragment.sharedElementEnterTransition = move
            fragment.enterTransition = fade
            //exitT.excludeTarget(R.id.start_root, true)
            //exitT.addTarget(R.id.start_desc_tv)

            // Add second fragment by replacing first
            val ft = fragmentManager.beginTransaction()
                    .replace(R.id.containerFl, fragment)
                    .addToBackStack(UserCarouselComponentFragment::class.java.simpleName)
                    .addSharedElement(signupBtn, "signUpButtonTransition")
                    .addSharedElement(logoIv, "eboksLogoTransition")
            ft.commit()
            */
            (activity as StartActivity).replaceFragment(LoginComponentFragment())
        }
        if(BuildConfig.DEBUG) {
            debugSkipBtn.visibility = View.VISIBLE
            debugSkipBtn.setOnClickListener {
                (activity as StartActivity).startMain()
            }
        }
        /*
        if(BuildConfig.DEBUG)
            Toast.makeText(activity, "Press volume down 3 times for debug menu", Toast.LENGTH_SHORT).show()
            */
    }

    override fun setupTranslations() {
        signupBtn.text = Translation.start.signupButton
        logonBtn.text = Translation.start.logonButton
    }

}