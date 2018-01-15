package com.h2g2.dontpanic.activities.base;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.h2g2.dontpanic.R;
import com.h2g2.dontpanic.services.abstracts.PermissionBase;

import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void navigateToActivity(Class<?> cls) {
        System.out.println("WELP!");
        Intent intent = new Intent(getApplicationContext(), cls);
        startActivity(intent);
    }

    public void checkPermissions(){
        PermissionBase permission = new PermissionBase(this) {
            @Override
            public List<String> getPermissionNotGranted(List<String> permissionList) {
                List<String> result = new ArrayList<>();
                for (String item : permissionList) {
                    if (ContextCompat.checkSelfPermission(BaseActivity.this, item) != PackageManager.PERMISSION_GRANTED) {
                        result.add(item);
                    }
                }
                return result;
            }

            @Override
            public List<String> setPermissionList() {
                List<String> permissionList = new ArrayList<>();

                permissionList.add(Manifest.permission.ACCESS_COARSE_LOCATION);
                permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
                permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                permissionList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
                permissionList.add(Manifest.permission.CAMERA);
                permissionList.add(Manifest.permission.BLUETOOTH);
                permissionList.add(Manifest.permission.BLUETOOTH_ADMIN);
                permissionList.add(Manifest.permission.BLUETOOTH_PRIVILEGED);
                permissionList.add(Manifest.permission.ACCESS_NETWORK_STATE);

                return permissionList;
            }

            @Override
            public void checkPermissionList() {
                List<String> permissionNotGranted = getPermissionNotGranted(setPermissionList());
                if (permissionNotGranted.size() > 1) {
                    ActivityCompat.requestPermissions(BaseActivity.this, permissionNotGranted.toArray(new String[permissionNotGranted.size()]), REQUEST_CODE_ASK_PERMISSIONS);
                } else {
                    System.out.println("NO!");
                }
            }
        };

        try{
            permission.checkPermissionList();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
