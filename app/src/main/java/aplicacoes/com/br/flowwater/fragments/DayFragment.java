package aplicacoes.com.br.flowwater.fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

import aplicacoes.com.br.flowwater.R;


public class DayFragment extends Fragment {
    private View view;
    private BarChart chart;

    private OnFragmentInteractionListener mListener;

    public DayFragment() {
    }

    public static DayFragment newInstance(String param1, String param2) {
        DayFragment fragment = new DayFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.item_day, container, false);

        BarChart chart = (BarChart) view.findViewById(R.id.barChart);

        BarData data = new BarData(getXAxisValues(), getDataSet());
        chart.setData(data);
        chart.setDescription("Fluxo de água");
        chart.animateX(1000);
        chart.setDrawGridBackground(false);
        chart.setDrawBorders(false);
        chart.setNoDataTextDescription("Você não possui nenhuma medição");
        chart.setDrawHighlightArrow(false);
        chart.setDrawValueAboveBar(true);
        chart.setEnabled(false);
        chart.invalidate();

        Legend legend = chart.getLegend();
        legend.setTextSize(14f);
        legend.setTextColor(Color.WHITE);

        return view;
    }

    private ArrayList<BarDataSet> getDataSet() {
        ArrayList<BarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet = new ArrayList<>();
        valueSet.add(new BarEntry(100f, 0));
        valueSet.add(new BarEntry(80f, 1));
        valueSet.add(new BarEntry(70f, 2));
        valueSet.add(new BarEntry(120f, 3));
        valueSet.add(new BarEntry(30f, 4));
        valueSet.add(new BarEntry(35f, 5));

        BarDataSet barDataSet = new BarDataSet(valueSet, "Medições");
        barDataSet.setColor(Color.rgb(228, 196, 105));
        barDataSet.setBarSpacePercent(3);

        dataSets = new ArrayList<>();
        dataSets.add(barDataSet);
        return dataSets;
    }

    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add("Seg");
        xAxis.add("Ter");
        xAxis.add("Qua");
        xAxis.add("Qui");
        xAxis.add("Sex");
        xAxis.add("Sab");
        xAxis.add("Dom");


        //TODO Add line limited
        LimitLine line = new LimitLine(65f);
        line.setLineColor(Color.RED);

        return xAxis;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
