package com.example.alarstudios.fragments.data_list;

import com.example.alarstudios.models.Human;
import java.util.List;

/**
 * Contract between DataListPresenter and DataListFragment
 *
 * @author Vladislav Kuzmin
 * @since 1.0
 */
public interface DataListContract {
    interface Presenter {
        List<Human> getHumans(String code);
    }

    interface Fragment {
        void setDataToRecycler();
        void goToDetailedInfo(Human human);
    }
}
