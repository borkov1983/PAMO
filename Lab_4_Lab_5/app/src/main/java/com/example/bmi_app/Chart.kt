package com.example.bmi_app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.charts.Cartesian
import com.anychart.data.Mapping
import com.anychart.data.Set
import com.anychart.enums.Anchor
import com.anychart.enums.MarkerType
import com.anychart.enums.TooltipPositionMode
import kotlinx.android.synthetic.main.chart.*
import java.util.*

class Chart : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chart)

        backChart.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val cartesian: Cartesian = AnyChart.line()
        cartesian.animation(true)
        cartesian.padding(10, 30, 80, 10)
        cartesian.crosshair().enabled(true)
        cartesian.crosshair().yLabel(true)


//                    .yStroke((Stroke) null , null, null, (String) "null", (String) "null");
        cartesian.tooltip().positionMode(TooltipPositionMode.POINT)

        cartesian.title("Liczba zachorowań na Covid-19 w Polsce, Włoszech i Hiszpani w marcu 2020r.")

        cartesian.yAxis(0).title("Liczba osób zarażonych")
        cartesian.xAxis(0).labels().padding(5.0, 5.0, 5.0, 5.0)

        val seriesData: MutableList<DataEntry> = ArrayList()

        seriesData.add(CustomDataEntry("1.03", 0, 1694, 74))
        seriesData.add(CustomDataEntry("2.03", 0, 2036, 120))
        seriesData.add(CustomDataEntry("3.03", 0, 2502, 165))
        seriesData.add(CustomDataEntry("4.03", 1, 3089, 222))
        seriesData.add(CustomDataEntry("5.03", 3, 3858, 259))
        seriesData.add(CustomDataEntry("6.03", 5, 4636, 400))
        seriesData.add(CustomDataEntry("7.03", 5, 5883, 500))
        seriesData.add(CustomDataEntry("8.03", 11, 7375, 673))
        seriesData.add(CustomDataEntry("9.03", 16, 9172, 1073))
        seriesData.add(CustomDataEntry("10.03", 22, 10149, 1695))
        seriesData.add(CustomDataEntry("11.03", 31, 12462, 2277))
        seriesData.add(CustomDataEntry("12.03", 4.1, 12462, 2300))
        seriesData.add(CustomDataEntry("13.03", 68, 17660, 5232))
        seriesData.add(CustomDataEntry("14.03", 103, 21157, 6391))
        seriesData.add(CustomDataEntry("15.03", 119, 24747, 7798))
        seriesData.add(CustomDataEntry("16.03", 177, 27980, 9942))
        seriesData.add(CustomDataEntry("17.03", 238, 31506, 11748))
        seriesData.add(CustomDataEntry("18.03", 251, 35713, 13910))
        seriesData.add(CustomDataEntry("19.03", 355, 41035, 17963))
        seriesData.add(CustomDataEntry("20.03", 425, 47021, 20410))
        seriesData.add(CustomDataEntry("21.03", 536, 53578, 25374))
        seriesData.add(CustomDataEntry("22.03", 600, 59138, 28768))
        seriesData.add(CustomDataEntry("23.03", 749, 63927, 35136))
        seriesData.add(CustomDataEntry("24.03", 901, 69176, 35136))
        seriesData.add(CustomDataEntry("25.03", 1051, 74386, 49515))
        seriesData.add(CustomDataEntry("26.03", 1221, 80589, 57786))
        seriesData.add(CustomDataEntry("27.03", 1389, 86498, 65719))
        seriesData.add(CustomDataEntry("28.03", 1638, 92472, 73235))
        seriesData.add(CustomDataEntry("29.03", 1862, 97689, 80110))
        seriesData.add(CustomDataEntry("30.03", 2055, 101739, 87956))
        seriesData.add(CustomDataEntry("31.03", 2311, 105792, 95923))

        val set: Set = Set.instantiate()
        set.data(seriesData)
        val series1Mapping: Mapping = set.mapAs("{ x: 'x', value: 'value' }")
        val series2Mapping: Mapping = set.mapAs("{ x: 'x', value: 'value2' }")
        val series3Mapping: Mapping = set.mapAs("{ x: 'x', value: 'value3' }")

        val series1 = cartesian.line(series1Mapping)
        series1.name("Polska")
        series1.hovered().markers()
            .enabled(true)
        series1.hovered().markers().type(MarkerType.CIRCLE)
            .size(4)
        series1.tooltip()
            .position("right")
            .anchor(Anchor.LEFT_CENTER)
            .offsetX(5)
            .offsetY(5)

        val series2 = cartesian.line(series2Mapping)
        series2.name("Włochy")
        series2.hovered().markers()
            .enabled(true)
        series2.hovered().markers().type(MarkerType.CIRCLE)
            .size(4)
        series2.tooltip()
            .position("right")
            .anchor(Anchor.LEFT_CENTER)
            .offsetX(5)
            .offsetY(5)

        val series3 = cartesian.line(series3Mapping)
        series3.name("Hiszpania")
        series3.hovered().markers()
            .enabled(true)
        series3.hovered().markers().type(MarkerType.CIRCLE)
            .size(4)
        series3.tooltip()
            .position("right")
            .anchor(Anchor.LEFT_CENTER)
            .offsetX(5)
            .offsetY(5)

        cartesian.legend().enabled(true)
        cartesian.legend().fontSize(13)
        cartesian.legend().padding(0, 0, 10, 0)

        any_chart_view.setChart(cartesian)
    }

    class CustomDataEntry(x: String, value: Number, value2: Number, value3: Number) : DataEntry() {

        init {
            super.setValue(x,value)
            setValue("value", value)
            setValue("value2", value2)
            setValue("value3", value3)
        }
    }
}