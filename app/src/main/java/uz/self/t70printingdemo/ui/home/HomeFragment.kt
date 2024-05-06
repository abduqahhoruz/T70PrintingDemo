package uz.self.t70printingdemo.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.pos.printerlib.Printer
import com.pos.printerlib.PrinterStatus
import com.pos.printerlib.status.PrinterStatusCallback
import uz.self.t70printingdemo.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    val TAG = "MYT70"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        binding.textHome.setOnClickListener {

            setPrinter()

        }
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    private fun setPrinter() {
        val isInit = Printer.getInstance().init(requireActivity())
        if (isInit) {
            val version = Printer.getInstance().printerVersion
            Log.d(TAG, "printerVersion:$version ")
            Printer.getInstance().setPrintListener(object : PrinterStatusCallback {
                override fun onStatus(p0: MutableList<PrinterStatus>?) {
                    Log.d(TAG, "onStatus: $p0")
                }

            })

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}