package com.tapptic.abwe.ui

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.tapptic.abwe.datamodels.Item
import com.tapptic.abwe.datamodels.ItemList
import com.tapptic.abwe.R
import com.tapptic.abwe.component.DaggerFragmentComponentDetail
import com.tapptic.abwe.module.FragmentModule
import com.tapptic.abwe.mvp.PresenterImpl
import com.tapptic.abwe.mvp.ViewModule
import kotlinx.android.synthetic.main.activity_item_detail.*
import javax.inject.Inject

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [MainActivity]
 * in two-pane mode (on tablets) or a [DetailActivity]
 * on handsets.
 */
class DetailFragment : Fragment() , ViewModule.View {
    override fun showData(data: MutableList<ItemList>?) {
    }

    override fun showError(message: String?) {
        /*Toast.makeText(activity, "ERROR IN FETCHING DATA. Try again",
                Toast.LENGTH_LONG).show()*/
    }

    override fun showComplete() {
    }

    override fun showProgress() {
    }

    override fun hideProgress() {
    }

    override fun showOneItem(item: Item?) {
        textView!!.text = item!!.text
        Picasso.get()
                .load(item.image)
                .resize(300, 300)
                .centerCrop()
                .into(imageView)
    }


    /**
     * The dummy content this fragment is presenting.
     */
    private var item: String? = null
    private var textView: TextView? = null
    private var imageView: ImageView? = null

    @Inject
    lateinit var presenter: PresenterImpl


    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injectDependency()

        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                item = it.getString(ARG_ITEM_ID)
                activity?.toolbar_layout?.title = item
            }
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.item_detail, container, false)

        intializeUiControls(rootView)

        return rootView
    }

    private fun injectDependency() {
        val aboutComponent = DaggerFragmentComponentDetail.builder()
                .fragmentModule(FragmentModule(this))
                .build()

        aboutComponent.inject(this)
    }

    private fun intializeUiControls(rootView: View?) {
        textView = rootView!!.findViewById(R.id.txtPrice) as TextView
        imageView = rootView.findViewById(R.id.txtMarket) as ImageView

        presenter.loadOneItem(item)
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
    }
}
