package com.android.drawertestapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.drawertestapp.MainActivity
import com.android.drawertestapp.adapter.NavigationDrawerAdapter
import com.android.drawertestapp.model.NavigationItem
import com.android.drawertestapp.callback.NavigationDrawerCallback
import com.android.drawertestapp.databinding.FragmentNavigationBinding
import com.android.football.base.BaseFragment


class NavigationFragment : BaseFragment<FragmentNavigationBinding>(FragmentNavigationBinding::inflate) {
    private var adapter: NavigationDrawerAdapter? = null

    override fun start(inflater: LayoutInflater, container: ViewGroup?) {
        setAdapter()
        adapter?.setSelected(0)
    }

    private fun setAdapter() {
            adapter = NavigationDrawerAdapter(object : NavigationDrawerCallback {
                override fun onDrawerClick(position: Int) {
                    replaceFragment(position)
                }

            })
            adapter!!.addData(setData())
            binding.rvNavigation.layoutManager = LinearLayoutManager(activity)
            binding.rvNavigation.adapter = adapter
    }
    private fun setData(): MutableList<NavigationItem>{
        return mutableListOf(
            NavigationItem("News")
            , NavigationItem("foo")
            , NavigationItem("Bar")
            , NavigationItem("Gar")
            , NavigationItem("Jar")
        )
    }

    private fun replaceFragment(position: Int) {
        (activity as MainActivity?)!!.replaceFragment(position)
        adapter?.setSelected(position)
    }

    companion object {
        fun newInstance(): NavigationFragment {
            val navigationFragment = NavigationFragment()
            val args = Bundle()
            navigationFragment.arguments = args
            return navigationFragment
        }
    }


}
