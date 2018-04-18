package com.reshma.prajapati.mylist.adapters;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.reshma.prajapati.mylist.R;
import com.reshma.prajapati.mylist.databinding.ListItemBinding;
import com.reshma.prajapati.mylist.model.Row;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
/*
 * Adapter listing data into recycler view
 * */
public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {
    private ArrayList<Row> myDataList;

    public MyListAdapter(ArrayList<Row> myDataList) {
        this.myDataList=myDataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        //Binding list view
        ListItemBinding itemBinding =
                DataBindingUtil.inflate(layoutInflater,R.layout.list_item, parent,false);
        return new ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Row data = myDataList.get(position);

        holder.bind(data);
        loadImage((ImageView) holder.itemView.findViewById(R.id.img), data.getImageHref());

    }

    @Override
    public int getItemCount() {
//        Log.v("Adapter","Size ()+ "+myDataList.size());

        return myDataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ListItemBinding itemBinding;

        ViewHolder(ListItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }
        void bind(Row data) {
            itemBinding.setData(data);
            itemBinding.executePendingBindings();
        }
    }

    @BindingAdapter({"bind:image"})
    public static void loadImage(ImageView view, String imageUrl) {

        if(imageUrl!=null) {

            //Picasso library for lazy loading and cache
            Log.v("adapter","image : "+imageUrl);
            Picasso.get()
                    .load(imageUrl)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(view);
        }
    }
}
