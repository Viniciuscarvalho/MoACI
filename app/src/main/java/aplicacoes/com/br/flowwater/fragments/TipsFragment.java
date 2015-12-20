package aplicacoes.com.br.flowwater.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import aplicacoes.com.br.flowwater.R;

public class TipsFragment extends Fragment {

    private OnTipsFragmentInteractionListener mListener;

    private static final String ARG_USER = "user";

    public TipsFragment() {
    }

    public static TipsFragment newInstance(Parcelable user) {
        TipsFragment fragment = new TipsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_USER, user);
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
        View view = inflater.inflate(R.layout.fragment_tips, container, false);
        return view;
    }



    public interface OnTipsFragmentInteractionListener {
        void onTipsFragmentInteraction(Uri uri);
    }

    public interface OnTipsFragmentInteractonListener {
    }
}
