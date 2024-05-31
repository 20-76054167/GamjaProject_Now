package com.example.gamjaproject_now;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gamjaproject_now.API.Content;
import com.example.gamjaproject_now.API.APIController;
import com.example.gamjaproject_now.API.ContentGenre;
import com.example.gamjaproject_now.API.Count;
import com.example.gamjaproject_now.API.Genre;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RandomActivity extends AppCompatActivity {
    ImageView programV;
    TextView programNA;
    TextView programDi;
    TextView programSU;
    String[] tableList = {"couplay", "kakaowebtoon", "kpnovel", "naverwebtoon", "netflix", "watcha"};
    String[] GenretableList = {"couplay_genre", "kakaowebtoon_genre", "kpnovel_genre", "naverwebtoon_genre", "netflix_genre", "watcha_genre"};
    String tableName;
    private Content[] result;
    Random rand;
    int PageRandom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_random);

        ImageView DICE = (ImageView) findViewById(R.id.dice);

        View.OnClickListener dice = new View.OnClickListener(){
            public void onClick(View v){
                Intent intentRR = new Intent(RandomActivity.this, RandomActivity.class);
                startActivity(intentRR);
                finish();
            }
        };
        DICE.setOnClickListener(dice);



        programV = (ImageView) findViewById(R.id.randomprogramView);
        programNA = (TextView) findViewById(R.id.randomprogramname);
        programSU = (TextView) findViewById(R.id.randomprogramsummary);
        programDi = (TextView) findViewById(R.id.randomprogramdirector);
        rand = new Random();

        fetchDataFromApi();

//        137, 224, 4816, 657, 305, 200;
    }

    private void fetchDataFromApi() {
            tableName = tableList[rand.nextInt(tableList.length)];
            if (tableName.equals(tableList[0])) {
                PageRandom = (int) (Math.random() * 137) + 1;
            } else if (tableName.equals(tableList[1])) {
                PageRandom = (int) (Math.random() * 224) + 1;
            } else if (tableName.equals(tableList[2])) {
                PageRandom = (int) (Math.random() * 4816) + 1;
            } else if (tableName.equals(tableList[3])) {
                PageRandom = (int) (Math.random() * 657) + 1;
            } else if (tableName.equals(tableList[4])) {
                PageRandom = (int) (Math.random() * 305) + 1;
            } else if (tableName.equals(tableList[5])) {
                PageRandom = (int) (Math.random() * 200) + 1;
            }
            Call<Content[]> call = APIController.getTestCall(tableName, PageRandom, 1);
            call.enqueue(new Callback<Content[]>() {
                @Override
                public void onResponse(Call<Content[]> call, Response<Content[]> response) {
                    if (response.isSuccessful()) {
                        result = response.body();
                        if(result != null) {
                            programNA.setText(result[0].getTitle());
                            programDi.setText(result[0].getDirector());
                            programSU.setText(result[0].getDescription());
                            new RandomActivity.DownloadFilesTask().execute(result[0].getImg());
                        }


                    } else {
                        Toast.makeText(RandomActivity.this, "API call failed", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Content[]> call, Throwable t) {
                    Log.d("결과", "실패 : " + t.getMessage());
                }
            });

        }




    private class DownloadFilesTask extends AsyncTask<String, Void, Bitmap> {
        private int index;
        public DownloadFilesTask() {
            this.index = index;
        }
        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bmp = null;
            InputStream inputStream = null;//추가사항
            HttpURLConnection connection = null;//추가사항
            try {
                String img_url = strings[0];
                URL url = new URL(img_url);
                connection = (HttpURLConnection) url.openConnection();//추가사항
                connection.setConnectTimeout(10000); // 10초 타임아웃 추가사항
                connection.setReadTimeout(15000);    // 15초 타임아웃 추가사항
                connection.setRequestProperty("User-Agent", "Mozilla/5.0"); // User-Agent 설정
                int responseCode = connection.getResponseCode();//추가사항

                if(responseCode == HttpURLConnection.HTTP_OK) {
                    inputStream = connection.getInputStream();
                    bmp = BitmapFactory.decodeStream(inputStream);
//                    bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream()); //이거 남겨 이게 원본
                }else{
                    Log.e("DownloadFilesTask", "서버 응답 오류: " + responseCode + " URL: " + img_url);
                }
            } catch (MalformedURLException e) {
                Log.e("DownloadFilesTask", "MalformedURLException: " + e.getMessage());
                e.printStackTrace();
            } catch (IOException e) {
                Log.e("DownloadFilesTask", "IOException: " + e.getMessage());
                e.printStackTrace();
            }finally{
                if(inputStream != null){
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
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


