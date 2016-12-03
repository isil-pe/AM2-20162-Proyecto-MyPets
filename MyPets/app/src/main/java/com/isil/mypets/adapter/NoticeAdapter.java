package com.isil.mypets.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.isil.mypets.R;
import com.isil.mypets.entity.NoticeEntity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by Alumno-J on 23/11/2016.
 */
public class NoticeAdapter extends BaseAdapter{

    private Context context;
    private List<NoticeEntity> noticeEntities;

    public NoticeAdapter(Context context, List<NoticeEntity> noticeEntities) {
        this.context = context;
        this.noticeEntities = noticeEntities;
    }

    @Override
    public int getCount() {
        return noticeEntities.size();
    }

    @Override
    public Object getItem(int position) {
        return noticeEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view= convertView;
        if(view==null){
            LayoutInflater inflater=LayoutInflater.from(context);
            view=inflater.inflate(R.layout.row_notice,null);
            ViewHolder holder=new ViewHolder();
            holder.tviTitle=(TextView)view.findViewById(R.id.tviTitle);
            holder.iviNotice = (ImageView) view.findViewById(R.id.iviNotice);
            //FALTAN LAS IMAGENES
            view.setTag(holder);
        }
        NoticeEntity entity= noticeEntities.get(position);
        if(entity!=null){
            ViewHolder viewHolder=(ViewHolder)view.getTag();
            viewHolder.tviTitle.setText(entity.getTitulo());
        }
        return view;
    }

    static class ViewHolder{
        ImageView iviNotice;
        TextView tviTitle;
    }

}
