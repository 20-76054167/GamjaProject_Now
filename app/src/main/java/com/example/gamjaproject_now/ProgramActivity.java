package com.example.gamjaproject_now;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gamjaproject_now.API.Content;
import com.example.gamjaproject_now.API.APIController;
import com.example.gamjaproject_now.API.ContentGenre;
import com.example.gamjaproject_now.API.Genre;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
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
    String[] GenretableList = { "couplay_genre", "kakaowebtoon_genre", "kpnovel_genre", "naverwebtoon_genre", "netflix_genre", "watcha_genre"};
    ImageView[] iv_imagearr = new ImageView[10];
    TextView[] testArr = new TextView[10];
    private Content[] resultG;

    private Content[] result;

    String genretableN;
    int Gid;
    Bitmap bitmap;
    int index = 0;
    int All;
    Random rand;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);

        Intent intent = getIntent();




        String title = intent.getStringExtra("title");
        String director = intent.getStringExtra("director");
        String description = intent.getStringExtra("description");
        String image = intent.getStringExtra("image");
        int id = intent.getIntExtra("id", -1);

        String tableName = intent.getStringExtra("tableName");

        Log.d("p-id", String.valueOf(id));
        Log.d("tablename", tableName != null? tableName : "DefaultMessage");

        if(tableName.equals(tableList[0])){
            genretableN="couplay_genre";
        }else if(tableName.equals(tableList[1])){
            genretableN="kakaowebtoon_genre";
        }else if(tableName.equals(tableList[2])){
            genretableN="kpnovel_genre";
        }else if(tableName.equals(tableList[3])){
            genretableN="naverwebtoon_genre";
        }else if(tableName.equals(tableList[4])){
            genretableN="netflix_genre";
        }else if(tableName.equals(tableList[5])){
            genretableN="watcha_genre";
        }
        Log.d("genretableName", genretableN != null? genretableN : "DefaultMessage");


//        Log.d("tablename", tableName);

//        Call<ContentGenre[]> call13 = APIController.getGenreCall(tableName, id);
//        call13.enqueue(new Callback<ContentGenre[]>() {
//            @Override
//            public void onResponse(Call<ContentGenre[]> call13, Response<ContentGenre[]> response) {
//                if(response.isSuccessful()){
//                    ContentGenre[] result = response.body();
//
//                    Log.d("responseSuccess", result.toString());
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ContentGenre[]> call13, Throwable t) {
//                Log.d("responseFail", t.getMessage());
//            }
//        });


        programV = (ImageView) findViewById(R.id.programView);
        programNA = (TextView) findViewById(R.id.programname);
        programSU = (TextView) findViewById(R.id.programsummary);
        programDi = (TextView) findViewById(R.id.programdirector);
        programNA.setText(title);
        programDi.setText(director);
        programSU.setText(description);

        Thread imgThread = new Thread() {
            public void run() {
                try {
                    URL url = new URL(image);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();

                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        imgThread.start();

        try {
            imgThread.join();
            programV.setImageBitmap(bitmap);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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




        Call<ContentGenre[]> genrecall = APIController.getGenreCall(genretableN, id);

        genrecall.enqueue(new Callback<ContentGenre[]>() {
            @Override
            public void onResponse(Call<ContentGenre[]> genrecall, Response<ContentGenre[]> response) {
                if(response.isSuccessful()){
                    ContentGenre[] resultGenre = response.body();
                    Gid = resultGenre[0].getGenre_id();
                    Log.d("genre_id", String.valueOf(Gid));

                }
            }

            @Override
            public void onFailure(Call<ContentGenre[]> call, Throwable t) {

            }
        });








        int PageRandom = (int) (Math.random()*5) + 1;

        fetchDataFromApi(tableName, PageRandom, 10);
    }


    private void fetchDataFromApi(String tableList, int page, int pagingUnit) {
        Call<Content[]> call = APIController.getTestCall(tableList, page, pagingUnit);
        call.enqueue(new Callback<Content[]>() {
            Intent[] intent = new Intent[pagingUnit];
            @Override
            public void onResponse(Call<Content[]> call, Response<Content[]> response) {
                if (response.isSuccessful()) {
                    result = response.body();
                    for (All = 0; All < pagingUnit; All++) {
//                        Log.d("img_link", "img_link : " + result[i].getImg());
                        testArr[All].append(result[All].getTitle());
                        new ProgramActivity.DownloadFilesTask().execute(result[All].getImg());

                        intent[All] = new Intent(ProgramActivity.this, ProgramActivity.class);
                        intent[All].putExtra("tableName", tableList);
                        intent[All].putExtra("title", result[All].getTitle());
                        intent[All].putExtra("director", result[All].getDirector());
                        intent[All].putExtra("description", result[All].getDescription());
                        intent[All].putExtra("image", result[All].getImg());
                        intent[All].putExtra("id", result[All].getId());
                        intent[All].putExtra("actor", result[All].getActor());


                    }

                } else {
                    Toast.makeText(ProgramActivity.this, "API call failed", Toast.LENGTH_SHORT).show();
                }


                testArr[0].setOnClickListener(v -> {
                    if (result != null) {
                        startActivity(intent[0]);

                    }
                });
                testArr[1].setOnClickListener(v -> {
                    if (result != null) {
                        startActivity(intent[1]);
                    }
                });
                testArr[2].setOnClickListener(v -> {
                    if (result != null) {
                        startActivity(intent[2]);
                    }
                });
                testArr[3].setOnClickListener(v -> {
                    if (result != null) {
                        startActivity(intent[3]);
                    }
                });
                testArr[4].setOnClickListener(v -> {
                    if (result != null) {
                        startActivity(intent[4]);
                    }
                });
                testArr[5].setOnClickListener(v -> {
                    if (result != null) {
                        startActivity(intent[5]);
                    }
                });
                testArr[6].setOnClickListener(v -> {
                    if (result != null) {
                        startActivity(intent[6]);
                    }
                });
                testArr[7].setOnClickListener(v -> {
                    if (result != null) {
                        startActivity(intent[7]);
                    }
                });
                testArr[8].setOnClickListener(v -> {
                    if (result != null) {
                        startActivity(intent[8]);
                    }
                });
                testArr[9].setOnClickListener(v -> {
                    if (result != null) {
                        startActivity(intent[9]);
                    }
                });
                iv_imagearr[0].setOnClickListener(v -> {
                    if (result != null) {
                        startActivity(intent[0]);
                    }
                });
                iv_imagearr[1].setOnClickListener(v -> {
                    if (result != null) {
                        startActivity(intent[1]);
                    }
                });
                iv_imagearr[2].setOnClickListener(v -> {
                    if (result != null) {
                        startActivity(intent[2]);
                    }
                });
                iv_imagearr[3].setOnClickListener(v -> {
                    if (result != null) {
                        startActivity(intent[3]);
                    }
                });
                iv_imagearr[4].setOnClickListener(v -> {
                    if (result != null) {
                        startActivity(intent[4]);
                    }
                });
                iv_imagearr[5].setOnClickListener(v -> {
                    if (result != null) {
                        startActivity(intent[5]);
                    }
                });
                iv_imagearr[6].setOnClickListener(v -> {
                    if (result != null) {
                        startActivity(intent[6]);
                    }
                });
                iv_imagearr[7].setOnClickListener(v -> {
                    if (result != null) {
                        startActivity(intent[7]);
                    }
                });
                iv_imagearr[8].setOnClickListener(v -> {
                    if (result != null) {
                        startActivity(intent[8]);
                    }
                });
                iv_imagearr[9].setOnClickListener(v -> {
                    if (result != null) {
                        startActivity(intent[9]);
                    }
                });
                index = 0;
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
                iv_imagearr[index].setImageBitmap(result);
                index++;
            } else {
                Log.e("DownloadFilesTask", "Bitmap is null");
            }
        }
    }

}


