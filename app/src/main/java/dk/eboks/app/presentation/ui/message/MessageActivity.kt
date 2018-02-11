package dk.eboks.app.presentation.ui.message

import android.os.Bundle
import android.util.Log
import dk.eboks.app.presentation.base.BaseActivity
import javax.inject.Inject

class MessageActivity : BaseActivity(), MessageContract.View {
    @Inject lateinit var presenter: MessageContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
        presenter.onViewCreated(this, lifecycle)
        setContentView(dk.eboks.app.R.layout.activity_message)

    }

    override fun setupTranslations() {

    }

    override fun showError(msg: String) {
        Log.e("debug", msg)
    }
}
