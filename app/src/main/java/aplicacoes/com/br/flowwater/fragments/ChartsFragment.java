package aplicacoes.com.br.flowwater.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

import aplicacoes.com.br.flowwater.R;
import aplicacoes.com.br.flowwater.adapters.ChartsAdapter;
import aplicacoes.com.br.flowwater.parcelables.User;

public class ChartsFragment extends Fragment {
    private View v;

    private User mUser;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private OnChartsFragmentInteractionListener mListener;

    private static final String ARG_USER = "user";

    public static ChartsFragment newInstance(Parcelable user) {
        ChartsFragment fragment = new ChartsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_USER, user);
        fragment.setArguments(args);
        return fragment;
    }

    public ChartsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUser = getArguments().getParcelable(ARG_USER);
        } else if (savedInstanceState != null) {
            mUser = savedInstanceState.getParcelable(ARG_USER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_charts, container, false);

        viewPager = (ViewPager) v.findViewById(R.id.viewpager);
        viewPager.setAdapter(new ChartsAdapter(getChildFragmentManager(), mUser));

        tabLayout = (TabLayout) v.findViewById(R.id.tabsCharts);
        tabLayout.setupWithViewPager(viewPager);

        return v;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public interface OnChartsFragmentInteractionListener {
        public void onChartsFramgmentInteraction();
    }
}
