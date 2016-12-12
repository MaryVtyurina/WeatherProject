package model;

/**
 * Created by Maria on 12/12/16.
 */
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maria.weatherapp.R;

public class RecyclerHolder extends RecyclerView.ViewHolder {
    TextView tv1, tv2, tv3, tv4;
    ImageView imageView;

    public RecyclerHolder(View itemView) {
        super(itemView);
        tv1 = (TextView) itemView.findViewById(R.id.data);
        tv2 = (TextView) itemView.findViewById(R.id.temp_min);
        tv3 = (TextView) itemView.findViewById(R.id.temp_max);
        tv4 = (TextView) itemView.findViewById(R.id.desc);
        imageView = (ImageView) itemView.findViewById(R.id.avatar);
    }
}
