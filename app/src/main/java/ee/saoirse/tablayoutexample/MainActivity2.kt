package ee.saoirse.tablayoutexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.MaterialToolbar

class MainActivity2 : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
//        layoutInflater.inflate(R.layout.activity_main,findViewById(R.id.mFrameLayout), true)

//        isWithToolbar(false)
        setHeaderName("MSFT")
     //   setupNavigationBar()
    }

    private fun setupNavigationBar() {
        val topAppBar = findViewById<View>(R.id.topAppBar) as MaterialToolbar
        topAppBar.setNavigationOnClickListener {
            finish()
        }
        topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.favorite -> {
                    // Handle favorite icon press

                    true
                }
                else -> false
            }
        }
    }
}