package com.isil.mypets.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.isil.mypets.R;
import com.isil.mypets.adapter.ComentarioAdapter;
import com.isil.mypets.entity.ComentarioEntity;
import com.isil.mypets.listeners.OnCommentNoticeListener;
import com.isil.mypets.listeners.OnNoticeListener;
import com.isil.mypets.presenter.ComentarioNoticiaPresenter;
import com.isil.mypets.presenter.ComentarioNoticiaView;
import com.isil.mypets.storage.PreferencesHelper;

import java.awt.font.TextAttribute;
import java.util.List;

public class ComentariosNoticiaFragment extends Fragment implements ComentarioNoticiaView,View.OnClickListener{

    private static final String TAG = "ComentariosNoticiaFragment";

    private OnCommentNoticeListener commentNoticeListener;
    private ComentarioAdapter comentarioAdapter;

    private Button btnComentar;
    private Button btnVolverNoticiaDetalle;
    private ListView lstComentarios;
    private EditText eteComentario;
    private String idNoticia;

    private ComentarioNoticiaPresenter comentarioNoticiaPresenter;

    public static ComentariosNoticiaFragment newInstance(String param1, String param2) {
        ComentariosNoticiaFragment fragment = new ComentariosNoticiaFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public ComentariosNoticiaFragment() {
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
        return inflater.inflate(R.layout.fragment_comentarios, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCommentNoticeListener) {
            commentNoticeListener = (OnCommentNoticeListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        commentNoticeListener = null;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btnComentar=(Button)getView().findViewById(R.id.btnEnviarComentario);
        btnVolverNoticiaDetalle=(Button)getView().findViewById(R.id.btnVolverDetalle);
        lstComentarios=(ListView)getView().findViewById(R.id.lstComentarios);
        eteComentario=(EditText)getView().findViewById(R.id.eteComentario);


        comentarioNoticiaPresenter=new ComentarioNoticiaPresenter();
        comentarioNoticiaPresenter.attachedView(this);
        comentarioNoticiaPresenter.cargarComentariosNoticia(idNoticia);

        btnVolverNoticiaDetalle.setOnClickListener(this);
        btnComentar.setOnClickListener(this);
    }

    @Override
    public void renderComentarios(List<ComentarioEntity> list) {
        comentarioAdapter=new ComentarioAdapter(getContext(),list);
        lstComentarios.setAdapter(comentarioAdapter);
        eteComentario.setText("");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnVolverDetalle:
                commentNoticeListener.volverNoticiaDetalle(idNoticia);
                break;
            case R.id.btnEnviarComentario:
                if(eteComentario.getText()!=null) {
                    ComentarioEntity comentarioEntity = new ComentarioEntity();
                    comentarioEntity.setId(idNoticia);
                    comentarioEntity.setIdUsuario(PreferencesHelper.getUserSession(getContext().getApplicationContext()));
                    comentarioEntity.setAlias(PreferencesHelper.getAliasSession(getContext().getApplicationContext()));


                    comentarioEntity.setComentario(eteComentario.getText().toString());
                    comentarioNoticiaPresenter.comentarNoticia(comentarioEntity);
                }
                break;
        }
    }
}
