package aplicacoes.com.br.flowwater.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

import aplicacoes.com.br.flowwater.R;
import aplicacoes.com.br.flowwater.parcelables.User;


public class MapsFragment extends Fragment {

    private static final String ARG_USER = "user";
    private static final String ARG_MMARKER = "mMarker";

    private FloatingActionButton mFabMyLocation;

    private GoogleMap mGoogleMap;
    private LatLng mMyLatLng;
    private User mUser;

    private ClusterManager<Place> mClusterManager;

    private OnMapsFragmentInteractionListener mListener;

    public static MapsFragment newInstance(Parcelable user, boolean mMarker) {
        MapsFragment fragment = new MapsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_USER, user);
        args.putBoolean(ARG_MMARKER, mMarker);
        fragment.setArguments(args);
        return fragment;
    }

    public MapsFragment() {

    }

    public class Place implements ClusterItem {
        private final LatLng mPosition;

        public Place(double lat, double lng) {
            mPosition = new LatLng(lat, lng);
        }

        @Override
        public LatLng getPosition() {
            return mPosition;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUser = getArguments().getParcelable(ARG_USER);
        } else if (savedInstanceState !=null) {
            mUser = savedInstanceState.getParcelable(ARG_USER);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putParcelable(ARG_USER, mUser);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps, container, false);

        mFabMyLocation = (FloatingActionButton) view.findViewById(R.id.fabMyLocation);

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    class CustomRenderer <T extends ClusterItem> extends DefaultClusterRenderer<T>{
        public CustomRenderer(Context context, GoogleMap mGoogleMap, ClusterManager<T> clusterManager) {
            super(context, mGoogleMap, clusterManager);
        }

        @Override
        protected boolean shouldRenderAsCluster(Cluster<T> cluster) {
            //start clustering if at least 2 items overlap
            return cluster.getSize() > 2;
        }
    }

    private void addItems() {

        //Set lat/lng to start with.
        double lat = -3.7357094;
        double lng = -38.5069693;

        //Add ten cluster items in close proximity, for purposes.
        for (int i = 0; i < 20; i++) {
            double offset = i / 200d;
            lat = lat + offset;
            lng = lng + offset;
            Place offsetItem = new Place(lat, lng);
            mClusterManager.addItem(offsetItem);
        }
    }

    private void setUpMapIfNeeded() {
        if (mGoogleMap == null) {
            mGoogleMap = ((SupportMapFragment) getChildFragmentManager()
                        .findFragmentById(R.id.map))
                        .getMap();
            mGoogleMap.getUiSettings().setMapToolbarEnabled(true);
            mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
            if (mGoogleMap != null) {

                mClusterManager = new ClusterManager<>(getContext(), mGoogleMap);
                mClusterManager.setRenderer(new CustomRenderer<>(getContext(), mGoogleMap, mClusterManager));
                mGoogleMap.setOnCameraChangeListener(mClusterManager);
                mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-3, -38), 10));

                addItems();

            }
        }
    }

//    private void myLocation() {
//        if (mMyLatLng != null) {
//            mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mMyLatLng, 10));
//        }
//    }
//
//    private void addMarker(LatLng latLng) {
//        mGoogleMap.addMarker(new MarkerOptions()
//                .position(latLng)
//                .title(getAddressByLatLng(latLng))
//                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
//        addLatLng(latLng);
//    }
//
//    private void initMarker() {
//        Location location = getLastKnowLocation();
//        if (location != null) {
//            mMyLatLng = new LatLng(location.getLatitude(), location.getLongitude());
//        }
//    }
//
//    private Location getLastKnowLocation() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
//                getActivity().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
//                getActivity().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            return null;
//        }
//
//        LocationManager locationManager = (LocationManager)
//                getActivity().getSystemService(Context.LOCATION_SERVICE);
//
//        boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
//        boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
//
//        List<String> providers = locationManager.getProviders(true);
//        for (String provider : providers) {
//            Location location = locationManager.getLastKnownLocation(provider);
//            if (location != null) {
//                return location;
//            }
//        }
//
//        return null;
//    }
//
//    private String getAddressByLatLng(LatLng latLng) {
//        String address = "";
//        try {
//            if (latLng != null) {
//                Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
//                List<Address> listAddresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
//                if (listAddresses != null && !listAddresses.isEmpty()) {
//                    //address += listAddresses.get(0).getAdminArea();
//                    address += listAddresses.get(0).getLocality();
//                }
//            }
//        } catch (IOException e) {
//            Log.e(getString(R.string.app_name), "Error getting address from location", e);
//        }
//        return address;
//    }
//
//    private void addLatLng(LatLng latLng) {
//        if (mListener != null) {
//            mListener.onMapsFragmentInteraction(latLng);
//        }
//    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnMapsFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnMapsFragmentInteractionListener {
        public void onMapsFragmentInteraction(LatLng latLng);
    }

}
