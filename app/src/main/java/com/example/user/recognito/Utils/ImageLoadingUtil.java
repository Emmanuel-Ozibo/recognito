package com.example.user.recognito.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.example.user.recognito.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;

/**
 * Created by emmanuel on 12/20/2017.
 */

public class ImageLoadingUtil{

    /**
     * @param context Application context
     * @param path The image path
     * @param loadedListener This checks the loaded bitmap
     * */
    public static void loadImage(Context context, String path, final BitmapLoadedListener loadedListener){
        Picasso.with(context)
                .load(path)
                .placeholder(R.drawable.ic_image_placeholder)
                .error(R.drawable.ic_action_image)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        loadedListener.onLoad(bitmap);
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable){

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
    }

    public interface BitmapLoadedListener{
        void onLoad(Bitmap originalBitmap);
    }

    public static Bitmap getCircularBitmap(Bitmap inputBitmap){
        final Bitmap output = Bitmap.createBitmap(inputBitmap.getWidth(), inputBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(output);

        final int color = Color.RED;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, inputBitmap.getWidth(), inputBitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawOval(rectF, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(inputBitmap, rect, rect, paint);

        return output;
    }

}
