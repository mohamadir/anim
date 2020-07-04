package ee.saoirse.tablayoutexample

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity(), AppBarLayout.OnOffsetChangedListener {
    private val tabLayout: TabLayout? = null
    private var scrollView: NestedScrollView? = null
    private var viewChanged: View? = null
    private var pieChart: PieChart? = null
    private var textView: TextView? = null

    var isShow = true
    var scrollRange = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tabLayout = findViewById<View>(R.id.tabLayout) as TabLayout
        val appbar = findViewById<View>(R.id.appbar) as AppBarLayout
        appbar.addOnOffsetChangedListener(this)
        appbar
        scrollView = findViewById<View>(R.id.scrollView) as NestedScrollView
        textView = findViewById<TextView>(R.id.textView) as TextView
        setScrollChange()
        tabLayout.setTabTextColors(Color.BLACK, Color.WHITE)
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"))
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"))
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"))
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"))
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"))
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"))
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"))
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"))
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"))


        pieChart = findViewById<PieChart>(R.id.pieChart) as PieChart
        pieChart!!.setUsePercentValues(true)
        val xvalues = ArrayList<PieEntry>()
        xvalues.add(PieEntry(34.0f, "London"))
        xvalues.add(PieEntry(28.2f, "Coventry"))
        xvalues.add(PieEntry(37.9f, "Manchester"))
        val dataSet = PieDataSet(xvalues, "")
        val data = PieData(dataSet)
        // In Percentage
        data.setValueFormatter(PercentFormatter())
        dataSet.colors = listOf(Color.RED, Color.YELLOW, Color.GREEN)
        pieChart!!.data = data
        pieChart!!.description.text = ""
        pieChart!!.isDrawHoleEnabled = false
        data.setValueTextSize(13f)

        chartDetails(pieChart!!, Typeface.SANS_SERIF)
    }

    private fun setScrollChange() {


        scrollView?.setOnScrollChangeListener { view, x, y, oX, oY ->
//            Log.i("scrollViewPrint","$i2")
            if (y > oY && oY == 0 ) {
                expandView(pieChart!!, pieChart?.layoutParams!!.height, pieChart?.layoutParams!!.height / 2, 0f, 20f);
                pieChart!!.data.setValueTextColor(Color.TRANSPARENT)
            }
            if (y == 0   ){
                pieChart!!.data.setValueTextColor(Color.BLACK)
                expandView(pieChart!!, pieChart?.layoutParams!!.height, pieChart?.layoutParams!!.height * 2, 20f, 0f);
            }
            pieChart?.requestLayout()
            pieChart?.invalidate()
        }


    }


    fun chartDetails(mChart: PieChart, tf: Typeface) {
        mChart.description.isEnabled = true
        mChart.centerText = ""
        mChart.setCenterTextSize(10F)
        mChart.setCenterTextTypeface(tf)
        val l = mChart.legend
        mChart.legend.isWordWrapEnabled = true
        mChart.legend.isEnabled = false
        l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        l.formSize = 20F
        l.formToTextSpace = 5f
        l.form = Legend.LegendForm.SQUARE
        l.textSize = 12f
        l.orientation = Legend.LegendOrientation.HORIZONTAL
        l.isWordWrapEnabled = true
        l.setDrawInside(false)
        mChart.setTouchEnabled(false)
        mChart.setDrawEntryLabels(false)
        mChart.legend.isWordWrapEnabled = true
        mChart.setExtraOffsets(20f, 0f, 20f, 0f)
        mChart.setUsePercentValues(true)
        // mChart.rotationAngle = 0f
        mChart.setUsePercentValues(true)
        mChart.setDrawCenterText(false)
        mChart.description.isEnabled = true
        mChart.isRotationEnabled = false
    }


    fun expandView(view: View,
                   currentHeight: Int,
                   newHeight: Int,
                   startSize : Float,
                  endSize : Float) {

        var slideAnimator: ValueAnimator = ValueAnimator.ofInt(currentHeight, newHeight)
                .setDuration(500)
        slideAnimator.addUpdateListener { animation1: ValueAnimator ->
            val value = animation1.animatedValue as Int
            view.layoutParams.height = value
            view.layoutParams.width = value
            view.requestLayout()
        }

        /* We use an update listener which listens to each tick
         * and manually updates the height of the view  */
        slideAnimator.addUpdateListener {
            var value: Int = it.animatedValue as Int
            view.getLayoutParams().height = value
            view.getLayoutParams().width = value
            view.requestLayout();
        }

        var animationSet: AnimatorSet = AnimatorSet();
        animationSet.setInterpolator(AccelerateDecelerateInterpolator());
        animationSet.play(slideAnimator);
        animationSet.start()




        val animationDuration: Long = 500 // Animation duration in ms


        val animator = ValueAnimator.ofFloat(startSize, endSize)
        animator.duration = animationDuration

        animator.addUpdateListener { valueAnimator ->
            val animatedValue = valueAnimator.animatedValue as Float
            textView?.setTextSize(animatedValue)
        }

        animator.start()


    }


    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
//        val percentage: Double = Math.abs(verticalOffset).toDouble() / appBarLayout!!.getHeight()
//        val scale = ( 1 - percentage ) * 100
//        val scaleVal = Math.round((scale  *  viewChanged?.layoutParams?.width!! ) ) / 100
//        val textView = findViewById<TextView>(R.id.textView)
//        if (verticalOffset == 0 ){
//            Log.i("COLLAPSE-MODE","expanded")
//            viewChanged?.layoutParams?.width = (viewChanged?.layoutParams?.width!! / 2).toInt()
//            viewChanged?.layoutParams?.height = (viewChanged?.layoutParams?.height!! / 2).toInt()
//        } else {
//            Log.i("COLLAPSE-MODE","collapsed")
//
//        }
//        textView.setTextSize((resources.getDimension(R.dimen.textSize) * percentage).toFloat())
//        textView.requestLayout()
//        viewChanged?.layoutParams?.width = (viewChanged?.layoutParams?.width!! * scale).toInt()
//        viewChanged?.layoutParams?.height = (viewChanged?.layoutParams?.height!! * scale).toInt()
//        viewChanged!!.scaleY = 0.5f
//        viewChanged!!.scaleX = 0.5f
//        viewChanged?.requestLayout()
//        viewChanged?.invalidate()
        Log.i("scale-offset", viewChanged?.layoutParams?.height.toString())


//
//        var scalPer = Math.abs(verticalOffset)-appBarLayout!!.getTotalScrollRange()
//        viewChanged?.layoutParams?.height =  ( viewChanged?.layoutParams?.height!! * percentage ).toInt()
//        viewChanged?.layoutParams?.width =  ( viewChanged?.layoutParams?.width!! * percentage ).toInt()
//        viewChanged.invalidate()


    }

}