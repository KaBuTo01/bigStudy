package com.example.forum.Fragment.Fragcamera;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.forum.Adapter.LeftAdapter;
import com.example.forum.Adapter.RightAdapter;
import com.example.forum.CircleDetailActivity;
import com.example.forum.R;
import com.example.forum.bean.NewsBean.Journalism;
import com.example.forum.bean.TieBaBean.TieBaHome;
import com.example.forum.bean.catalog;
import com.example.forum.bean.catalogRight;
import com.example.forum.uit.OkhttpCallback;
import com.example.forum.uit.OkhttpUit;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragRight#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragRight extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ListView left;
    private ListView right;
    private List<catalog> dataLeft;
    private LeftAdapter leftAdapter;
    private List<catalogRight> dataRight;
    private RightAdapter rightAdapter;
    public FragRight() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragRight.
     */
    // TODO: Rename and change types and number of parameters
    public static FragRight newInstance(String param1, String param2) {
        FragRight fragment = new FragRight();
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
        View view=inflater.inflate(R.layout.fragment_frag_right, container, false);
        left = view.findViewById(R.id.listView_left);
        right = view.findViewById(R.id.listView_right);
        leftListView();
        rightListView();


        return view;
    }

    private void rightListView() {
        OkhttpUit.getInstance().doGet("/getTieBa", new OkhttpCallback() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                List<TieBaHome> tieba = gson.fromJson(result, new TypeToken<List<TieBaHome>>(){}.getType());
                rightAdapter = new RightAdapter(getContext(),tieba);
                right.setAdapter(rightAdapter);
                intentCircle(tieba);
            }

            @Override
            public void onError(Exception e) {

            }
        });

    }

    private void leftListView() {
        dataLeft = new ArrayList<>();
        dataLeft.add(new catalog(0,getResources().getString(R.string.Recommend)));
        dataLeft.add(new catalog(1,getResources().getString(R.string.Hot)));
        dataLeft.add(new catalog(2,getResources().getString(R.string.Game)));
        dataLeft.add(new catalog(3,getResources().getString(R.string.School)));
        leftAdapter = new LeftAdapter(getContext(),dataLeft);

        left.setAdapter(leftAdapter);
        left.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                leftAdapter.setCurrentItem(position);
                leftAdapter.notifyDataSetChanged();
                if (position==0){
                    OkhttpUit.getInstance().doGet("/getTieBa", new OkhttpCallback() {
                        @Override
                        public void onSuccess(String result) {
                            Gson gson = new Gson();
                            List<TieBaHome> tieba = gson.fromJson(result, new TypeToken<List<TieBaHome>>(){}.getType());
                            rightAdapter.replaceAll(tieba);
                        }
                        @Override
                        public void onError(Exception e) {

                        }
                    });
                }else{
                    OkhttpUit.getInstance().doGet("/getType?type="+(position-1), new OkhttpCallback() {
                        @Override
                        public void onSuccess(String result) {
                            Gson gson = new Gson();
                            List<TieBaHome> alltieba = gson.fromJson(result, new TypeToken<List<TieBaHome>>(){}.getType());
                            rightAdapter.replaceAll(alltieba);
                        }

                        @Override
                        public void onError(Exception e) {

                        }
                    });
                }


            }
        });
    }
    private  void intentCircle(List<TieBaHome> list){
        right.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =new Intent(getActivity(), CircleDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id",String.valueOf(list.get(position).getId()));
                bundle.putString("image",list.get(position).getImage());
                bundle.putString("name",list.get(position).getName());
                intent.putExtras(bundle);
                startActivity(intent);
                Toast.makeText(getActivity(),list.get(position).getName() , Toast.LENGTH_SHORT).show();
            }
        });

    }

}