package com.example.alarstudios.fragments.data_list;

import com.example.alarstudios.constants.WebConstants;
import com.example.alarstudios.models.Data;
import com.example.alarstudios.models.Human;
import com.example.alarstudios.utils.WebUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DataListPresenter implements DataListContract.Presenter {
    private final String TAG = DataListPresenter.class.getSimpleName();
    private final Gson gson = new GsonBuilder().create();
    private List<Human> humans;
    private int page = 1;

    @Override
    public List<Human> getHumans(String code) {
        Thread thread = new Thread(() -> {
            try {
                URL url = new URL(WebConstants.DATA_URL + "?" + WebConstants.CODE_PARAM + code + "&" + WebConstants.PAGE_PARAM + page++);
                String response = WebUtil.getResponse(url);
                Data data = gson.fromJson(response, Data.class);
                humans = data.getData();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e.getLocalizedMessage());
            } catch (JsonSyntaxException e) {
                humans = new ArrayList<>();
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
        return humans;
    }
}
