package com.allef.developer.marketplaceoffers.menu


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.allef.developer.marketplaceoffers.R


/**
 * A simple [Fragment] subclass.
 *
 */
class DefaultMenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_default_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        open.setOnClickListener {
//            context?.let { it1 -> NotificationUtils.notificationWithTapAction(it1) }
//        }
    }
}
