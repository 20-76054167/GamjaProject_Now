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
    String BW = "웹툰";
    String BM = "영화";
    String BN = "소설";

    ImageView[] iv_imagearr = new ImageView[21];
    TextView[] testArr = new TextView[21];

    Content[] result;

    String[] WebtooontableList = {"kakaowebtoon", "naverwebtoon"};
    String[] MovietableList = {"couplay", "netflix", "watcha"};
    String[] NoveltableList = {"kpnovel"};

    Random randT = new Random();
    int b = 4;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        String CategoryRandomTableWebtoon = WebtooontableList[randT.nextInt(2)];
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


        Call<Count[]> countCall = APIController.getCountCall("kakaowebtoon");
        countCall.enqueue(new Callback<Count[]>() {
            @Override
            public void onResponse(Call<Count[]> call, Response<Count[]> response) {
                Count[] result = response.body();
                Log.d("Count", "Count : " + result[0].getCnt());
//                b = result[0].getCnt();
            }

            @Override
            public void onFailure(Call<Count[]> call, Throwable t) {
                Log.d("결과", "실패 : " + t.getMessage());

            }
        });


        if((message.equals(BW))) {
            fetchDataFromApi(CategoryRandomTableWebtoon, (int)(Math.random()*b)+1, 21);
        }else if((message.equals(BN))){
            fetchDataFromApi(CategoryRandomTableNovel, (int)(Math.random()*b)+1, 21);
        }else{
            fetchDataFromApi(CategoryRandomTableMovie, (int)(Math.random()*b)+1, 21);
        }




//        testarr[0].setOnClickListener(listener);
//        testarr[1].setOnClickListener(listener);
//        testarr[2].setOnClickListener(listener);
//        testarr[3].setOnClickListener(listener);
//        testarr[4].setOnClickListener(listener);
//        testarr[5].setOnClickListener(listener);
//        testarr[6].setOnClickListener(listener);
//        testarr[7].setOnClickListener(listener);
//        testarr[8].setOnClickListener(listener);
//        testarr[9].setOnClickListener(listener);
//
//
//        iv_imagearr[0].setOnClickListener(listener);
//        iv_imagearr[1].setOnClickListener(listener);
//        iv_imagearr[2].setOnClickListener(listener);
//        iv_imagearr[3].setOnClickListener(listener);
//        iv_imagearr[4].setOnClickListener(listener);
//        iv_imagearr[5].setOnClickListener(listener);
//        iv_imagearr[6].setOnClickListener(listener);
//        iv_imagearr[7].setOnClickListener(listener);
//        iv_imagearr[8].setOnClickListener(listener);
//        iv_imagearr[9].setOnClickListener(listener);


    }
    private void fetchDataFromApi(String tableList, int page, int pagingUnit) {
        Call<Content[]> call = APIController.getTestCall(tableList, page, pagingUnit);
        call.enqueue(new Callback<Content[]>() {
            Intent[] intent = new Intent[pagingUnit];
            @Override
            public void onResponse(Call<Content[]> call, Response<Content[]> response) {
                if (response.isSuccessful()) {
                    result = response.body();
                    for(All = 0; All<pagingUnit; All++) {
//                        Log.d("img_link", "img_link : " + result[i].getImg());

                        testArr[All].append(result[All].getTitle());
                        new CategoryActivity.DownloadFilesTask().execute(result[All].getImg());

                        intent[All] = new Intent(CategoryActivity.this, ProgramActivity.class);
                        intent[All].putExtra("title", result[All].getTitle());
                        intent[All].putExtra("director", result[All].getDirector());
                        intent[All].putExtra("description", result[All].getDescription());
                        intent[All].putExtra("image", result[All].getImg());
                        intent[All].putExtra("id", result[All].getId());
                        intent[All].putExtra("actor", result[All].getActor());
                        intent[All].putExtra("tableName", tableList);

                    }

                }else{
                    Toast.makeText(CategoryActivity.this, "API call failed", Toast.LENGTH_SHORT).show();
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
                testArr[10].setOnClickListener(v -> {
                    if (result != null) {
                        startActivity(intent[10]);
                    }
                });
                testArr[11].setOnClickListener(v -> {
                    if (result != null) {
                        startActivity(intent[11]);
                    }
                });
                testArr[12].setOnClickListener(v -> {
                    if (result != null) {
                        startActivity(intent[12]);
                    }
                });
                testArr[13].setOnClickListener(v -> {
                    if (result != null) {
                        startActivity(intent[13]);
                    }
                });
                testArr[14].setOnClickListener(v -> {
                    if (result != null) {
                        startActivity(intent[14]);
                    }
                });
                testArr[15].setOnClickListener(v -> {
                    if (result != null) {
                        startActivity(intent[15]);
                    }
                });
                testArr[16].setOnClickListener(v -> {
                    if (result != null) {
                        startActivity(intent[16]);
                    }
                });
                testArr[17].setOnClickListener(v -> {
                    if (result != null) {
                        startActivity(intent[17]);
                    }
                });
                testArr[18].setOnClickListener(v -> {
                    if (result != null) {
                        startActivity(intent[18]);
                    }
                });
                testArr[19].setOnClickListener(v -> {
                    if (result != null) {
                        startActivity(intent[19]);
                    }
                });
                testArr[20].setOnClickListener(v ->{
                    if (result != null){
                        startActivity(intent[20]);
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
                iv_imagearr[10].setOnClickListener(v -> {
                    if (result != null) {
                        startActivity(intent[10]);
                    }
                });
                iv_imagearr[11].setOnClickListener(v -> {
                    if (result != null) {
                        startActivity(intent[11]);
                    }
                });
                iv_imagearr[12].setOnClickListener(v -> {
                    if (result != null) {
                        startActivity(intent[12]);
                    }
                });
                iv_imagearr[13].setOnClickListener(v -> {
                    if (result != null) {
                        startActivity(intent[13]);
                    }
                });
                iv_imagearr[14].setOnClickListener(v -> {
                    if (result != null) {
                        startActivity(intent[14]);
                    }
                });
                iv_imagearr[15].setOnClickListener(v -> {
                    if (result != null) {
                        startActivity(intent[15]);
                    }
                });
                iv_imagearr[16].setOnClickListener(v -> {
                    if (result != null) {
                        startActivity(intent[16]);
                    }
                });
                iv_imagearr[17].setOnClickListener(v -> {
                    if (result != null) {
                        startActivity(intent[17]);
                    }
                });
                iv_imagearr[18].setOnClickListener(v -> {
                    if (result != null) {
                        startActivity(intent[18]);
                    }
                });
                iv_imagearr[19].setOnClickListener(v -> {
                    if (result != null) {
                        startActivity(intent[19]);
                    }
                });
                iv_imagearr[20].setOnClickListener(v ->{
                    if (result != null){
                        startActivity(intent[20]);
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
            Log.d("String", Arrays.toString(strings));
            Bitmap bmp = null;
            try {
                String img_url = strings[0];
                URL url = new URL(img_url);
                Log.d("url", url.toString());
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
