package uz.self.t70printingdemo

import android.os.Bundle
import android.util.Log
import android.view.Menu
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.pos.printerlib.Printer
import com.pos.printerlib.PrinterStatus
import com.pos.printerlib.status.PrinterStatusCallback
import uz.self.t70printingdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val TAG: String = "MYT70"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUI()
    }

    private fun setUI() {
        binding.testPrint.setOnClickListener {
            setPrinter()
        }
    }


    private fun setPrinter() {
        val isInit = Printer.getInstance().init(this)
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

}