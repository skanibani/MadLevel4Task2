package com.example.madlevel4task2

import com.example.madlevel4task2.model.*
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_history.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class HistoryFragment : Fragment() {

    private var results: ArrayList<Result> = arrayListOf<Result>()
    private val resultAdapter = ResultAdapter(results)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

//        var test1 = Result(
//            date = Calendar.getInstance().time,
//            playerMove = Move.PAPER,
//            computerMove = Move.ROCK,
//            winner = Winner.PLAYER
//        )
//        results.add(test1)
//        resultAdapter.notifyDataSetChanged()
    }

    private fun initViews() {
        rvHistory.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvHistory.adapter = resultAdapter
        rvHistory.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }
}