package com.allef.developer.marketplaceoffers.favorite


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
class FavoriteProductFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_product, container, false)
    }


}
