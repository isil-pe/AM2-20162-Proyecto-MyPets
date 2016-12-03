package com.isil.mypets.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.isil.mypets.R;
import com.isil.mypets.entity.ComentarioEntity;

import java.util.List;

/**
 * Created by Alumno-J on 23/11/2016.
 */
public class ComentarioAdapter extends BaseAdapter{

    private Context context;
    private List<ComentarioEntity> comentarioEntities;

    public ComentarioAdapter(Context context, List<ComentarioEntity> comentarioEntities) {
        this.context = context;
        this.comentarioEntities = comentarioEntities;
    }

    @Override
    public int getCount() {
        return comentarioEntities.size();
    }

    @Override
    public Object getItem(int position) {
        return comentarioEntities.get(position);
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
            view=inflater.inflate(R.layout.row_comentario,null);
            ViewHolder holder=new ViewHolder();
            holder.tviUsuario=(TextView)view.findViewById(R.id.tviUsuario);
            holder.tviComentario = (TextView) view.findViewById(R.id.tviComentario);
            view.setTag(holder);
        }
        ComentarioEntity entity= comentarioEntities.get(position);
        if(entity!=null){
            ViewHolder viewHolder=(ViewHolder)view.getTag();
            viewHolder.tviUsuario.setText(entity.getAlias());
            viewHolder.tviComentario.setText(entity.getComentario());
        }
        return view;
    }

    static class ViewHolder{
        TextView tviUsuario;
        TextView tviComentario;
    }

}
