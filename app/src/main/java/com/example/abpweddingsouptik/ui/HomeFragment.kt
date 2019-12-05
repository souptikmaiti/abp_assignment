///////////////////////////////////////////////////////
// Author: Souptik Maiti
// email: souptikmaiti@gmail.com
// mobile: 8240776027
///////////////////////////////////////////////////////
package com.example.abpweddingsouptik.ui


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.abpweddingsouptik.R
import com.example.abpweddingsouptik.adapter.VisitHistoryAdapter
import com.example.abpweddingsouptik.data.model.Visit
import com.example.abpweddingsouptik.viewmodel.ViewModelListener
import com.example.abpweddingsouptik.viewmodel.VisitHistoryViewModel
import com.example.abpweddingsouptik.viewmodel.VisitHistoryViewModelFactory
import kotlinx.android.synthetic.main.fragment_home.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class HomeFragment : Fragment(), AdapterView.OnItemSelectedListener, KodeinAware, ViewModelListener{

    companion object{
        var years = arrayOf("2017", "2018", "2019","2020")
        var yearsInt: Array<Int> = arrayOf(2017, 2018, 2019, 2020)
        var months = arrayOf("January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December")
        var monthsInt: Array<Int> = arrayOf(1,2,3,4,5,6,7,8,9,10,11,12)
    }
    override val kodein:Kodein by kodein()
    private val factory:VisitHistoryViewModelFactory by instance()
    private lateinit var viewModel: VisitHistoryViewModel
    private lateinit var visitList: ArrayList<Visit>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this,factory).get(VisitHistoryViewModel::class.java)
        viewModel.viewModelListener = this
        visitList = ArrayList<Visit>()
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title ="Activity Log"
        spinner_year.onItemSelectedListener = this
        spinner_month.onItemSelectedListener = this
        val adapterYears = ArrayAdapter<String>(context!!, android.R.layout.simple_spinner_item, years)
        adapterYears.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_year.adapter = adapterYears
        val adapterMonths = ArrayAdapter<String>(context!!, android.R.layout.simple_spinner_item, months)
        adapterMonths.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_month.adapter = adapterMonths

        initializeSpinner()


        refreshRecyclerView()

    }

    private fun initializeSpinner(){
        val currYear = Calendar.getInstance().get(Calendar.YEAR)
        val currMonth = Calendar.getInstance().get(Calendar.MONTH)
        spinner_year.setSelection(yearsInt.indexOfFirst { it == currYear }!!)
        spinner_month.setSelection(currMonth)
        //viewModel.year = currYear
        //viewModel.month = currMonth + 1
    }

    private fun refreshRecyclerView() {
        viewModel.getVisitHistory()
        visitList.clear()
        viewModel.histories.observe(viewLifecycleOwner, Observer { response ->
            if(response!=null){
                rv_visits.also {
                    var mapList = response.activityDocuments as HashMap<Long,List<Visit>>
                    val keyList = ArrayList(mapList.keys)
                    keyList.sortDescending()

                    for (i in keyList){
                        for(j in mapList.get(i)!!.iterator()){
                            visitList.add(j)
                        }

                    }
                    mapList.clear()
                    keyList.clear()
                    it.layoutManager = LinearLayoutManager(requireContext())
                    it.setHasFixedSize(true)
                    it.adapter = VisitHistoryAdapter(visitList)
                }
            }

        })

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, p3: Long) {

        if(adapterView?.id == R.id.spinner_year){
            viewModel.year = yearsInt[position]
        }
        if(adapterView?.id == R.id.spinner_month){
            viewModel.month = monthsInt[position]
        }
        refreshRecyclerView()
    }

    override fun onSuccess(message: String) {

    }

    override fun onFailure(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
        Log.i("TAG", message)
    }


}
