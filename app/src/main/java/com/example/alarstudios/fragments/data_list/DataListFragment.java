package com.example.alarstudios.fragments.data_list;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alarstudios.R;
import com.example.alarstudios.constants.BundleKeys;
import com.example.alarstudios.constants.SharedPrefKeys;
import com.example.alarstudios.databinding.FragmentDataListBinding;
import com.example.alarstudios.fragments.adapters.DataListAdapter;
import com.example.alarstudios.fragments.data_info.DataInfoFragment;
import com.example.alarstudios.models.Human;
import com.example.alarstudios.utils.UtilSharedPreferences;

/**
 * Fragment which includes the RecyclerView with Humans data. If you
 * scrolled to the last position in list, the app will load more data from server.
 * If you clicked on some item, you will see detailed info about it
 *
 * @author Vladislav Kuzmin
 * @since 1.0
 */
public class DataListFragment extends Fragment implements DataListContract.Fragment {
    private FragmentDataListBinding binding;
    private static DataListFragment instance;
    private DataListAdapter adapter;
    private DataListContract.Presenter presenter;

    public static DataListFragment getInstance() {
        if (instance == null) {
            instance = new DataListFragment();
        }
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataListAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_data_list, container, false);
        return binding.getRoot().getRootView();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new DataListPresenter();
        initRecyclerView();
        setDataToRecycler();
    }

    /**
     * Setting data to recycler view
     *
     * @since 1.0
     */
    @Override
    public void setDataToRecycler() {
        binding.progressCircular.setVisibility(View.VISIBLE);
        adapter.setData(presenter.getHumans(UtilSharedPreferences.loadValue(getActivity(), SharedPrefKeys.CODE_KEY, "0")));
        new Handler().postDelayed(() -> {
            binding.progressCircular.setVisibility(View.GONE);
            adapter.notifyDataSetChanged();
        }, 500);
    }

    /**
     * Going to fragment where you will see detailed info
     *
     * @param human - human which you clicked
     * @since 1.0
     */
    @Override
    public void goToDetailedInfo(Human human) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(BundleKeys.HUMAN_KEY, human);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_main_container, DataInfoFragment.class, bundle)
                .commit();
    }

    /**
     * Initialization the RecyclerView by data from server
     *
     * @since 1.0
     */
    private void initRecyclerView() {
        binding.rvDataList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvDataList.setHasFixedSize(true);
        binding.rvDataList.setAdapter(adapter);
        DividerItemDecoration itemDecorator = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        binding.rvDataList.addItemDecoration(itemDecorator);
    }

    /**
     * Initialization DataListAdapter
     *
     * @since 1.0
     */
    private void initDataListAdapter() {
        adapter = new DataListAdapter(this);
    }
}