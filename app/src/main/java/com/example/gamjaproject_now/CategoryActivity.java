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
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gamjaproject_now.API.Content;
import com.example.gamjaproject_now.API.APIController;
import com.example.gamjaproject_now.API.Count;

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

public class CategoryActivity extends AppCompatActivity {

    int index = 0;
    int All;
    String BW = "WEBTOONS";
    String BM = "영화";
    String BN = "NOVELS";

    ImageView[] iv_imagearr = new ImageView[21];
    TextView[] testArr = new TextView[21];

    Content[] result;
    String[] tableList = {"couplay", "kakaowebtoon", "kpnovel", "naverwebtoon", "netflix", "watcha"};

    String[] WebtoontableList = {"kakaowebtoon", "naverwebtoon"};
    String[] MovietableList = {"couplay", "netflix", "watcha"};
    String[] NoveltableList = {"kpnovel"};

    Random randT = new Random();
    int PageRandom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        String CategoryRandomTableWebtoon = WebtoontableList[randT.nextInt(2)];
        String CategoryRandomTableMovie = MovietableList[randT.nextInt(3)];
        String CategoryRandomTableNovel = NoveltableList[randT.nextInt(1)];

        Intent intent = getIntent();


        String message = intent.getStringExtra("buttonText");
        TextView category = (TextView) findViewById(R.id.categoryText);
        category.setText(message);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ImageView LOGO = (ImageView) findViewById(R.id.logo);

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

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, ProgramActivity.class);
                startActivity(intent);
            }
        };

        View.OnClickListener reroll = new View.OnClickListener(){
            public void onClick(View v){
                Intent intentR = new Intent(CategoryActivity.this, MainActivity.class);
                startActivity(intentR);
                finish();
            }
        };


        LOGO.setOnClickListener(reroll);


        if ((message.equals(BW))) { //9, 30
            fetchWebtoonDataFromApi();
//            if (CategoryRandomTableWebtoon.equals(WebtooontableList[0])) {
//                fetchDataFromApi(CategoryRandomTableWebtoon, (int) (Math.random() * 9) + 1, 21);
//            } else if (CategoryRandomTableWebtoon.equals(WebtooontableList[1])) {
//                fetchDataFromApi(CategoryRandomTableWebtoon, (int) (Math.random() * 30) + 1, 21);
//            }
        } else if ((message.equals(BN))) { //200
            fetchNovelDataFromApi();
//            if (CategoryRandomTableNovel.equals(NoveltableList[0])) {
//                fetchDataFromApi(CategoryRandomTableNovel, (int) (Math.random() * 200) + 1, 21);
//            }
        } else { // 5, 14, 8
            fetchMovieDataFromApi();
//            if (CategoryRandomTableMovie.equals(MovietableList[0])) {
//                fetchDataFromApi(CategoryRandomTableMovie, (int) (Math.random() * 5) + 1, 21);
//            } else if (CategoryRandomTableMovie.equals(MovietableList[1])) {
//                fetchDataFromApi(CategoryRandomTableMovie, (int) (Math.random() * 14) + 1, 21);
//            } else if (CategoryRandomTableMovie.equals(MovietableList[2])) {
//                fetchDataFromApi(CategoryRandomTableMovie, (int) (Math.random() * 8) + 1, 21);
//            }
        }



    }

    private void fetchWebtoonDataFromApi() {
        for (All = 0; All < testArr.length; All++) {
            String webtoontableName = WebtoontableList[randT.nextInt(WebtoontableList.length)];
            if (webtoontableName.equals(tableList[0])) {
                PageRandom = (int) (Math.random() * 137) + 1;
            } else if (webtoontableName.equals(tableList[1])) {
                PageRandom = (int) (Math.random() * 224) + 1;
            } else if (webtoontableName.equals(tableList[2])) {
                PageRandom = (int) (Math.random() * 4816) + 1;
            } else if (webtoontableName.equals(tableList[3])) {
                PageRandom = (int) (Math.random() * 657) + 1;
            } else if (webtoontableName.equals(tableList[4])) {
                PageRandom = (int) (Math.random() * 305) + 1;
            } else if (webtoontableName.equals(tableList[5])) {
                PageRandom = (int) (Math.random() * 200) + 1;
            }
            int CurrentIndex = All;
            Call<Content[]> call = APIController.getTestCall(webtoontableName, PageRandom, 1);
            call.enqueue(new Callback<Content[]>() {
                @Override
                public void onResponse(Call<Content[]> call, Response<Content[]> response) {
                    if (response.isSuccessful()) {
                        result = response.body();

                        if (CurrentIndex < testArr.length && result != null && result.length > 0) {
                            Content content = result[0];

                            testArr[CurrentIndex].append(content.getTitle());
                            new CategoryActivity.DownloadFilesTask(CurrentIndex).execute(content.getImg());
                            Intent intent = new Intent(CategoryActivity.this, ProgramActivity.class);
                            intent.putExtra("tableName", webtoontableName);
                            intent.putExtra("id", content.getId());
                            intent.putExtra("title", content.getTitle());
                            intent.putExtra("director", content.getDirector());
                            intent.putExtra("description", content.getDescription());
                            intent.putExtra("image", content.getImg());
                            intent.putExtra("actor", content.getActor());

                            testArr[CurrentIndex].setOnClickListener(v ->
                                    startActivity(intent));
                            iv_imagearr[CurrentIndex].setOnClickListener(v ->
                                    startActivity(intent));
                        } else {
                            Log.e("API Response", "Invalid data or index out of bounds");
                        }
                    } else {
                        Toast.makeText(CategoryActivity.this, "API call failed", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<Content[]> call, Throwable t) {
                    Log.d("결과", "실패 : " + t.getMessage());
                }
            });

        }
    }

    private void fetchNovelDataFromApi() {
        for (All = 0; All < testArr.length; All++) {
            String noveltableName = NoveltableList[randT.nextInt(NoveltableList.length)];
            if (noveltableName.equals(tableList[0])) {
                PageRandom = (int) (Math.random() * 137) + 1;
            } else if (noveltableName.equals(tableList[1])) {
                PageRandom = (int) (Math.random() * 224) + 1;
            } else if (noveltableName.equals(tableList[2])) {
                PageRandom = (int) (Math.random() * 4816) + 1;
            } else if (noveltableName.equals(tableList[3])) {
                PageRandom = (int) (Math.random() * 657) + 1;
            } else if (noveltableName.equals(tableList[4])) {
                PageRandom = (int) (Math.random() * 305) + 1;
            } else if (noveltableName.equals(tableList[5])) {
                PageRandom = (int) (Math.random() * 200) + 1;
            }
            int CurrentIndex = All;
            Call<Content[]> call = APIController.getTestCall(noveltableName, PageRandom, 1);
            call.enqueue(new Callback<Content[]>() {
                @Override
                public void onResponse(Call<Content[]> call, Response<Content[]> response) {
                    if (response.isSuccessful()) {
                        result = response.body();

                        if (CurrentIndex < testArr.length && result != null && result.length > 0) {
                            Content content = result[0];

                            testArr[CurrentIndex].append(content.getTitle());
                            new CategoryActivity.DownloadFilesTask(CurrentIndex).execute(content.getImg());
                            Intent intent = new Intent(CategoryActivity.this, ProgramActivity.class);
                            intent.putExtra("tableName", noveltableName);
                            intent.putExtra("id", content.getId());
                            intent.putExtra("title", content.getTitle());
                            intent.putExtra("director", content.getDirector());
                            intent.putExtra("description", content.getDescription());
                            intent.putExtra("image", content.getImg());
                            intent.putExtra("actor", content.getActor());

                            testArr[CurrentIndex].setOnClickListener(v ->
                                    startActivity(intent));
                            iv_imagearr[CurrentIndex].setOnClickListener(v ->
                                    startActivity(intent));
                        } else {
                            Log.e("API Response", "Invalid data or index out of bounds");
                        }
                    } else {
                        Toast.makeText(CategoryActivity.this, "API call failed", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<Content[]> call, Throwable t) {
                    Log.d("결과", "실패 : " + t.getMessage());
                }
            });

        }
    }

    private void fetchMovieDataFromApi() {
        for (All = 0; All < testArr.length; All++) {
            String movietableName = MovietableList[randT.nextInt(MovietableList.length)];
            if (movietableName.equals(tableList[0])) {
                PageRandom = (int) (Math.random() * 137) + 1;
            } else if (movietableName.equals(tableList[1])) {
                PageRandom = (int) (Math.random() * 224) + 1;
            } else if (movietableName.equals(tableList[2])) {
                PageRandom = (int) (Math.random() * 4816) + 1;
            } else if (movietableName.equals(tableList[3])) {
                PageRandom = (int) (Math.random() * 657) + 1;
            } else if (movietableName.equals(tableList[4])) {
                PageRandom = (int) (Math.random() * 305) + 1;
            } else if (movietableName.equals(tableList[5])) {
                PageRandom = (int) (Math.random() * 200) + 1;
            }
            int CurrentIndex = All;
            Call<Content[]> call = APIController.getTestCall(movietableName, PageRandom, 1);
            call.enqueue(new Callback<Content[]>() {
                @Override
                public void onResponse(Call<Content[]> call, Response<Content[]> response) {
                    if (response.isSuccessful()) {
                        result = response.body();

                        if (CurrentIndex < testArr.length && result != null && result.length > 0) {
                            Content content = result[0];

                            testArr[CurrentIndex].append(content.getTitle());
                            new CategoryActivity.DownloadFilesTask(CurrentIndex).execute(content.getImg());
                            Intent intent = new Intent(CategoryActivity.this, ProgramActivity.class);
                            intent.putExtra("tableName", movietableName);
                            intent.putExtra("id", content.getId());
                            intent.putExtra("title", content.getTitle());
                            intent.putExtra("director", content.getDirector());
                            intent.putExtra("description", content.getDescription());
                            intent.putExtra("image", content.getImg());
                            intent.putExtra("actor", content.getActor());
                            intent.putExtra("url", content.getUrl());

                            testArr[CurrentIndex].setOnClickListener(v ->
                                    startActivity(intent));
                            iv_imagearr[CurrentIndex].setOnClickListener(v ->
                                    startActivity(intent));
                        } else {
                            Log.e("API Response", "Invalid data or index out of bounds");
                        }
                    } else {
                        Toast.makeText(CategoryActivity.this, "API call failed", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<Content[]> call, Throwable t) {
                    Log.d("결과", "실패 : " + t.getMessage());
                }
            });

        }
    }









//    private void fetchDataFromApi(String tableList, int page, int pagingUnit) {
//        Call<Content[]> call = APIController.getTestCall(tableList, page, pagingUnit);
//        call.enqueue(new Callback<Content[]>() {
//            Intent[] intent = new Intent[pagingUnit];
//
//            @Override
//            public void onResponse(Call<Content[]> call, Response<Content[]> response) {
//                if (response.isSuccessful()) {
//                    result = response.body();
//                    for (All = 0; All < pagingUnit; All++) {
////                        Log.d("img_link", "img_link : " + result[i].getImg());
//
//                        testArr[All].append(result[All].getTitle());
//                        new CategoryActivity.DownloadFilesTask().execute(result[All].getImg());
//
//                        intent[All] = new Intent(CategoryActivity.this, ProgramActivity.class);
//                        intent[All].putExtra("title", result[All].getTitle());
//                        intent[All].putExtra("director", result[All].getDirector());
//                        intent[All].putExtra("description", result[All].getDescription());
//                        intent[All].putExtra("image", result[All].getImg());
//                        intent[All].putExtra("id", result[All].getId());
//                        intent[All].putExtra("actor", result[All].getActor());
//                        intent[All].putExtra("tableName", tableList);
//
//                    }
//
//                } else {
//                    Toast.makeText(CategoryActivity.this, "API call failed", Toast.LENGTH_SHORT).show();
//                }
//
//
//                testArr[0].setOnClickListener(v -> {
//                    if (result != null) {
//                        startActivity(intent[0]);
//                    }
//                });
//                testArr[1].setOnClickListener(v -> {
//                    if (result != null) {
//                        startActivity(intent[1]);
//                    }
//                });
//                testArr[2].setOnClickListener(v -> {
//                    if (result != null) {
//                        startActivity(intent[2]);
//                    }
//                });
//                testArr[3].setOnClickListener(v -> {
//                    if (result != null) {
//                        startActivity(intent[3]);
//                    }
//                });
//                testArr[4].setOnClickListener(v -> {
//                    if (result != null) {
//                        startActivity(intent[4]);
//                    }
//                });
//                testArr[5].setOnClickListener(v -> {
//                    if (result != null) {
//                        startActivity(intent[5]);
//                    }
//                });
//                testArr[6].setOnClickListener(v -> {
//                    if (result != null) {
//                        startActivity(intent[6]);
//                    }
//                });
//                testArr[7].setOnClickListener(v -> {
//                    if (result != null) {
//                        startActivity(intent[7]);
//                    }
//                });
//                testArr[8].setOnClickListener(v -> {
//                    if (result != null) {
//                        startActivity(intent[8]);
//                    }
//                });
//                testArr[9].setOnClickListener(v -> {
//                    if (result != null) {
//                        startActivity(intent[9]);
//                    }
//                });
//                testArr[10].setOnClickListener(v -> {
//                    if (result != null) {
//                        startActivity(intent[10]);
//                    }
//                });
//                testArr[11].setOnClickListener(v -> {
//                    if (result != null) {
//                        startActivity(intent[11]);
//                    }
//                });
//                testArr[12].setOnClickListener(v -> {
//                    if (result != null) {
//                        startActivity(intent[12]);
//                    }
//                });
//                testArr[13].setOnClickListener(v -> {
//                    if (result != null) {
//                        startActivity(intent[13]);
//                    }
//                });
//                testArr[14].setOnClickListener(v -> {
//                    if (result != null) {
//                        startActivity(intent[14]);
//                    }
//                });
//                testArr[15].setOnClickListener(v -> {
//                    if (result != null) {
//                        startActivity(intent[15]);
//                    }
//                });
//                testArr[16].setOnClickListener(v -> {
//                    if (result != null) {
//                        startActivity(intent[16]);
//                    }
//                });
//                testArr[17].setOnClickListener(v -> {
//                    if (result != null) {
//                        startActivity(intent[17]);
//                    }
//                });
//                testArr[18].setOnClickListener(v -> {
//                    if (result != null) {
//                        startActivity(intent[18]);
//                    }
//                });
//                testArr[19].setOnClickListener(v -> {
//                    if (result != null) {
//                        startActivity(intent[19]);
//                    }
//                });
//                testArr[20].setOnClickListener(v -> {
//                    if (result != null) {
//                        startActivity(intent[20]);
//                    }
//                });
//                iv_imagearr[0].setOnClickListener(v -> {
//                    if (result != null) {
//                        startActivity(intent[0]);
//                    }
//                });
//
//                iv_imagearr[1].setOnClickListener(v -> {
//                    if (result != null) {
//                        startActivity(intent[1]);
//                    }
//                });
//                iv_imagearr[2].setOnClickListener(v -> {
//                    if (result != null) {
//                        startActivity(intent[2]);
//                    }
//                });
//                iv_imagearr[3].setOnClickListener(v -> {
//                    if (result != null) {
//                        startActivity(intent[3]);
//                    }
//                });
//                iv_imagearr[4].setOnClickListener(v -> {
//                    if (result != null) {
//                        startActivity(intent[4]);
//                    }
//                });
//                iv_imagearr[5].setOnClickListener(v -> {
//                    if (result != null) {
//                        startActivity(intent[5]);
//                    }
//                });
//                iv_imagearr[6].setOnClickListener(v -> {
//                    if (result != null) {
//                        startActivity(intent[6]);
//                    }
//                });
//                iv_imagearr[7].setOnClickListener(v -> {
//                    if (result != null) {
//                        startActivity(intent[7]);
//                    }
//                });
//                iv_imagearr[8].setOnClickListener(v -> {
//                    if (result != null) {
//                        startActivity(intent[8]);
//                    }
//                });
//                iv_imagearr[9].setOnClickListener(v -> {
//                    if (result != null) {
//                        startActivity(intent[9]);
//                    }
//                });
//                iv_imagearr[10].setOnClickListener(v -> {
//                    if (result != null) {
//                        startActivity(intent[10]);
//                    }
//                });
//                iv_imagearr[11].setOnClickListener(v -> {
//                    if (result != null) {
//                        startActivity(intent[11]);
//                    }
//                });
//                iv_imagearr[12].setOnClickListener(v -> {
//                    if (result != null) {
//                        startActivity(intent[12]);
//                    }
//                });
//                iv_imagearr[13].setOnClickListener(v -> {
//                    if (result != null) {
//                        startActivity(intent[13]);
//                    }
//                });
//                iv_imagearr[14].setOnClickListener(v -> {
//                    if (result != null) {
//                        startActivity(intent[14]);
//                    }
//                });
//                iv_imagearr[15].setOnClickListener(v -> {
//                    if (result != null) {
//                        startActivity(intent[15]);
//                    }
//                });
//                iv_imagearr[16].setOnClickListener(v -> {
//                    if (result != null) {
//                        startActivity(intent[16]);
//                    }
//                });
//                iv_imagearr[17].setOnClickListener(v -> {
//                    if (result != null) {
//                        startActivity(intent[17]);
//                    }
//                });
//                iv_imagearr[18].setOnClickListener(v -> {
//                    if (result != null) {
//                        startActivity(intent[18]);
//                    }
//                });
//                iv_imagearr[19].setOnClickListener(v -> {
//                    if (result != null) {
//                        startActivity(intent[19]);
//                    }
//                });
//                iv_imagearr[20].setOnClickListener(v -> {
//                    if (result != null) {
//                        startActivity(intent[20]);
//                    }
//                });
//                index = 0;
//            }
//
//            @Override
//            public void onFailure(Call<Content[]> call, Throwable t) {
//                Log.d("결과", "실패 : " + t.getMessage());
//            }
//        });
//
//
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
                iv_imagearr[index].setImageBitmap(result);
            } else {
                Log.e("DownloadFilesTask", "Bitmap is null");
            }


        }
    }
}
