package com.example.gamjaproject_now;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gamjaproject_now.API.Content;
import com.example.gamjaproject_now.API.APIController;
import com.example.gamjaproject_now.API.Count;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    int index = 0;
    int imgIndex = 0;
    int page = 1;
    int pagingUnit = 21;


    int P;
    int i;
    int contentIndex;

    int All;

    private Content[] result;
    private Count[] resultP;
    String[] tableList = {"couplay", "kpwebtoon", "kpnovel", "naverwebtoon", "netflix", "watcha"};

    int tC;

    Random rand = new Random();
    int PageRandom;
    String tableName;


    ImageView[] iv_imagearr = new ImageView[pagingUnit];
    TextView[] testArr = new TextView[pagingUnit];
    Button[] buttonTag = new Button[3];

    //    private API data;
    Intent[] intent = new Intent[pagingUnit];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ImageView DICE = (ImageView) findViewById(R.id.dice);
        ImageView LOGO = (ImageView) findViewById(R.id.logo);
        // Setup button click listeners
        buttonTag[0] = (Button) findViewById(R.id.button_webtoon);
        buttonTag[1] = (Button) findViewById(R.id.button_movie);
        buttonTag[2] = (Button) findViewById(R.id.button_book);


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
        testArr[12] = (TextView) findViewById(R.id.test13);
        testArr[13] = (TextView) findViewById(R.id.test14);
        testArr[14] = (TextView) findViewById(R.id.test15);
        testArr[15] = (TextView) findViewById(R.id.test16);
        testArr[16] = (TextView) findViewById(R.id.test17);
        testArr[17] = (TextView) findViewById(R.id.test18);
        testArr[18] = (TextView) findViewById(R.id.test19);
        testArr[19] = (TextView) findViewById(R.id.test20);
        testArr[20] = (TextView) findViewById(R.id.test21);

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
        iv_imagearr[12] = (ImageView) findViewById(R.id.imageView13);
        iv_imagearr[13] = (ImageView) findViewById(R.id.imageView14);
        iv_imagearr[14] = (ImageView) findViewById(R.id.imageView15);
        iv_imagearr[15] = (ImageView) findViewById(R.id.imageView16);
        iv_imagearr[16] = (ImageView) findViewById(R.id.imageView17);
        iv_imagearr[17] = (ImageView) findViewById(R.id.imageView18);
        iv_imagearr[18] = (ImageView) findViewById(R.id.imageView19);
        iv_imagearr[19] = (ImageView) findViewById(R.id.imageView20);
        iv_imagearr[20] = (ImageView) findViewById(R.id.imageView21);


        View.OnClickListener dice = new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentD = new Intent(MainActivity.this, RandomActivity.class);
                startActivity(intentD);
            }
        };

        View.OnClickListener reroll = new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentR = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intentR);
                finish();
            }
        };
        Button.OnClickListener buttonTagmove = new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentC = new Intent(MainActivity.this, CategoryActivity.class);
                String buttonText = buttonTag[0].getText().toString();
                intentC.putExtra("buttonText", buttonText);
                startActivity(intentC);
            }
        };
        Button.OnClickListener buttonTagmove2 = new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentC = new Intent(MainActivity.this, CategoryActivity.class);
                String buttonText = buttonTag[1].getText().toString();
                intentC.putExtra("buttonText", buttonText);
                startActivity(intentC);
            }
        };
        Button.OnClickListener buttonTagmove3 = new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentC = new Intent(MainActivity.this, CategoryActivity.class);
                String buttonText = buttonTag[2].getText().toString();
                intentC.putExtra("buttonText", buttonText);
                startActivity(intentC);
            }
        };
        DICE.setOnClickListener(dice);
        LOGO.setOnClickListener(reroll);

        buttonTag[0].setOnClickListener(buttonTagmove);
        buttonTag[1].setOnClickListener(buttonTagmove2);
        buttonTag[2].setOnClickListener(buttonTagmove3);
//        couplay137, kakaowebtoon224, kpnovel4816, naverwebtoon2982, netflix305, watcha200;

        fetchDataFromApi();
    }

    private void fetchDataFromApi() {
        for (All = 0; All < pagingUnit; All++) {
            String tableName = tableList[rand.nextInt(tableList.length)];
            Call<Count[]> countCall = APIController.getCountCall(tableName);
            int CurrentIndex = All;
            countCall.enqueue(new Callback<Count[]>() {
                @Override
                public void onResponse(Call<Count[]> countCall, Response<Count[]> response) {
                    if (response.isSuccessful()) {
                        Count[] tableCounts = response.body();
                        tC = tableCounts[0].getCnt();

                        fetchContentDataFromApi(tableName, CurrentIndex);
                    }
                }

                @Override
                public void onFailure(Call<Count[]> countCall, Throwable t) {

                }
            });

        }
    }

    private void fetchContentDataFromApi(String tableName, int CurrentIndex) {
        if (tableName.equals(tableList[0])) {
            PageRandom = (int) (Math.random() * tC) + 1; //couplay
            Log.d("couplayCount", String.valueOf(tC));
        } else if (tableName.equals(tableList[1])) {
            PageRandom = (int) (Math.random() * tC) + 1; //kpwebtoon
            Log.d("kakaowebtoonCount", String.valueOf(tC));
        } else if (tableName.equals(tableList[2])) {
            PageRandom = (int) (Math.random() * tC) + 1; //kpnovel
            Log.d("kpnovelCount", String.valueOf(tC));
        } else if (tableName.equals(tableList[3])) {
            PageRandom = (int) (Math.random() * tC) + 1; //naverwebtoon
            Log.d("naverwebtoonCount", String.valueOf(tC));
        } else if (tableName.equals(tableList[4])) {
            PageRandom = (int) (Math.random() * tC) + 1; // netflix
            Log.d("netflixCount", String.valueOf(tC));
        } else if (tableName.equals(tableList[5])) {
            PageRandom = (int) (Math.random() * tC) + 1; //watcha
            Log.d("watchaCount", String.valueOf(tC));
        }

        Call<Content[]> call = APIController.getTestCall(tableName, PageRandom, 1);
        call.enqueue(new Callback<Content[]>() {
            @Override
            public void onResponse(Call<Content[]> call, Response<Content[]> response) {
                if (response.isSuccessful()) {
                    result = response.body();

                    if (CurrentIndex < pagingUnit && result != null && result.length > 0) {
                        Content content = result[0];

                        testArr[CurrentIndex].append(content.getTitle());
                        new DownloadFilesTask(CurrentIndex).execute(content.getImg());

                        Intent intent = new Intent(MainActivity.this, ProgramActivity.class);
                        intent.putExtra("tableName", tableName);
                        intent.putExtra("id", content.getId());
                        intent.putExtra("title", content.getTitle());
                        intent.putExtra("director", content.getDirector());
                        intent.putExtra("description", content.getDescription());
                        intent.putExtra("image", content.getImg());
                        intent.putExtra("actor", content.getActor());
                        intent.putExtra("url", content.getUrl());
                        Log.d("URL", content.getUrl());


                        testArr[CurrentIndex].setOnClickListener(v ->
                                startActivity(intent));
                        iv_imagearr[CurrentIndex].setOnClickListener(v ->
                                startActivity(intent));
                    } else {
                        Log.e("API Response", "Invalid data or index out of bounds");
                    }
                } else {
                    Toast.makeText(MainActivity.this, "API call failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Content[]> call, Throwable t) {
                Log.d("결과", "실패 : " + t.getMessage());
            }
        });
    }


//    private void fetchDataFromApi() {
//        for (All = 0; All < pagingUnit; All++) {
//            String tableName = tableList[rand.nextInt(tableList.length)];
//            if (tableName.equals(tableList[0])) {
//                PageRandom = (int) (Math.random() * 137) + 1; //couplay
//            } else if (tableName.equals(tableList[1])) {
//                PageRandom = (int) (Math.random() * 224) + 1; //kakaowebtoon
//            } else if (tableName.equals(tableList[2])) {
//                PageRandom = (int) (Math.random() * 4816) + 1; //kpnovel
//            } else if (tableName.equals(tableList[3])) {
//                PageRandom = (int) (Math.random() * 2982) + 1; //naverwebtoon
//            } else if (tableName.equals(tableList[4])) {
//                PageRandom = (int) (Math.random() * 305) + 1; // netflix
//            } else if (tableName.equals(tableList[5])) {
//                PageRandom = (int) (Math.random() * 200) + 1; //watcha
//            }
//            int CurrentIndex = All;
//            Call<Content[]> call = APIController.getTestCall(tableName, PageRandom, 1);
//            call.enqueue(new Callback<Content[]>() {
//                @Override
//                public void onResponse(Call<Content[]> call, Response<Content[]> response) {
//                    if (response.isSuccessful()) {
//                        result = response.body();
//
//                        if (CurrentIndex < pagingUnit && result != null && result.length > 0) {
//                            Content content = result[0];
//
//                            testArr[CurrentIndex].append(content.getTitle());
//                            new DownloadFilesTask(CurrentIndex).execute(content.getImg());
//
//                            Intent intent = new Intent(MainActivity.this, ProgramActivity.class);
//                            intent.putExtra("tableName", tableName);
//                            intent.putExtra("id", content.getId());
//                            intent.putExtra("title", content.getTitle());
//                            intent.putExtra("director", content.getDirector());
//                            intent.putExtra("description", content.getDescription());
//                            intent.putExtra("image", content.getImg());
//                            intent.putExtra("actor", content.getActor());
//                            intent.putExtra("url", content.getUrl());
//                            Log.d("URL", content.getUrl());
//
//
//                            testArr[CurrentIndex].setOnClickListener(v ->
//                                    startActivity(intent));
//                            iv_imagearr[CurrentIndex].setOnClickListener(v ->
//                                    startActivity(intent));
//                        } else {
//                            Log.e("API Response", "Invalid data or index out of bounds");
//                        }
////                        Log.d("img_link", "img_link : " + result[i].getImg());
////                            testArr[All].append(result[PageRandom].getTitle());
////                            new DownloadFilesTask().execute(result[PageRandom].getImg());
////                            intent[All] = new Intent(MainActivity.this, ProgramActivity.class);
////                            Log.d("mid", String.valueOf(result[PageRandom].getId()));
////                            intent[All].putExtra("tableName", tableList);
////                            intent[All].putExtra("id", result[PageRandom].getId());
////                            intent[All].putExtra("title", result[PageRandom].getTitle());
////                            intent[All].putExtra("director", result[PageRandom].getDirector());
////                            intent[All].putExtra("description", result[PageRandom].getDescription());
////                            intent[All].putExtra("image", result[PageRandom].getImg());
////                            intent[All].putExtra("actor", result[PageRandom].getActor());
////                        }
//                    } else {
//                        Toast.makeText(MainActivity.this, "API call failed", Toast.LENGTH_SHORT).show();
//                    }
//                }
//                @Override
//                public void onFailure(Call<Content[]> call, Throwable t) {
//                    Log.d("결과", "실패 : " + t.getMessage());
//                }
//            });
//        }
//    }

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
        protected void onPostExecute(Bitmap result) {
            if (result != null) {
                iv_imagearr[index].setImageBitmap(result);
            } else {
                Log.e("DownloadFilesTask", "Bitmap is null");
            }
        }
    }
}
