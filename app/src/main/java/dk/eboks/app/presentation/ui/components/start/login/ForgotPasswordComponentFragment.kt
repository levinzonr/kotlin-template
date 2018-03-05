package dk.eboks.app.presentation.ui.components.start.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dk.eboks.app.R
import dk.eboks.app.domain.models.Translation
import dk.eboks.app.presentation.base.BaseFragment
import dk.eboks.app.presentation.base.SheetComponentActivity
import kotlinx.android.synthetic.main.fragment_forgot_password_component.*
import javax.inject.Inject

/**
 * Created by bison on 09-02-2018.
 */
class ForgotPasswordComponentFragment : BaseFragment(), ForgotPasswordComponentContract.View {

    @Inject
    lateinit var presenter : ForgotPasswordComponentContract.Presenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater?.inflate(R.layout.fragment_forgot_password_component, container, false)
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        component.inject(this)
        presenter.onViewCreated(this, lifecycle)
        headerTv.requestFocus()
        cancelTv.setOnClickListener {
            (activity as SheetComponentActivity).onBackPressed()
        }
    }

    override fun setupTranslations() {
        headerTv.text = Translation.forgotpassword.title
        detailTv.text = Translation.forgotpassword.subtitle
        emailTil.hint = Translation.forgotpassword.emailHeader
        resetPasswordBtn.text = Translation.forgotpassword.resetPasswordButton
        cancelTv.text = Translation.defaultSection.cancel
    }

}