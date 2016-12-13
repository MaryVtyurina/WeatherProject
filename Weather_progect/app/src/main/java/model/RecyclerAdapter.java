package model;

/**
 * Created by Maria on 12/12/16.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.maria.weatherapp.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerHolder>{

    Context context;
    LayoutInflater inflater;

    List<WeatherList> weatherList;
    int[] icons = { R.drawable.icon_01d,
            R.drawable.icon_02d,
            R.drawable.icon_03d,
            R.drawable.icon_04d,
            R.drawable.icon_09d,
            R.drawable.icon_10d,
            R.drawable.icon_11d,
            R.drawable.icon_13d,
            R.drawable.icon_50d};

    public RecyclerAdapter(Context context, List<WeatherList> weatherList) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.weatherList = weatherList;
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.custom_row, parent, false);

        RecyclerHolder viewHolder = new RecyclerHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {

        WeatherList current = weatherList.get(position);

        switch (current.getList_icon()) {
            case "01d":
                holder.imageView.setImageResource(icons[0]);
                break;
            case "02d":
                holder.imageView.setImageResource(icons[1]);
                break;
            case "03d":
                holder.imageView.setImageResource(icons[2]);
                break;
            case "04d":
                holder.imageView.setImageResource(icons[3]);
                break;
            case "09d":
                holder.imageView.setImageResource(icons[4]);
                break;
            case "10d":
                holder.imageView.setImageResource(icons[5]);
                break;
            case "11d":
                holder.imageView.setImageResource(icons[6]);
                break;
            case "13d":
                holder.imageView.setImageResource(icons[7]);
                break;
            case "50d":
                holder.imageView.setImageResource(icons[8]);
                break;
        }

        holder.tv1.setText(current.getList_data());
        holder.tv2.setText(current.getMin_temp());
        holder.tv3.setText(current.getMax_temp());
        holder.tv4.setText(current.getDesc());
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }
}
