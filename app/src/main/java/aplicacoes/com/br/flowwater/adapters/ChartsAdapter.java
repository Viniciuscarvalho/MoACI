package aplicacoes.com.br.flowwater.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import aplicacoes.com.br.flowwater.fragments.DayFragment;
import aplicacoes.com.br.flowwater.fragments.MonthFragment;
import aplicacoes.com.br.flowwater.fragments.WeekFragment;
import aplicacoes.com.br.flowwater.parcelables.User;

/**
 * Created by user3 on 04/11/15.
 */
public class ChartsAdapter extends FragmentPagerAdapter {

    private static final int POSITION_DAY = 0;
    private static final int POSITION_WEEK = 1;
    private static final int POSITION_MONTH = 2;

    private User mUser;

    public ChartsAdapter(FragmentManager fragmentManager, User user) {
        super(fragmentManager);
        mUser = user;
    }

    @Override
    public Fragment getItem (int position) {
        switch (position) {
            case POSITION_DAY:
                return DayFragment.newInstance("", "");
            case POSITION_WEEK:
                return WeekFragment.newInstance("", "");
            case POSITION_MONTH:
                return MonthFragment.newInstance("", "");
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }


    @Override
    public CharSequence getPageTitle (int position) {
        switch (position) {
            case POSITION_DAY:
                return "Dia";
            case POSITION_WEEK:
                return "Semana";
            case POSITION_MONTH:
                return "Mês";
        }
        return null;
    }
}

