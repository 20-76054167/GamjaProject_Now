//package com.example.gamjaproject_now;
//
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//import com.example.gamjaproject_now.API.API;
//import com.example.gamjaproject_now.API.APIController;
//
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.net.URL;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class MainActivity extends AppCompatActivity {
//    ImageView iv_image;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main);
//
//        // Setup button click listeners
//        Button buttonWebtoon = findViewById(R.id.button_webtoon);
//        Button buttonMovie = findViewById(R.id.button_movie);
//        Button buttonBook = findViewById(R.id.button_book);
//
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//
//        TextView test = (TextView) findViewById(R.id.test);
//
//        iv_image = (ImageView) findViewById(R.id.imageView);
//
//        Call<API[]> call = APIController.getTestCall("movie_test",4, 3);
//        call.enqueue(new Callback<API[]>() {
//            @Override
//            public void onResponse(Call<API[]> call, Response<API[]> response) {
//                API[] result = response.body();
////                for(int i = 0; i < result.length; i++){
////                    test.append(result[i].toString());
////                    Log.d("결과", "성공 : " + result[i].toString());
////                }
//                Log.d("img_link", "img_link : " + result[1].getImg());
//                test.append(result[1].getTitle());
//                new DownloadFilesTask().execute(result[1].getImg());
//            }
//
//            @Override
//            public void onFailure(Call<API[]> call, Throwable t) {
//                Log.d("결과", "실패 : " + t.getMessage());
//            }
//        });
//
//        buttonWebtoon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, WebtoonActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        buttonMovie.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, MovieActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        buttonBook.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, BookActivity.class);
//                startActivity(intent);
//            }
//        });
//    }
//    private class DownloadFilesTask extends AsyncTask<String,Void, Bitmap> {
//        @Override
//        protected Bitmap doInBackground(String... strings) {
//            Bitmap bmp = null;
//            try {
//                String img_url = strings[0]; //url of the image
//                URL url = new URL(img_url);
//                bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return bmp;
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//
//        @Override
//        protected void onPostExecute(Bitmap result) {
//            // doInBackground 에서 받아온 total 값 사용 장소
//            iv_image.setImageBitmap(result);
//        }
//    }
//}


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

import androidx.activity.EdgeToEdge;
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
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;




public class MainActivity extends AppCompatActivity {
    int index = 0;


//    ImageView iv_image;
//    ImageView iv_image2;
//    ImageView iv_image3;
//    ImageView iv_image4;
//    ImageView iv_image5;
//    ImageView iv_image6;
//    ImageView iv_image7;
//    ImageView iv_image8;
//    ImageView iv_image9;
//
//    ImageView iv_image10;
    ImageView[] iv_imagearr = new ImageView[10];
    TextView[] testarr = new TextView[10];

    Button[] buttonTag = new Button[3];

    Intent intent = new Intent(MainActivity.this, ProgramActivity.class);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);






        // Setup button click listeners
        buttonTag[0] = findViewById(R.id.button_webtoon);
        buttonTag[1] = findViewById(R.id.button_movie);
        buttonTag[2] = findViewById(R.id.button_book);


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



//        TextView test = (TextView) findViewById(R.id.test);
//        iv_image = (ImageView) findViewById(R.id.imageView);
//        TextView test2 = (TextView) findViewById(R.id.test2);
//        iv_image2 = (ImageView) findViewById(R.id.imageView2);
//        TextView test3 = (TextView) findViewById(R.id.test3);
//        iv_image3 = (ImageView) findViewById(R.id.imageView3);
//        TextView test4 = (TextView) findViewById(R.id.test4);
//        iv_image4 = (ImageView) findViewById(R.id.imageView4);
//        TextView test5 = (TextView) findViewById(R.id.test5);
//        iv_image5 = (ImageView) findViewById(R.id.imageView5);
//        TextView test6 = (TextView) findViewById(R.id.test6);
//        iv_image6 = (ImageView) findViewById(R.id.imageView6);
//        TextView test7 = (TextView) findViewById(R.id.test7);
//        iv_image7 = (ImageView) findViewById(R.id.imageView7);
//        TextView test8 = (TextView) findViewById(R.id.test8);
//        iv_image8 = (ImageView) findViewById(R.id.imageView8);
//        TextView test9 = (TextView) findViewById(R.id.test9);
//        iv_image9 = (ImageView) findViewById(R.id.imageView9);
//        TextView test10 = (TextView) findViewById(R.id.test10);
//        iv_image10 = (ImageView) findViewById(R.id.imageView10);
        Random random;





        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProgramActivity.class);
                startActivity(intent);
            }
        };


        Button.OnClickListener buttonTagmove = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
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



            Call<API[]> call = APIController.getTestCall("movie_test", 1, 10);
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
                        new DownloadFilesTask().execute(result[i].getImg());

                    }
                    index = 0;

                }



                @Override
                public void onFailure(Call<API[]> call, Throwable t) {
                    Log.d("결과", "실패 : " + t.getMessage());
                }
            });




//        Call<API[]> call2 = APIController.getTestCall("movie_test", 2, 1);
//        call2.enqueue(new Callback<API[]>() {
//            @Override
//            public void onResponse(Call<API[]> call2, Response<API[]> response) {
//                API[] result2 = response.body();
//////                if (result != null && result.length > 0) {
//////                    Log.d("img_link", "img_link : " + result[1].getImg());
//////                    test.append(result[1].getTitle());
//////                    new DownloadFilesTask().execute(result[1].getImg());
////                } else {
////                    Log.e("API Response", "Response is null or empty");
////                }
//                Log.d("img_link", "img_link : " + result2[0].getImg());
//                test2.append(result2[0].getTitle());
//                new DownloadFilesTask().execute(result2[0].getImg());
//            }
//
//            @Override
//            public void onFailure(Call<API[]> call2, Throwable t) {
//                Log.d("결과", "실패 : " + t.getMessage());
//            }
//        });
//
//        Call<API[]> call3 = APIController.getTestCall("movie_test", 3, 1);
//        call3.enqueue(new Callback<API[]>() {
//            @Override
//            public void onResponse(Call<API[]> call3, Response<API[]> response) {
//                API[] result = response.body();
//////                if (result != null && result.length > 0) {
//////                    Log.d("img_link", "img_link : " + result[1].getImg());
//////                    test.append(result[1].getTitle());
//////                    new DownloadFilesTask().execute(result[1].getImg());
////                } else {
////                    Log.e("API Response", "Response is null or empty");
////                }
//                Log.d("img_link", "img_link : " + result[0].getImg());
//                test3.append(result[0].getTitle());
//                new DownloadFilesTask().execute(result[0].getImg());
//            }
//
//            @Override
//            public void onFailure(Call<API[]> call3, Throwable t) {
//                Log.d("결과", "실패 : " + t.getMessage());
//            }
//        });
//
//        Call<API[]> call4 = APIController.getTestCall("movie_test", 4, 1);
//        call4.enqueue(new Callback<API[]>() {
//            @Override
//            public void onResponse(Call<API[]> call4, Response<API[]> response) {
//                API[] result = response.body();
//////                if (result != null && result.length > 0) {
//////                    Log.d("img_link", "img_link : " + result[1].getImg());
//////                    test.append(result[1].getTitle());
//////                    new DownloadFilesTask().execute(result[1].getImg());
////                } else {
////                    Log.e("API Response", "Response is null or empty");
////                }
//                Log.d("img_link", "img_link : " + result[0].getImg());
//                test4.append(result[0].getTitle());
//                new DownloadFilesTask().execute(result[0].getImg());
//            }
//
//            @Override
//            public void onFailure(Call<API[]> call4, Throwable t) {
//                Log.d("결과", "실패 : " + t.getMessage());
//            }
//        });
//
//        Call<API[]> call5 = APIController.getTestCall("movie_test", 5, 1);
//        call5.enqueue(new Callback<API[]>() {
//            @Override
//            public void onResponse(Call<API[]> call5, Response<API[]> response) {
//                API[] result = response.body();
//////                if (result != null && result.length > 0) {
//////                    Log.d("img_link", "img_link : " + result[1].getImg());
//////                    test.append(result[1].getTitle());
//////                    new DownloadFilesTask().execute(result[1].getImg());
////                } else {
////                    Log.e("API Response", "Response is null or empty");
////                }
//                Log.d("img_link", "img_link : " + result[0].getImg());
//                test5.append(result[0].getTitle());
//                new DownloadFilesTask().execute(result[0].getImg());
//            }
//
//            @Override
//            public void onFailure(Call<API[]> call5, Throwable t) {
//                Log.d("결과", "실패 : " + t.getMessage());
//            }
//        });
//
//        Call<API[]> call6 = APIController.getTestCall("movie_test", 6, 1);
//        call6.enqueue(new Callback<API[]>() {
//            @Override
//            public void onResponse(Call<API[]> call6, Response<API[]> response) {
//                API[] result = response.body();
//////                if (result != null && result.length > 0) {
//////                    Log.d("img_link", "img_link : " + result[1].getImg());
//////                    test.append(result[1].getTitle());
//////                    new DownloadFilesTask().execute(result[1].getImg());
////                } else {
////                    Log.e("API Response", "Response is null or empty");
////                }
//                Log.d("img_link", "img_link : " + result[0].getImg());
//                test6.append(result[0].getTitle());
//                new DownloadFilesTask().execute(result[0].getImg());
//            }
//
//            @Override
//            public void onFailure(Call<API[]> call6, Throwable t) {
//                Log.d("결과", "실패 : " + t.getMessage());
//            }
//        });
//
//        Call<API[]> call7 = APIController.getTestCall("movie_test", 7, 1);
//        call7.enqueue(new Callback<API[]>() {
//            @Override
//            public void onResponse(Call<API[]> call7, Response<API[]> response) {
//                API[] result = response.body();
//////                if (result != null && result.length > 0) {
//////                    Log.d("img_link", "img_link : " + result[1].getImg());
//////                    test.append(result[1].getTitle());
//////                    new DownloadFilesTask().execute(result[1].getImg());
////                } else {
////                    Log.e("API Response", "Response is null or empty");
////                }
//                Log.d("img_link", "img_link : " + result[0].getImg());
//                test7.append(result[0].getTitle());
//                new DownloadFilesTask().execute(result[0].getImg());
//            }
//
//            @Override
//            public void onFailure(Call<API[]> call7, Throwable t) {
//                Log.d("결과", "실패 : " + t.getMessage());
//            }
//        });
//
//        Call<API[]> call8 = APIController.getTestCall("movie_test", 8, 1);
//        call8.enqueue(new Callback<API[]>() {
//            @Override
//            public void onResponse(Call<API[]> call8, Response<API[]> response) {
//                API[] result = response.body();
//////                if (result != null && result.length > 0) {
//////                    Log.d("img_link", "img_link : " + result[1].getImg());
//////                    test.append(result[1].getTitle());
//////                    new DownloadFilesTask().execute(result[1].getImg());
////                } else {
////                    Log.e("API Response", "Response is null or empty");
////                }
//                Log.d("img_link", "img_link : " + result[0].getImg());
//                test8.append(result[0].getTitle());
//                new DownloadFilesTask().execute(result[0].getImg());
//            }
//
//            @Override
//            public void onFailure(Call<API[]> call8, Throwable t) {
//                Log.d("결과", "실패 : " + t.getMessage());
//            }
//        });
//
//        Call<API[]> call9 = APIController.getTestCall("movie_test", 9, 1);
//        call9.enqueue(new Callback<API[]>() {
//            @Override
//            public void onResponse(Call<API[]> call9, Response<API[]> response) {
//                API[] result = response.body();
//////                if (result != null && result.length > 0) {
//////                    Log.d("img_link", "img_link : " + result[1].getImg());
//////                    test.append(result[1].getTitle());
//////                    new DownloadFilesTask().execute(result[1].getImg());
////                } else {
////                    Log.e("API Response", "Response is null or empty");
////                }
//                Log.d("img_link", "img_link : " + result[0].getImg());
//                test9.append(result[0].getTitle());
//                new DownloadFilesTask().execute(result[0].getImg());
//            }
//
//            @Override
//            public void onFailure(Call<API[]> call9, Throwable t) {
//                Log.d("결과", "실패 : " + t.getMessage());
//            }
//        });
//
//        Call<API[]> call10 = APIController.getTestCall("movie_test", 10, 1);
//        call10.enqueue(new Callback<API[]>() {
//            @Override
//            public void onResponse(Call<API[]> call10, Response<API[]> response) {
//                API[] result = response.body();
//////                if (result != null && result.length > 0) {
//////                    Log.d("img_link", "img_link : " + result[1].getImg());
//////                    test.append(result[1].getTitle());
//////                    new DownloadFilesTask().execute(result[1].getImg());
////                } else {
////                    Log.e("API Response", "Response is null or empty");
////                }
//                Log.d("img_link", "img_link : " + result[0].getImg());
//                test10.append(result[0].getTitle());
//                new DownloadFilesTask().execute(result[0].getImg());
//            }
//
//            @Override
//            public void onFailure(Call<API[]> call10, Throwable t) {
//                Log.d("결과", "실패 : " + t.getMessage());
//            }
//        });





//        buttonWebtoon.setOnClickListener(v -> {
//            Intent intent = new Intent(MainActivity.this, WebtoonActivity.class);
//            startActivity(intent);
//        });
//
//        buttonMovie.setOnClickListener(v -> {
//            Intent intent = new Intent(MainActivity.this, MovieActivity.class);
//            startActivity(intent);
//        });
//
//        buttonBook.setOnClickListener(v -> {
//            Intent intent = new Intent(MainActivity.this, BookActivity.class);
//            startActivity(intent);
//        });



        buttonTag[0].setOnClickListener(buttonTagmove);
        buttonTag[1].setOnClickListener(buttonTagmove);
        buttonTag[2].setOnClickListener(buttonTagmove);



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

//        test.setOnClickListener(listener);
//        test2.setOnClickListener(listener);
//        test3.setOnClickListener(listener);
//        test4.setOnClickListener(listener);
//        test5.setOnClickListener(listener);
//        test6.setOnClickListener(listener);
//        test7.setOnClickListener(listener);
//        test8.setOnClickListener(listener);
//        test9.setOnClickListener(listener);
//        test10.setOnClickListener(listener);



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
