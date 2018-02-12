package dk.eboks.app.presentation.ui.message.components.pdfpreview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dk.eboks.app.R
import dk.eboks.app.domain.models.Folder
import dk.eboks.app.presentation.ui.message.components.SheetComponentFragment
import kotlinx.android.synthetic.main.fragment_pdfpreview_component.*
import javax.inject.Inject

/**
 * Created by bison on 09-02-2018.
 */
class PdfPreviewComponentFragment : SheetComponentFragment(), PdfPreviewComponentContract.View {

    @Inject
    lateinit var presenter : PdfPreviewComponentContract.Presenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater?.inflate(R.layout.fragment_pdfpreview_component, container, false)
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        component.inject(this)
        presenter.onViewCreated(this, lifecycle)
        pdfView.filename = "pdf.pdf"
    }

    override fun setupTranslations() {

    }

    override fun updateView(folder: Folder) {

    }


}