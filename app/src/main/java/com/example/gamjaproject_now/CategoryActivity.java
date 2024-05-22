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

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gamjaproject_now.API.API;
import com.example.gamjaproject_now.API.APIController;
import com.example.gamjaproject_now.API.CountAPI;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity {

    int index = 0;

    ImageView[] iv_imagearr = new ImageView[10];
    TextView[] testarr = new TextView[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        testarr[0] = (TextView) findViewById(R.id.test);
        testarr[1] = (TextView) findViewById(R.id.test2);
        testarr[2] = (TextView) findViewById(R.id.test3);
        testarr[3] = (TextView) findViewById(R.id.test4);
        testarr[4] = (TextView) findViewById(R.id.test5);
        testarr[5] = (TextView) findViewById(R.id.test6);
        testarr[6] = (TextView) findViewById(R.id.test7);
        testarr[7] = (TextView) findViewById(R.id.test8);
        testarr[8] = (TextView) findViewById(R.id.test9);
        testarr[9] = (TextView) findViewById(R.id.test10);

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

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, ProgramActivity.class);
                startActivity(intent);
            }
        };


        Call<CountAPI[]> countCall = APIController.getCountCall("movie_test");
        countCall.enqueue(new Callback<CountAPI[]>() {
            @Override
            public void onResponse(Call<CountAPI[]> call, Response<CountAPI[]> response) {
                CountAPI[] result = response.body();
                Log.d("c_link", "c_link : " + result[0].getCnt());
//                int b = result[0].getCnt();
            }

            @Override
            public void onFailure(Call<CountAPI[]> call, Throwable t) {
                Log.d("결과", "실패 : " + t.getMessage());

            }
        });



        Call<API[]> call = APIController.getTestCall("movie_test", 2, 10);
        call.enqueue(new Callback<API[]>() {
            @Override
            public void onResponse(Call<API[]> call, Response<API[]> response) {
                API[] result = response.body();

                ////                if (result != null && result.length > 0) {
                ////                    Log.d("img_link", "img_link : " + result[1].getImg());
                ////                    test.append(result[1].getTitle());
                ////                    new DownloadFilesTask().execute(result[1].getImg());
                //                } else {
                //                    Log.e("API Response", "Response is null or empty");
                //                }
                for (int i = 0; i < 10; i++) {
                    Log.d("img_link", "img_link : " + result[i].getImg());
                    testarr[i].append(result[i].getTitle());

                    new CategoryActivity.DownloadFilesTask().execute(result[i].getImg());
                }
                index = 0;

            }



            @Override
            public void onFailure(Call<API[]> call, Throwable t) {
                Log.d("결과", "실패 : " + t.getMessage());
            }
        });


        testarr[0].setOnClickListener(listener);
        testarr[1].setOnClickListener(listener);
        testarr[2].setOnClickListener(listener);
        testarr[3].setOnClickListener(listener);
        testarr[4].setOnClickListener(listener);
        testarr[5].setOnClickListener(listener);
        testarr[6].setOnClickListener(listener);
        testarr[7].setOnClickListener(listener);
        testarr[8].setOnClickListener(listener);
        testarr[9].setOnClickListener(listener);


        iv_imagearr[0].setOnClickListener(listener);
        iv_imagearr[1].setOnClickListener(listener);
        iv_imagearr[2].setOnClickListener(listener);
        iv_imagearr[3].setOnClickListener(listener);
        iv_imagearr[4].setOnClickListener(listener);
        iv_imagearr[5].setOnClickListener(listener);
        iv_imagearr[6].setOnClickListener(listener);
        iv_imagearr[7].setOnClickListener(listener);
        iv_imagearr[8].setOnClickListener(listener);
        iv_imagearr[9].setOnClickListener(listener);


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


//            if (result != null) {
//                iv_image2.setImageBitmap(result);
//            }
//
//            if (result != null) {
//                iv_image3.setImageBitmap(result);
//            }
//            if (result != null) {
//                iv_image4.setImageBitmap(result);
//            }
//            if (result != null) {
//                iv_image5.setImageBitmap(result);
//            }
//            if (result != null) {
//                iv_image6.setImageBitmap(result);
//            }
//            if (result != null) {
//                iv_image7.setImageBitmap(result);
//
//            }
//            if (result != null) {
//                iv_image8.setImageBitmap(result);
//            }
//            if (result != null) {
//                iv_image9.setImageBitmap(result);
//            }
//            if (result != null) {
//                iv_image10.setImageBitmap(result);
//            }


        }
    }
}
