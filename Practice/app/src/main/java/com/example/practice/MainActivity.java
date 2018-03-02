package com.example.practice;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.practice.view.MvvmActivity;

import java.util.List;


/**
 * 打开QQ，打开豌豆荚，从应用市场下载指定包名的应用
 */
public class MainActivity extends AppCompatActivity {
    private Button gotoQQBtn;
    private Button gotoWDJBtn;
    private Button downloadBtn;
    private Button mvvmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gotoQQBtn = (Button)findViewById(R.id.gotoQQBtn);
        gotoQQBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //启动第三方应用QQ
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.tencent.mobileqq");
                if(intent != null){
                    startActivity(intent);
                }
            }
        });
        gotoWDJBtn = (Button)findViewById(R.id.gotoWDJBtn);
        gotoWDJBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //启动第三方应用豌豆荚
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.wandoujia.phoenix2");
                if(intent != null){
                    startActivity(intent);
                }
            }
        });
        downloadBtn = (Button)findViewById(R.id.downloadBtn);
        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //从应用市场下载包名为com.ss.android.ugc.aweme的应用
                Uri uri = Uri.parse("market://details?id=com.ss.android.ugc.aweme");//id为应用包名
                Intent it = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(it);
            }
        });
//        getAllPackages();
        mvvmBtn = (Button)findViewById(R.id.mvvmBtn);
        mvvmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MvvmActivity.class));
            }
        });
    }

    private void getAllPackages(){
        List<PackageInfo> packageInfoList = getPackageManager().getInstalledPackages(0);
        if(packageInfoList != null){
            for (PackageInfo packageInfo:packageInfoList){
                if(packageInfo.packageName.contains("wandou")){
                    Log.i("wanlijun",packageInfo.packageName);
                }
            }
        }
    }
}
