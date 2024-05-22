package com.example.gamjaproject_now;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gamjaproject_now.API.Content;
import com.example.gamjaproject_now.API.APIController;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProgramActivity extends AppCompatActivity {

    ImageView programV;
    TextView programNA;

    TextView programDi;

    TextView programSU;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);

        programV = (ImageView) findViewById(R.id.programView);
        programNA = (TextView) findViewById(R.id.programname);
        programSU = (TextView) findViewById(R.id.programsummary);
        programDi = (TextView) findViewById(R.id.programdirector);


        Intent intent = getIntent();


        String title = intent.getStringExtra("title");
        String director = intent.getStringExtra("director");
        String description = intent.getStringExtra("description");
        String image = intent.getStringExtra("image");
        Log.d("message", title);
        programNA.setText(title);
        programDi.setText(director);
        programSU.setText(description);
        new ProgramActivity.DownloadFilesTask().execute(image);








        Call<Content[]> call = APIController.getTestCall("movie_test", 2, 1);
        call.enqueue(new Callback<Content[]>() {
            @Override
            public void onResponse(Call<Content[]> call, Response<Content[]> response) {
                Content[] result = response.body();

                ////                if (result != null && result.length > 0) {
                ////                    Log.d("img_link", "img_link : " + result[1].getImg());
                ////                    test.append(result[1].getTitle());
                ////                    new DownloadFilesTask().execute(result[1].getImg());
                //                } else {
                //                    Log.e("API Response", "Response is null or empty");
                //                }

                    Log.d("img_link", "img_link : " + result[0].getImg());
                    //programNA.append(result[0].getTitle());
                    //programSU.append(result[0].getDescription());
                    //programDi.append(result[0].getDirector());
                    //new ProgramActivity.DownloadFilesTask().execute(result[0].getImg());



            }



            @Override
            public void onFailure(Call<Content[]> call, Throwable t) {
                Log.d("결과", "실패 : " + t.getMessage());
            }
        });







    }
    private class DownloadFilesTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bmp = null;
            try {
                String img_url = strings[0];
                URL url = new URL(img_url);
                bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (MalformedURLException e) {
                Log.e("DownloadFilesTask", "MalformedURLException: " + e.getMessage());
                e.printStackTrace();
            } catch (IOException e) {
                Log.e("DownloadFilesTask", "IOException: " + e.getMessage());
                e.printStackTrace();
            }
            return bmp;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            if (result != null) {
                programV.setImageBitmap(result);
            } else {
                Log.e("DownloadFilesTask", "Bitmap is null");
            }


        }
    }
}
