package com.example.bmi_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.MarkerType;
import com.anychart.enums.TooltipPositionMode;


import java.util.ArrayList;
import java.util.List;

public class Chart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart);
        Button back = findViewById(R.id.backChart);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });

            AnyChartView anyChartView = findViewById(R.id.any_chart_view);

            Cartesian cartesian = AnyChart.line();

            cartesian.animation(true);

            cartesian.padding(10d, 30d, 80d, 10d);

            cartesian.crosshair().enabled(true);
            cartesian.crosshair()
                    .yLabel(true);

//                    .yStroke((Stroke) null , null, null, (String) "null", (String) "null");

            cartesian.tooltip().positionMode(TooltipPositionMode.POINT);

            cartesian.title("Liczba zachorowań na Covid-19 w Polsce, Włoszech i Hiszpani w marcu 2020r.");

            cartesian.yAxis(0).title("Liczba osób zarażonych");
            cartesian.xAxis(0).labels().padding(5d, 5d, 5d, 5d);

            List<DataEntry> seriesData = new ArrayList<>();
            seriesData.add(new CustomDataEntry("1.03", 0, 1694, 74));
            seriesData.add(new CustomDataEntry("2.03", 0, 2036, 120));
            seriesData.add(new CustomDataEntry("3.03", 0, 2502, 165));
            seriesData.add(new CustomDataEntry("4.03", 1, 3089, 222));
            seriesData.add(new CustomDataEntry("5.03", 3, 3858, 259));
            seriesData.add(new CustomDataEntry("6.03", 5, 4636, 400));
            seriesData.add(new CustomDataEntry("7.03", 5, 5883, 500));
            seriesData.add(new CustomDataEntry("8.03", 11, 7375, 673));
            seriesData.add(new CustomDataEntry("9.03", 16, 9172, 1073));
            seriesData.add(new CustomDataEntry("10.03", 22, 10149, 1695));
            seriesData.add(new CustomDataEntry("11.03", 31, 12462, 2277));
            seriesData.add(new CustomDataEntry("12.03", 4.1, 12462, 2300));
            seriesData.add(new CustomDataEntry("13.03", 68, 17660, 5232));
            seriesData.add(new CustomDataEntry("14.03", 103, 21157, 6391));
            seriesData.add(new CustomDataEntry("15.03", 119, 24747, 7798));
            seriesData.add(new CustomDataEntry("16.03", 177, 27980, 9942));
            seriesData.add(new CustomDataEntry("17.03", 238, 31506, 11748));
            seriesData.add(new CustomDataEntry("18.03", 251, 35713, 13910));
            seriesData.add(new CustomDataEntry("19.03", 355, 41035, 17963));
            seriesData.add(new CustomDataEntry("20.03", 425, 47021, 20410));
            seriesData.add(new CustomDataEntry("21.03", 536, 53578, 25374));
            seriesData.add(new CustomDataEntry("22.03", 600, 59138, 28768));
            seriesData.add(new CustomDataEntry("23.03", 749, 63927, 35136));
            seriesData.add(new CustomDataEntry("24.03", 901, 69176, 35136));
            seriesData.add(new CustomDataEntry("25.03", 1051, 74386, 49515));
            seriesData.add(new CustomDataEntry("26.03", 1221, 80589, 57786));


            Set set = Set.instantiate();
            set.data(seriesData);
            Mapping series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");
            Mapping series2Mapping = set.mapAs("{ x: 'x', value: 'value2' }");
            Mapping series3Mapping = set.mapAs("{ x: 'x', value: 'value3' }");

            Line series1 = cartesian.line(series1Mapping);
            series1.name("Polska");
            series1.hovered().markers().enabled(true);
            series1.hovered().markers()
                    .type(MarkerType.CIRCLE)
                    .size(4d);
            series1.tooltip()
                    .position("right")
                    .anchor(Anchor.LEFT_CENTER)
                    .offsetX(5d)
                    .offsetY(5d);

            Line series2 = cartesian.line(series2Mapping);
            series2.name("Włochy");
            series2.hovered().markers().enabled(true);
            series2.hovered().markers()
                    .type(MarkerType.CIRCLE)
                    .size(4d);
            series2.tooltip()
                    .position("right")
                    .anchor(Anchor.LEFT_CENTER)
                    .offsetX(5d)
                    .offsetY(5d);

            Line series3 = cartesian.line(series3Mapping);
            series3.name("Hiszpania");
            series3.hovered().markers().enabled(true);
            series3.hovered().markers()
                    .type(MarkerType.CIRCLE)
                    .size(4d);
            series3.tooltip()
                    .position("right")
                    .anchor(Anchor.LEFT_CENTER)
                    .offsetX(5d)
                    .offsetY(5d);

            cartesian.legend().enabled(true);
            cartesian.legend().fontSize(13d);
            cartesian.legend().padding(0d, 0d, 10d, 0d);

           anyChartView.setChart(cartesian);
        }

        private class CustomDataEntry extends ValueDataEntry {

            CustomDataEntry(String x, Number value, Number value2, Number value3) {
                super(x, value);
                setValue("value2", value2);
                setValue("value3", value3);
            }

        }

    }
