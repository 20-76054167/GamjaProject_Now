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

public class ProgramActivity extends AppCompatActivity {


    ImageView programV;
    TextView programNA;

    TextView programDi;

    TextView programSU;
    String[] tableList = {"couplay", "kakaowebtoon", "kpnovel", "naverwebtoon", "netflix", "watcha"};
    String[] GenretableList = {"couplay_genre", "kakaowebtoon_genre", "kpnovel_genre", "naverwebtoon_genre", "netflix_genre", "watcha_genre"};
    ImageView[] iv_imagearr = new ImageView[12];
    TextView[] testArr = new TextView[12];
    private Content[] resultG;

    private Content[] result;

    String genretableN;
    int Gid;
    int GC;
    Bitmap bitmap;
    int index = 0;
    int All;
    Random rand;
    int PageRandom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);

        Intent intent = getIntent();

        ImageView LOGO = (ImageView) findViewById(R.id.logo);


        String title = intent.getStringExtra("title");
        String director = intent.getStringExtra("director");
        String description = intent.getStringExtra("description");
        String image = intent.getStringExtra("image");
        String actor = intent.getStringExtra("actor");
        int id = intent.getIntExtra("id", -1);
        String url = intent.getStringExtra("url");


        String tableName = intent.getStringExtra("tableName");

        Log.d("p-id", String.valueOf(id));
        Log.d("tablename", tableName != null ? tableName : "DefaultMessage");
        Log.d("Content_URL", "Content_URL : " + url);


        if (tableName.equals(tableList[0])) {
            genretableN = "couplay_genre";
        } else if (tableName.equals(tableList[1])) {
            genretableN = "kakaowebtoon_genre";
        } else if (tableName.equals(tableList[2])) {
            genretableN = "kpnovel_genre";
        } else if (tableName.equals(tableList[3])) {
            genretableN = "naverwebtoon_genre";
        } else if (tableName.equals(tableList[4])) {
            genretableN = "netflix_genre";
        } else if (tableName.equals(tableList[5])) {
            genretableN = "watcha_genre";
        }

        Log.d("genretableName", genretableN != null ? genretableN : "DefaultMessage");

        View.OnClickListener reroll = new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentR = new Intent(ProgramActivity.this, MainActivity.class);
                startActivity(intentR);
                finish();
            }
        };


        LOGO.setOnClickListener(reroll);


        programV = (ImageView) findViewById(R.id.programView);
        programNA = (TextView) findViewById(R.id.programname);
        programSU = (TextView) findViewById(R.id.programsummary);
        programDi = (TextView) findViewById(R.id.programdirector);
        programNA.setText(title);
        programDi.setText(director);
        programSU.setText(description);
        new DownloadFilesTaskP().execute(image);


//        Thread imgThread = new Thread() {
//            public void run() {
//                try {
//                    URL url = new URL(image);
//                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                    conn.setDoInput(true);
//                    conn.connect();
//                    InputStream is = conn.getInputStream();
//                    bitmap = BitmapFactory.decodeStream(is);
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//        imgThread.start();
//
//        try {
//            imgThread.join();
//            programV.setImageBitmap(bitmap);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        testArr[0] = (TextView) findViewById(R.id.test);
        testArr[1] = (TextView) findViewById(R.id.test2);
        testArr[2] = (TextView) findViewById(R.id.test3);
        testArr[3] = (TextView) findViewById(R.id.test4);
        testArr[4] = (TextView) findViewById(R.id.test5);
        testArr[5] = (TextView) findViewById(R.id.test6);
        testArr[6] = (TextView) findViewById(R.id.test7);
        testArr[7] = (TextView) findViewById(R.id.test8);
        testArr[8] = (TextView) findViewById(R.id.test9);
        testArr[9] = (TextView) findViewById(R.id.test10);
        testArr[10] = (TextView) findViewById(R.id.test11);
        testArr[11] = (TextView) findViewById(R.id.test12);

        iv_imagearr[0] = (ImageView) findViewById(R.id.imageView);
        iv_imagearr[1] = (ImageView) findViewById(R.id.imageView2);
        iv_imagearr[2] = (ImageView) findViewById(R.id.imageView3);
        iv_imagearr[3] = (ImageView) findViewById(R.id.imageView4);
        iv_imagearr[4] = (ImageView) findViewById(R.id.imageView5);
        iv_imagearr[5] = (ImageView) findViewById(R.id.imageView6);
        iv_imagearr[6] = (ImageView) findViewById(R.id.imageView7);
        iv_imagearr[7] = (ImageView) findViewById(R.id.imageView8);
        iv_imagearr[8] = (ImageView) findViewById(R.id.imageView9);
        iv_imagearr[9] = (ImageView) findViewById(R.id.imageView10);
        iv_imagearr[10] = (ImageView) findViewById(R.id.imageView11);
        iv_imagearr[11] = (ImageView) findViewById(R.id.imageView12);


        fetchGidAndProceed(tableName, id);
        Log.d("genre__id", String.valueOf(Gid));

//        137, 224, 4816, 657, 305, 200;

    }

    private void fetchGidAndProceed(String tableName, int id) {

        Call<ContentGenre[]> genrecall = APIController.getGenreCall(genretableN, id);
        genrecall.enqueue(new Callback<ContentGenre[]>() {
            @Override
            public void onResponse(Call<ContentGenre[]> genrecall, Response<ContentGenre[]> response) {
                if (response.isSuccessful()) {
                    ContentGenre[] resultGenre = response.body();
                    Gid = resultGenre[0].getGenre_id();
                    Log.d("genre_id", String.valueOf(Gid));
                    fetchGenreDataFromApi(tableName, Gid, 12);
                }
            }
            @Override
            public void onFailure(Call<ContentGenre[]> call, Throwable t) {
                Log.e("API Call Failure", t.getMessage());
            }
        });
    }


    private void fetchGenreDataFromApi(String tableName, int Gid, int pagingUnit) {
        Call<Content[]> genreTestcall = APIController.getGenreTestCall(tableName, Gid);
        genreTestcall.enqueue(new Callback<Content[]>() {
            Intent[] intent = new Intent[pagingUnit];
            @Override
            public void onResponse(Call<Content[]> genreTestcall, Response<Content[]> response) {
                if (response.isSuccessful()) {
                    resultG = response.body();

                    ArrayList<Integer> indices = new ArrayList<>();
                    for (int i = 0; i < resultG.length; i++) {
                        indices.add(i);
                    }
                    Collections.shuffle(indices);

                    for (int i = 0; i < Math.min(pagingUnit, resultG.length); i++) {
                        int index = indices.get(i);
                        testArr[i].append(resultG[index].getTitle());
                        Log.d("genretitles", "genretitles :  " + resultG[index].getTitle());
                        Log.d("genreimg", "genreimage:" + resultG[index].getImg());
                        new ProgramActivity.DownloadFilesTask(i).execute(resultG[index].getImg());

                        intent[i] = new Intent(ProgramActivity.this, ProgramActivity.class);
                        intent[i].putExtra("tableName", tableName);
                        intent[i].putExtra("title", resultG[index].getTitle());
                        intent[i].putExtra("director", resultG[index].getDirector());
                        intent[i].putExtra("description", resultG[index].getDescription());
                        intent[i].putExtra("image", resultG[index].getImg());
                        intent[i].putExtra("id", resultG[index].getId());
                        intent[i].putExtra("actor", resultG[index].getActor());
                    }

                } else {
                    Toast.makeText(ProgramActivity.this, "API call failed", Toast.LENGTH_SHORT).show();
                }

                setOnClickListeners(intent);
            }

            @Override
            public void onFailure(Call<Content[]> genreTestcall, Throwable t) {
                Log.d("결과", "실패 : " + t.getMessage());
            }
        });
    }

    private void setOnClickListeners(Intent[] intent) {
        for (int i = 0; i < intent.length; i++) {
            int index = i;
            if (testArr[index] != null) {
                testArr[index].setOnClickListener(v -> {
                    startActivity(intent[index]);
//                    finish();
                });
            }
            if (iv_imagearr[index] != null) {
                iv_imagearr[index].setOnClickListener(v -> {
                    startActivity(intent[index]);
//                    finish();
                });
            }
        }
    }



    //    private class DownloadFilesTask extends AsyncTask<String, Void, Bitmap> {
//        @Override
//        protected Bitmap doInBackground(String... strings) {
//            Bitmap bmp = null;
//            try {
//                String img_url = strings[0];
//                URL url = new URL(img_url);
//                bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//            } catch (MalformedURLException e) {
//                Log.e("DownloadFilesTask", "MalformedURLException: " + e.getMessage());
//                e.printStackTrace();
//            } catch (IOException e) {
//                Log.e("DownloadFilesTask", "IOException: " + e.getMessage());
//                e.printStackTrace();
//            }
//            return bmp;
//        }
    private class DownloadFilesTask extends AsyncTask<String, Void, Bitmap> {
        private int index;
        public DownloadFilesTask(int index) {
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

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    inputStream = connection.getInputStream();
                    bmp = BitmapFactory.decodeStream(inputStream);
//                    bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream()); //이거 남겨 이게 원본
                } else {
                    Log.e("DownloadFilesTask", "서버 응답 오류: " + responseCode + " URL: " + img_url);
                }
            } catch (MalformedURLException e) {
                Log.e("DownloadFilesTask", "MalformedURLException: " + e.getMessage());
                e.printStackTrace();
            } catch (IOException e) {
                Log.e("DownloadFilesTask", "IOException: " + e.getMessage());
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
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
        protected void onPostExecute(Bitmap resultG) {
            if (resultG != null) {
                iv_imagearr[index].setImageBitmap(resultG);
                index++;
            } else {
                Log.e("DownloadFilesTask", "Bitmap is null");
            }
        }
    }

    private class DownloadFilesTaskP extends AsyncTask<String, Void, Bitmap> {
        private int index;
        public DownloadFilesTaskP() {
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

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    inputStream = connection.getInputStream();
                    bmp = BitmapFactory.decodeStream(inputStream);
//                    bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream()); //이거 남겨 이게 원본
                } else {
                    Log.e("DownloadFilesTask", "서버 응답 오류: " + responseCode + " URL: " + img_url);
                }
            } catch (MalformedURLException e) {
                Log.e("DownloadFilesTask", "MalformedURLException: " + e.getMessage());
                e.printStackTrace();
            } catch (IOException e) {
                Log.e("DownloadFilesTask", "IOException: " + e.getMessage());
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
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


