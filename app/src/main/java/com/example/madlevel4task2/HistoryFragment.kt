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
import com.example.madlevel4task2.repository.ResultRepository
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class HistoryFragment : Fragment() {

    private var results: ArrayList<Result> = arrayListOf<Result>()
    private val resultAdapter = ResultAdapter(results)

    // Room
    private lateinit var resultRepository: ResultRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)

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

        resultRepository = ResultRepository(requireContext())
        getResultsFromDatabase()
    }

    private fun initViews() {
        rvHistory.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvHistory.adapter = resultAdapter
        rvHistory.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        // Delete all Results stored in database
        fabDeleteHistory.setOnClickListener{
            removeAllResults()
        }
    }

    private fun getResultsFromDatabase() {
        mainScope.launch {
            val results = withContext(Dispatchers.IO) {
                resultRepository.getAllResults()
            }
            this@HistoryFragment.results.clear()
            this@HistoryFragment.results.addAll(results)
            this@HistoryFragment.resultAdapter.notifyDataSetChanged()
        }
    }

    private fun removeAllResults() {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                resultRepository.deleteAllResults()
            }
            getResultsFromDatabase()
        }
        resultAdapter.notifyDataSetChanged()
    }

}