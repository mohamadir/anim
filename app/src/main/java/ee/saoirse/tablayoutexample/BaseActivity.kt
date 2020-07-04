package ee.saoirse.tablayoutexample

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.MaterialToolbar

open class BaseActivity : AppCompatActivity() {
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var topAppBar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setSupportActionBar(findViewById(R.id.topAppBar))
        coordinatorLayout  = layoutInflater.inflate(R.layout.activity_back_base, null) as CoordinatorLayout
        topAppBar = coordinatorLayout.findViewById<View>(R.id.topAppBar) as MaterialToolbar
        setContentView(R.layout.activity_back_base)

    }

    private fun setupNavigationBar(coordinatorLayout: CoordinatorLayout) {
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

    fun setHeaderName(name: String){
        topAppBar.title = name
    }

    fun isWithToolbar(toolbar: Boolean) {
        if (!toolbar){
            setSupportActionBar(null)
            coordinatorLayout.findViewById<MaterialToolbar>(R.id.topAppBar).visibility = GONE
        }
    }

    override fun setContentView(layoutResID: Int) {
        var activityContainer: FrameLayout = coordinatorLayout.findViewById(R.id.layout_container)
        setupNavigationBar(coordinatorLayout)
        layoutInflater.inflate(layoutResID, activityContainer, true)
        super.setContentView(coordinatorLayout)
    }
}