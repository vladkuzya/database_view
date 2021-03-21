package com.example.alarstudios.utils;

import android.os.AsyncTask;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.example.alarstudios.R;

/**
 * Util for downloading and setting the picture to image view in the RecyclerView
 *
 * @author Vladislav Kuzmin
 * @since 1.0
 */
public class DownloadPictureTask extends AsyncTask<ImageView, Void, DrawableTypeRequest<String>> {
    private ImageView imageView;
    @Override
    protected DrawableTypeRequest<String> doInBackground(ImageView... imageViews) {
        imageView = imageViews[0];
        return downloadImage(imageView.getTag(R.id.glide_tag).toString());
    }

    @Override
    protected void onPostExecute(DrawableTypeRequest<String> stringDrawableTypeRequest) {
        stringDrawableTypeRequest.into(imageView);
    }

    private DrawableTypeRequest<String> downloadImage(String url) {
        return Glide.with(imageView.getContext())
                .load(url);
    }
}
