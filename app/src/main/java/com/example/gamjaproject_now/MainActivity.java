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
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gamjaproject_now.API.Content;
import com.example.gamjaproject_now.API.APIController;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    int index = 0;

    private Content[] result;


    ImageView[] iv_imagearr = new ImageView[10];
    TextView[] testArr = new TextView[10];
    Button[] buttonTag = new Button[3];

//    private API data;


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

//        Call<CountAPI[]> countCall = APIController.getCountCall("movie_test");
//        countCall.enqueue(new Callback<CountAPI[]>() {
//            @Override
//            public void onResponse(Call<CountAPI[]> call, Response<CountAPI[]> response) {
//                CountAPI[] result = response.body();
//                Log.d("c_link", "c_link : " + result[0].getCnt());
////                int b = result[0].getCnt();
//            }
//
//            @Override
//            public void onFailure(Call<CountAPI[]> call, Throwable t) {
//                Log.d("결과", "실패 : " + t.getMessage());
//
//            }
//        });

        fetchDataFromApi();



//        View.OnClickListener listener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(result != null) {
//
//                    Intent intentP = new Intent(MainActivity.this, ProgramActivity.class);
//                    intentP.putExtra("result", result);
//
//                    startActivity(intentP);
//                }else{
//                    Toast.makeText(MainActivity.this, "data is not loaded yet", Toast.LENGTH_SHORT).show();
//                }
//            }
//        };


        Button.OnClickListener buttonTagmove = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentC = new Intent(MainActivity.this, CategoryActivity.class);
                String buttonText = buttonTag[0].getText().toString();
                String buttonText2 = buttonTag[1].getText().toString();
                String buttonText3 = buttonTag[2].getText().toString();
                intentC.putExtra("buttonText", buttonText);

                startActivity(intentC);
            }
        };


        buttonTag[0].setOnClickListener(buttonTagmove);
        buttonTag[1].setOnClickListener(buttonTagmove);
        buttonTag[2].setOnClickListener(buttonTagmove);

//        testArr[0].setOnClickListener(listener);
//        testArr[1].setOnClickListener(listener);
//        testArr[2].setOnClickListener(listener);
//        testArr[3].setOnClickListener(listener);
//        testArr[4].setOnClickListener(listener);
//        testArr[5].setOnClickListener(listener);
//        testArr[6].setOnClickListener(listener);
//        testArr[7].setOnClickListener(listener);
//        testArr[8].setOnClickListener(listener);
//        testArr[9].setOnClickListener(listener);
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


    private void fetchDataFromApi() {
        Call<Content[]> call = APIController.getTestCall("kpnovel", 1, 10);
        call.enqueue(new Callback<Content[]>() {
            Intent[] intent = new Intent[10];
            @Override
            public void onResponse(Call<Content[]> call, Response<Content[]> response) {
                if (response.isSuccessful()) {
                    result = response.body();

                    for (int i = 0; i < 10; i++) {
                        Log.d("img_link", "img_link : " + result[i].getImg());
                        testArr[i].append(result[i].getTitle());
                        new DownloadFilesTask().execute(result[i].getImg());

                        intent[i]=new Intent(MainActivity.this, ProgramActivity.class);
                        intent[i].putExtra("title", result[i].getTitle().toString());
                        intent[i].putExtra("director", result[i].getDirector().toString());
                        intent[i].putExtra("description", result[i].getDescription().toString());
                        intent[i].putExtra("image", result[i].getImg());
                    }
                }else{
                    Toast.makeText(MainActivity.this, "API call failed", Toast.LENGTH_SHORT).show();
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

                ////                if (result != null && result.length > 0) {
                ////                    Log.d("img_link", "img_link : " + result[1].getImg());
                ////                    test.append(result[1].getTitle());
                ////                    new DownloadFilesTask().execute(result[1].getImg());
                //                } else {
                //                    Log.e("API Response", "Response is null or empty");
                //                }

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
