package com.example.forum.Fragment.Fragme;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.forum.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_reg#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_reg extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_reg() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_reg.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_reg newInstance(String param1, String param2) {
        Fragment_reg fragment = new Fragment_reg();
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
        View view =inflater.inflate(R.layout.fragment_reg, container, false);
        EditText userName = (EditText) view.findViewById(R.id.UserNameEdit);
        EditText passWord = (EditText) view.findViewById(R.id.PassWordEdit);
        EditText passWordAgain = (EditText) view.findViewById(R.id.PassWordAgainEdit);
        EditText email = (EditText) view.findViewById(R.id.EmailEdit);
        Button signUpButton = (Button) view.findViewById(R.id.SignUpButton);
        Button backLoginButton = (Button) view.findViewById(R.id.BackLoginButton);
        signUpButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String strUserName = userName.getText().toString().trim();
                        String strPassWord = passWord.getText().toString().trim();
                        String strPassWordAgain = passWordAgain.getText().toString().trim();
                        String strPhoneNumber = email.getText().toString().trim();
                        //注册格式粗检
                        if (strUserName.length() > 10) {
                            Toast.makeText(getActivity(), "用户名长度必须小于10！", Toast.LENGTH_SHORT).show();
                        } else if (strUserName.length() < 4) {
                            Toast.makeText(getActivity(), "用户名长度必须大于4！", Toast.LENGTH_SHORT).show();
                        } else if (strPassWord.length() > 16) {
                            Toast.makeText(getActivity(), "密码长度必须小于16！", Toast.LENGTH_SHORT).show();
                        } else if (strPassWord.length() < 6) {
                            Toast.makeText(getActivity(), "密码长度必须大于6！", Toast.LENGTH_SHORT).show();
                        } else if (!strPassWord.equals(strPassWordAgain)) {
                            Toast.makeText(getActivity(), "两次密码输入不一致！", Toast.LENGTH_SHORT).show();
                        } else if (!strPhoneNumber.contains("@")) {
                            Toast.makeText(getActivity(), "邮箱格式不正确！", Toast.LENGTH_SHORT).show();
                        } else {

                            Toast.makeText(getActivity(), "注册成功！", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        return view;
    }
}