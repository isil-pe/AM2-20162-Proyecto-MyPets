package com.isil.mypets.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.isil.mypets.R;
import com.isil.mypets.adapter.NoticeAdapter;
import com.isil.mypets.entity.NoticeEntity;
import com.isil.mypets.listeners.OnNoticeListener;
import com.isil.mypets.presenter.NoticePresenter;
import com.isil.mypets.presenter.NoticesView;

import java.util.List;


public class NoticiaFragment extends Fragment implements NoticesView,ListView.OnItemClickListener{

    private static final String TAG = "NoticeInPresenter";
    private final String ERROR_MESSAGE= "Ocurriò un error";

    private ListView lstNotice;

    private NoticePresenter noticePresenter;
    private NoticeAdapter noticeAdapter;

    private OnNoticeListener noticeListener;

    public NoticiaFragment() {
        // Required empty public constructor
    }


    public static NoticiaFragment newInstance(String param1, String param2) {
        NoticiaFragment fragment = new NoticiaFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_noticia, container, false);
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
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        NoticeEntity item=(NoticeEntity)adapterView.getItemAtPosition(i);
        noticeListener.mostrarNoticiaDetalle(item.getObjectId());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        lstNotice = (ListView) getView().findViewById(R.id.lstNotice);
        lstNotice.setOnItemClickListener(this);


        noticePresenter= new NoticePresenter();
        noticePresenter.attachedView(this);
        noticePresenter.lstNotices();
    }

    @Override
    public void renderNotices(List<NoticeEntity> list) {
        noticeAdapter=new NoticeAdapter(getContext(),list);
        lstNotice.setAdapter(noticeAdapter);
    }
}
