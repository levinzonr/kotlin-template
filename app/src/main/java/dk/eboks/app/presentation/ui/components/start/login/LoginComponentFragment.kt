package dk.eboks.app.presentation.ui.components.start.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import dk.eboks.app.BuildConfig
import dk.eboks.app.R
import dk.eboks.app.domain.models.Translation
import dk.eboks.app.presentation.base.BaseFragment
import dk.eboks.app.presentation.base.SheetComponentActivity
import dk.eboks.app.presentation.ui.components.start.welcome.WelcomeComponentFragment
import dk.eboks.app.presentation.ui.screens.start.StartActivity
import kotlinx.android.synthetic.main.fragment_login_component.*
import kotlinx.android.synthetic.main.include_toolnar.*
import javax.inject.Inject

/**
 * Created by bison on 09-02-2018.
 */
class LoginComponentFragment : BaseFragment(), LoginComponentContract.View {

    @Inject
    lateinit var presenter : LoginComponentContract.Presenter

    var showGreeting : Boolean = true

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater?.inflate(R.layout.fragment_login_component, container, false)
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        component.inject(this)
        presenter.onViewCreated(this, lifecycle)
        getBaseActivity()?.setToolbar(R.drawable.ic_red_back, Translation.logoncredentials.title, null, {
            (activity as StartActivity).onBackPressed()
        })
        makeMocks()

        if(BuildConfig.DEBUG) {
            debugSkipBtn.visibility = View.VISIBLE
            debugSkipBtn.setOnClickListener {
                val emailOrCpr = cprEmailEt.text?.toString()?.trim() ?: ""
                if(emailOrCpr.isNotBlank())
                {
                    presenter.createUser(emailOrCpr)
                    if(showGreeting)
                    {
                        fragmentManager.beginTransaction().remove(this).replace(R.id.containerFl, UserCarouselComponentFragment(), UserCarouselComponentFragment::class.java.simpleName).commit()
                        //(activity as StartActivity).replaceFragment(UserCarouselComponentFragment())
                        /*
                        fragmentManager.findFragmentByTag(WelcomeComponentFragment::class.java.simpleName)?.let { frag->
                            fragmentManager.beginTransaction().remove(frag).commitNowAllowingStateLoss()
                            //fragmentManager.beginTransaction().replace(R.id.containerFl, UserCarouselComponentFragment(), UserCarouselComponentFragment::class.java.simpleName).commitNow()
                        }
                        */
                    }
                    else {
                        (activity as StartActivity).onBackPressed()
                    }
                }
            }
        }

        redOptionTv.setOnClickListener {
            val intent = Intent(activity, SheetComponentActivity::class.java)
            intent.putExtra("component", ForgotPasswordComponentFragment::class.java.simpleName)
            activity.startActivity(intent)
            activity.overridePendingTransition(0,0)
        }

        arguments?.let { args->
            if(args.getBoolean("showGreeting", true)) {
                showGreeting = true
                headerTv.visibility = View.VISIBLE
                detailTv.visibility = View.VISIBLE
            }
            else
            {
                showGreeting = false
                headerTv.visibility = View.GONE
                detailTv.visibility = View.GONE
            }
        }
    }

    fun makeMocks()
    {
        val li = LayoutInflater.from(context)

        val listener = object : View.OnClickListener {
            override fun onClick(p0: View?) {
                val intent = Intent(activity, SheetComponentActivity::class.java)
                intent.putExtra("component", ActivationCodeComponentFragment::class.java.simpleName)
                activity.startActivity(intent)
                activity.overridePendingTransition(0,0)
            }
        }

        var v = li.inflate(R.layout.viewholder_login_provider, loginProvidersLl, false)
        v.findViewById<ImageView>(R.id.iconIv).setImageResource(R.drawable.ic_fingerprint)
        v.findViewById<TextView>(R.id.nameTv).text = "_Logon with fingerprint"
        v.findViewById<TextView>(R.id.descTv).visibility = View.GONE
        v.setOnClickListener(listener)
        loginProvidersLl.addView(v)

        v = li.inflate(R.layout.viewholder_login_provider, loginProvidersLl, false)
        v.findViewById<ImageView>(R.id.iconIv).setImageResource(R.drawable.ic_idporten)
        v.findViewById<TextView>(R.id.nameTv).text = "_Logon with ID porten"
        v.findViewById<TextView>(R.id.descTv).text = "_Use this to see mail from public authories"
        v.setOnClickListener(listener)
        loginProvidersLl.addView(v)

        v = li.inflate(R.layout.viewholder_login_provider, loginProvidersLl, false)
        v.findViewById<ImageView>(R.id.iconIv).setImageResource(R.drawable.ic_bankid)
        v.findViewById<TextView>(R.id.nameTv).text = "_Logon with BankID"
        v.findViewById<TextView>(R.id.descTv).visibility = View.GONE
        v.setOnClickListener(listener)
        loginProvidersLl.addView(v)
    }

    override fun setupTranslations() {
        headerTv.text = Translation.logoncredentials.topLabel
        detailTv.text = Translation.logoncredentials.topSublabel
        cprEmailTil.hint = Translation.logoncredentials.emailfieldHeader
        passwordTil.hint = Translation.logoncredentials.passwordfieldHeader
        redOptionTv.visibility = View.VISIBLE
        redOptionTv.text = Translation.logoncredentials.forgotPasswordButton
    }

}