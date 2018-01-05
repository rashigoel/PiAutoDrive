package com.example.hp.layout;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.hp.rg.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


public class Analytics extends Fragment {
   /*ArrayList<Point> values = new ArrayList<>();
    ArrayList<String> labels = new ArrayList<>();*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_analytics, container, false);
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(4f, 0));
        entries.add(new BarEntry(8f, 1));
        entries.add(new BarEntry(6f, 2));
        entries.add(new BarEntry(12f, 3));
        entries.add(new BarEntry(10f, 4));
        entries.add(new BarEntry(9f, 5));
        BarDataSet dataset = new BarDataSet(entries, "# of Calls");
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");
        BarChart chart = (BarChart)v.findViewById(R.id.chart);
        BarData data = new BarData();
        data.addDataSet(dataset);
        data.setBarWidth(0.5f);
     /*   chart.getAxisRight().setEnabled(false); // hides horizontal grid lines with below line
        chart.getAxisLeft().setEnabled(false);*/
        chart.invalidate();
        chart.setFitBars(true);
        chart.setClickable(false);
        chart.setDescription("");    // Hide the description
        chart.getLegend().setEnabled(false);
        chart.setDoubleTapToZoomEnabled(false);
        chart.setPinchZoom(false);
        chart.setGridBackgroundColor(ColorTemplate.COLOR_NONE);
        chart.setBackgroundColor(ColorTemplate.COLOR_NONE);
        chart.setViewPortOffsets(0f, 0f, 0f, 0f);


        chart.setData(data);
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        chart.animateY(5000);




       /* ArrayList<BarEntry> entries = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<String>();


        entries.add(new BarEntry(0,100));
        labels.add("1");
        entries.add(new BarEntry(1,90));
        labels.add("2");
        entries.add(new BarEntry(2,200));
        labels.add("3");
        entries.add(new BarEntry(3,350));
        labels.add("4");
        entries.add(new BarEntry(4,450));
        labels.add("5");

        BarDataSet dataset = new BarDataSet(entries, "# of Calls");
        BarData data = new BarData();
        data.addDataSet(dataset);
        barChart.setData(data); // set the data and list of lables into chart
        barChart.animateY(5000);
        dataset.setColors(ColorTemplate.PASTEL_COLORS);
        dataset.getBarShadowColor();
        dataset.setBarShadowColor(100);*/

        return v;
    }



}
