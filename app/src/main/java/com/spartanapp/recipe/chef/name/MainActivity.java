package com.spartanapp.recipe.chef.name;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.SkuDetails;
import com.spartanapp.recipe.chef.R;
import com.spartanapp.recipe.chef.util.billing.BillingManager;
import com.spartanapp.recipe.chef.util.billing.BillingProvider;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BillingManager.BillingUpdatesListener {


    private static final String TAG = MainActivity.class.getSimpleName();

    private BillingManager mBillingManager;
    private Button mButton;
    private BillingProvider mBillingProvider;
    private BillingClient mBillingClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act);
        Log.e(TAG, "hi");

        mBillingManager = new BillingManager(this, this);
        mBillingManager.start();

        mButton = findViewById(R.id.btn);

        //   setupResponse();
        handleManagerAndUiReady();
    }

    private void handleManagerAndUiReady() {

        mBillingManager.onSubscribeClick("1month");

        List<String> skuList = new ArrayList<>();
        skuList.add("1month");

    }

    @Override
    public void onBillingClientSetupFinished() {
        Log.e(TAG, "BillingClientSetup");

        mBillingManager.getPrice("1month", new BillingManager.GetPriceCallback() {
            @Override
            public void onSuccess(String price) {
                Log.e(TAG, "onSuccess: "+price);
            }

            @Override
            public void onError() {
                Log.e(TAG, "onError: ");
            }
        });
    }

    @Override
    public void onPurchasesUpdated(List<Purchase> purchases) {
        Log.e(TAG, "get pacckage name: " + purchases.get(0).getPackageName());
    }
}