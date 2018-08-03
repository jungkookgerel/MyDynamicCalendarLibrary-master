package com.desai.vatsal.mydynamiccalendarlibrary;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class AppUtils {
    public static boolean isNetworkAvailable(Context con) {
        try {
            ConnectivityManager cm = (ConnectivityManager) con.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                networkInfo = Objects.requireNonNull(cm).getActiveNetworkInfo();
            }
            if (networkInfo != null && networkInfo.isConnected()) return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void showCustomAlert(Activity context, String message) {

        // Create layout inflater object to inflate toast.xml file
        LayoutInflater inflater = context.getLayoutInflater();

        // Call toast.xml file for toast layout
        @SuppressLint("InflateParams") View toastRoot = inflater.inflate(R.layout.toast, null);
        TextView txt =  toastRoot.findViewById(R.id.txtToastmessage);
        txt.setText(message);
        Toast toast = new Toast(context);

        // Set layout to toast
        toast.setView(toastRoot);
        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }
  /*  public static String getBaseUrl(Context context, String domain) {
        if (domain==null) domain = getProviderId(context);
        if (!domain.contains(".")) domain += ".trakit.in";
        return "https://"+domain+"/api/mobile/";
    }*/

}
