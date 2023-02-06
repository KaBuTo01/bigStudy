package com.example.forum.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.forum.MainActivity;
import com.example.forum.bean.Home.Home;
import com.example.forum.reg_login_Activity;
import com.example.forum.R;
import com.example.forum.sql.MySqlite;
import com.example.forum.uit.LanguageUtil;
import com.example.forum.uit.OkhttpCallback;
import com.example.forum.uit.OkhttpUit;
import com.example.forum.uit.SpUserUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_me#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_me extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageView login;
    private MySqlite mySqlite;
    private TextView name;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private ImageView translate;
    private ImageView translate1;

    public Fragment_me() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_me.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_me newInstance(String param1, String param2) {
        Fragment_me fragment = new Fragment_me();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_me, container, false);
        translate1 = view.findViewById(R.id.translate);
        translate1.setOnClickListener(this);
        login = view.findViewById(R.id.login);
        name = view.findViewById(R.id.name);
        tv1 = view.findViewById(R.id.tv1);
        tv2 = view.findViewById(R.id.tv2);
        tv3 = view.findViewById(R.id.tv3);
        mySqlite = new MySqlite(getActivity());
        if (mySqlite.getUser().size()>0){
            Glide.with(this)
                    .load(mySqlite.getUser().get(0).getHeadimg())
                    .error(R.drawable.loading)
                    .into(login);
            name.setText(mySqlite.getUser().get(0).getName());
            OkhttpUit.getInstance().doGet("/actityNum?id="+mySqlite.getUser().get(0).getId(), new OkhttpCallback() {
                @Override
                public void onSuccess(String result) {
                    Gson gson = new Gson();
                    List<Home> home = gson.fromJson(result, new TypeToken<List<Home>>(){}.getType());
                    tv1.setText(String.valueOf(home.size()));
                    tv2.setText("1");
                    tv3.setText("0");
                }

                @Override
                public void onError(Exception e) {

                }
            });
        }
        login.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                if (mySqlite.getUser().size()==0){
                    startActivity(new Intent(getActivity(),reg_login_Activity.class));
                }
                break;

            case R.id.translate:
                showSaveLanguage("en");
                break;
        }


    }

    private void showSaveLanguage(String language){
        //设置的语言、重启的类一般为应用主入口（微信也是到首页）
        LanguageUtil.changeAppLanguage(getActivity(), language, MainActivity.class);
        //保存设置的语言
        SpUserUtils.putString(getActivity(), "language", language);
    }

}