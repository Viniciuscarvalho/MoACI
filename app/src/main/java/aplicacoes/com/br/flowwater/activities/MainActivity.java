package aplicacoes.com.br.flowwater.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import org.w3c.dom.Text;

import aplicacoes.com.br.flowwater.Interfaces.DrawerCallback;
import aplicacoes.com.br.flowwater.R;
import aplicacoes.com.br.flowwater.fragments.ChartsFragment;
import aplicacoes.com.br.flowwater.fragments.ConfigFragment;
import aplicacoes.com.br.flowwater.fragments.MapsFragment;
import aplicacoes.com.br.flowwater.fragments.TipsFragment;
import aplicacoes.com.br.flowwater.parcelables.User;

import static aplicacoes.com.br.flowwater.fragments.TipsFragment.*;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        MapsFragment.OnMapsFragmentInteractionListener,
        OnTipsFragmentInteractonListener {

    private static final String ARGS = "args";
    private static final String ARG_USER = "user";

    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Flow Water");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toogle);
        toogle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_drawer);
        navigationView.setNavigationItemSelectedListener(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(ARGS);
        if (bundle != null) {
            mUser = bundle.getParcelable(ARG_USER);
        }else if (savedInstanceState != null) {
            mUser = savedInstanceState.getParcelable(ARG_USER);
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.Fragment_container, ChartsFragment.newInstance(mUser))
                .commit();

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //refreshDrawer();
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putParcelable(ARG_USER, mUser);
        super.onSaveInstanceState(savedInstanceState);
    }

    private void refreshDrawer() {
        TextView username = (TextView) findViewById(R.id.username);
        TextView email = (TextView) findViewById(R.id.email);

        username.setText(mUser.getName());
        email.setText(mUser.getEmail());
    }

    private void commitChartsFragment() {

            FragmentManager fragmentManager = getSupportFragmentManager();
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

            toolbar.setTitle(R.string.menu_chart);
            fragmentManager.beginTransaction()
                    .replace(R.id.Fragment_container, ChartsFragment.newInstance(mUser))
                    .commit();

    }

    private void commitMapsFragment() {

            FragmentManager fragmentManager = getSupportFragmentManager();
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

            toolbar.setTitle(R.string.menu_map);
            fragmentManager.beginTransaction()
                    .replace(R.id.Fragment_container, MapsFragment.newInstance(mUser, false))
                    .commit();

    }

    private void commitTipsFragment() {

            FragmentManager fragmentManager = getSupportFragmentManager();
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

            toolbar.setTitle(R.string.menu_tips);
            fragmentManager.beginTransaction()
                    .replace(R.id.Fragment_container, TipsFragment.newInstance(mUser))
                    .commit();

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nvHome:
                        commitChartsFragment();
                        break;
                    case R.id.nvTips:
                        commitTipsFragment();
                        break;
                    case R.id.nvMap:
                        commitMapsFragment();
                        break;
                    case R.id.nvQuit:

                        break;

                }
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    // Callback from MainActivity
    @Override
    public void onMapsFragmentInteraction(LatLng latLng) {

    }
}
