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

        holder.tv1.setText(current.getList_data());
        holder.tv2.setText(current.getMin_temp());
        holder.tv3.setText(current.getMax_temp());
        holder.tv4.setText(current.getDesc());
        holder.imageView.setTag(holder);
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }
}
