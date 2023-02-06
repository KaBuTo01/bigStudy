package com.example.forum.Fragment.Fragme;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.forum.MainActivity;
import com.example.forum.R;
import com.example.forum.bean.Circle.CircleDetail;
import com.example.forum.bean.User.User;
import com.example.forum.sql.MySqlite;
import com.example.forum.uit.OkhttpCallback;
import com.example.forum.uit.OkhttpUit;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_login#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_login extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText userName;
    private EditText passWord;
    private Button loginButton;
    private Button signUpButton;

    public Fragment_login() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_login.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_login newInstance(String param1, String param2) {
        Fragment_login fragment = new Fragment_login();
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
        View view =inflater.inflate(R.layout.fragment_login, container, false);
        userName = (EditText) view.findViewById(R.id.UserNameEdit);
        passWord = (EditText) view.findViewById(R.id.PassWordEdit);
        loginButton = (Button) view.findViewById(R.id.LoginButton);
        signUpButton = (Button) view.findViewById(R.id.SignUpButton);
        MySqlite mySqlite =new MySqlite(getActivity());
        loginButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 获取用户名和密码
                        String strUserName = userName.getText().toString().trim();
                        String strPassWord = passWord.getText().toString().trim();
                        // 判断如果用户名为"123456"密码为"123456"则登录成功
                        OkhttpUit.getInstance().doGet("/login?name="+strUserName+"&password="+strPassWord, new OkhttpCallback() {
                            @Override
                            public void onSuccess(String result) {
                                Gson gson = new Gson();
                                List<User> user = gson.fromJson(result, new TypeToken<List<User>>(){}.getType());
                                if (user.size()>0){
                                    mySqlite.addUser(user);
                                    Intent intent = new Intent(getActivity(), MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    Toast.makeText(getActivity(), "登录成功！", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(getActivity(), "请输入正确的用户名或密码！", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onError(Exception e) {

                            }
                        });

                    }
                }
        );

        return view;
    }
}