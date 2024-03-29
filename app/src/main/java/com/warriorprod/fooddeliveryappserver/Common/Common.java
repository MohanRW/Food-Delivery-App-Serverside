package com.warriorprod.fooddeliveryappserver.Common;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.warriorprod.fooddeliveryappserver.Remote.IGeoCoordinates;
import com.warriorprod.fooddeliveryappserver.Remote.RetrofitClient;
import com.warriorprod.fooddeliveryappserver.model.Request;
import com.warriorprod.fooddeliveryappserver.model.User;

import retrofit2.Retrofit;

public class Common {
    public static User currentUser;
    public static Request currentRequest;

    public static final String UPDATE = "Update";
    public static final String DELETE = "Delete";

    public static final int PICK_IMAGE_REQUEST = 71;

    public static final String baseUrl = "https://maps.googleapis.com";

    public static final String convertCodeToStatus(String status){
        if(status!=null) {
            if (status.equals("0"))
                return "Placed";
            else if (status.equals("1"))
                return "On My Way";
            else
                return "Shipped";
        }else
            return "Order Status is Not Available!";
    }

    public static IGeoCoordinates getGeoCodeService(){
        return RetrofitClient.getClient(baseUrl).create(IGeoCoordinates.class);
    }

    public static Bitmap scaleBitmap(Bitmap bitmap,int newWidth, int newHeight)
    {
        Bitmap scaledBitmap = Bitmap.createBitmap(newWidth,newHeight,Bitmap.Config.ARGB_8888);

        float scaleX = newWidth/(float)bitmap.getWidth();
        float scaleY = newHeight/(float)bitmap.getHeight();
        float pivotX = 0,pivotY = 0;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(scaleX,scaleY,pivotX,pivotY);

        Canvas canvas = new Canvas(bitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bitmap,0,0,new Paint(Paint.FILTER_BITMAP_FLAG));

        return scaledBitmap;

    }

}
