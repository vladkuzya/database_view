package com.example.alarstudios.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Util for sending the GET request to server and getting response as json
 *
 * @author Vladislav Kuzmin
 * @since 1.0
 */
public class WebUtil {
    private static String json;
    public static String getResponse(URL url) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                URLConnection urlConn = url.openConnection();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                bufferedReader.close();
                json = stringBuilder.toString();
            } catch (IOException e) {
                throw new RuntimeException(e.getLocalizedMessage());
            }
        });
        thread.start();
        thread.join();
        return json;
    }
}
