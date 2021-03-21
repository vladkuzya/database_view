package com.example.alarstudios.activities.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;

import android.os.Bundle;

import com.example.alarstudios.R;
import com.example.alarstudios.fragments.data_list.DataListFragment;

/**
 * Activity which has fragment manager. It replaces DataListFragment and DataInfoFragment
 *
 * @author Vladislav Kuzmin
 * @since 1.0
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showDataListFragment();
    }

    /**
     * Showing the first fragment - DataListFragment
     *
     * @since 1.0
     */
    private void showDataListFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_main_container, DataListFragment.getInstance())
                .commit();
    }

}