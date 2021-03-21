package com.example.alarstudios.fragments.data_info;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alarstudios.R;
import com.example.alarstudios.constants.BundleKeys;
import com.example.alarstudios.databinding.FragmentDataInfoBinding;
import com.example.alarstudios.fragments.data_list.DataListFragment;
import com.example.alarstudios.models.Human;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.Serializable;

/**
 * Fragment which shows the detailed info about the human which you clicked
 *
 * @author Vladislav Kuzmin
 * @since 1.0
 */
public class DataInfoFragment extends Fragment {
    private FragmentDataInfoBinding binding;
    private Human human;

    private OnMapReadyCallback callback = googleMap -> {
        LatLng position = new LatLng(human.getLat(), human.getLat());
        googleMap.addMarker(new MarkerOptions().position(position).title(human.getCountry()));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(position));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(5));
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_data_info, container, false);
        return binding.getRoot().getRootView();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getHumanFromBundle();
        initMap();
        initTextFields();
        initReturnButton();
    }

    /**
     * Getting Human data from Bundle
     *
     * @since 1.0
     */
    private void getHumanFromBundle() {
        Bundle bundle = getArguments();
        human = (Human) bundle.getSerializable(BundleKeys.HUMAN_KEY);
    }

    /**
     * Google Map initialization
     *
     * @since 1.0
     */
    private void initMap() {
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    /**
     * Text fields initialization by Human data
     *
     * @since 1.0
     */
    private void initTextFields() {
        binding.tvId.setText(human.getId());
        binding.tvName.setText(human.getName());
        binding.tvCountry.setText(human.getCountry());
    }

    /**
     * Return button initialization
     *
     * @since 1.0
     */
    private void initReturnButton() {
        binding.btnReturn.setOnClickListener(this::returnToList);
    }

    /**
     * If you clicked on return button, you will return to fragment with Human list
     *
     * @param view - View
     * @since 1.0
     */
    private void returnToList(View view) {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_main_container, DataListFragment.getInstance())
                .commit();
    }
}