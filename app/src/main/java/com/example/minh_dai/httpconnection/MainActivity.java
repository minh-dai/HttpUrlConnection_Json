package com.example.minh_dai.httpconnection;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private ImageView img;
    private DowloadJson mDowloadJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

        btn = findViewById(R.id.btn);
        img = findViewById(R.id.img);

        mDowloadJson = new DowloadJson(this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkNetWork()) {
                    //mDowloadJson.execute("http://jsonplaceholder.typicode.com/posts/1");
                    mDowloadJson.execute("https://api.github.com/users/dmnugent80/repos");
                }
            }
        });
    }

    private boolean checkNetWork(){

        ConnectivityManager mConnectivityManager;
        NetworkInfo mNetworkInfo;

        mConnectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        mNetworkInfo =mConnectivityManager.getActiveNetworkInfo();

        if(mNetworkInfo == null){
            Toast.makeText(this, "No Defual network ", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!mNetworkInfo.isConnected()){
            Toast.makeText(this, "NetWork is not connected", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!mNetworkInfo.isAvailable()){
            Toast.makeText(this, "NetWork is not availables", Toast.LENGTH_SHORT).show();
            return false;
        }

        Toast.makeText(this, "NetWork OK", Toast.LENGTH_SHORT).show();
        return true;
    }

}
