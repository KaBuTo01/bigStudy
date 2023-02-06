package com.example.forum.Fragment.Fragcamera;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.forum.Adapter.AttentionAdapter;
import com.example.forum.CircleDetailActivity;
import com.example.forum.R;
import com.example.forum.View.MyGridview;
import com.example.forum.bean.TieBaBean.TieBaHome;
import com.example.forum.bean.User.User;
import com.example.forum.sql.MySqlite;
import com.example.forum.uit.OkhttpCallback;
import com.example.forum.uit.OkhttpUit;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragLeft#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragLeft extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView text2;
    private MyGridview grid_view;

    public FragLeft() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragLeft.
     */
    // TODO: Rename and change types and number of parameters
    public static FragLeft newInstance(String param1, String param2) {
        FragLeft fragment = new FragLeft();
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
        View view =inflater.inflate(R.layout.fragment_frag_left, container, false);
        MySqlite my =new MySqlite(getActivity());
        List<User> users =my.getUser();
        grid_view = view.findViewById(R.id.grid_view);
        if (users.size()>0){
            OkhttpUit.getInstance().doGet("/attention?id="+users.get(0).getId(), new OkhttpCallback() {
                @Override
                public void onSuccess(String result) {
                    Gson gson = new Gson();
                    List<TieBaHome> data = gson.fromJson(result, new TypeToken<List<TieBaHome>>(){}.getType());
                    AttentionAdapter attentionAdapter=new AttentionAdapter(data,getActivity());
                    grid_view.setAdapter(attentionAdapter);
                    grid_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent =new Intent(getActivity(), CircleDetailActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("id",String.valueOf(data.get(position).getId()));
                            bundle.putString("image",data.get(position).getImage());
                            bundle.putString("name",data.get(position).getName());
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });
                }

                @Override
                public void onError(Exception e) {

                }
            });
        }

        return view;
    }
}