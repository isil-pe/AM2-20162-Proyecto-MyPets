package com.isil.mypets.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.isil.mypets.R;
import com.isil.mypets.entity.NoticeEntity;
import com.isil.mypets.listeners.OnNoticeListener;
import com.isil.mypets.presenter.DetalleNoticePresenter;
import com.isil.mypets.presenter.NoticeView;

public class NoticiaDetalleFragment extends Fragment implements NoticeView,View.OnClickListener{

    private static final String TAG = "NoticeInPresenter";
    private final String ERROR_MESSAGE= "Ocurriò un error";

    private DetalleNoticePresenter noticePresenter;

    private OnNoticeListener noticeListener;

    private TextView tviTitulo;
    private TextView tviDescripcion;
    private Button btnVolverLista;
    private Button btnVerComentarios;
    private String idNoticia;


    public static NoticiaDetalleFragment newInstance(String param1, String param2) {
        NoticiaDetalleFragment fragment = new NoticiaDetalleFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public NoticiaDetalleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            idNoticia = getArguments().getString("idNoticia");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_noticia_detalle, container, false);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnNoticeListener) {
            noticeListener = (OnNoticeListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        noticeListener = null;
    }

    @Override
    public void renderNotice(NoticeEntity notice) {
        tviTitulo.setText(notice.getTitulo());
        tviDescripcion.setText(notice.getDescripcion());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnVolverListaNoticia:
                noticeListener.volverListarNoticia();
                break;
            case R.id.btnVerNoticiaComentarios:
                noticeListener.verComentariosNoticia(idNoticia);
                break;
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tviTitulo=(TextView)getView().findViewById(R.id.tviNoticiaTitulo);
        tviDescripcion=(TextView)getView().findViewById(R.id.tviNoticiaDescripcion);
        btnVolverLista=(Button)getView().findViewById(R.id.btnVolverListaNoticia);
        btnVerComentarios=(Button)getView().findViewById(R.id.btnVerNoticiaComentarios);

        noticePresenter=new DetalleNoticePresenter();
        noticePresenter.attachedView(this);
        noticePresenter.findNotice(idNoticia);

        btnVolverLista.setOnClickListener(this);
        btnVerComentarios.setOnClickListener(this);

    }


}
