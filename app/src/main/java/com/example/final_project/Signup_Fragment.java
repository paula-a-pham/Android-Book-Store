package com.example.final_project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Signup_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Signup_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Signup_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Signup_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Signup_Fragment newInstance(String param1, String param2) {
        Signup_Fragment fragment = new Signup_Fragment();
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
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        Book_Database database = new Book_Database(getActivity());

        EditText email = (EditText) view.findViewById(R.id.signup_page_mail);
        EditText pass1 = (EditText) view.findViewById(R.id.signup_page_pass1);
        EditText pass2 = (EditText) view.findViewById(R.id.signup_page_pass2);
        Button signup = (Button) view.findViewById(R.id.signup_page_btn);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email.getText().toString().equals("")||pass1.getText().toString().equals("")||pass2.getText().toString().equals(""))
                {
                    Toast.makeText(getActivity(), "EMPTY FIELDS !", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    boolean check = database.check_user_email(email.getText().toString());
                    if(check == true)
                    {
                        Toast.makeText(getActivity(), "THIS EMAIL HAS TAKEN !", Toast.LENGTH_SHORT).show();
                        email.setText("");
                        pass1.setText("");
                        pass2.setText("");
                    }
                    else
                    {
                        if(!pass1.getText().toString().equals(pass2.getText().toString()))
                        {
                            Toast.makeText(getActivity(), "2 PASSWORD ARE NOT THE SAME !", Toast.LENGTH_SHORT).show();
                            pass1.setText("");
                            pass2.setText("");
                        }
                        else
                        {
                            boolean c = validate(email);
                            if(c)
                            {
                                boolean add = database.insert_user_data(email.getText().toString(),pass1.getText().toString());
                                if(add == true)
                                {
                                    Toast.makeText(getActivity(), "EMAIL ADDED SECCESSFULLY !", Toast.LENGTH_SHORT).show();
                                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.login_signup_fragment,new Login_Fragment()).commit();
                                }
                                else
                                {
                                    Toast.makeText(getActivity(), "EMAIL NOT ADDED !", Toast.LENGTH_SHORT).show();
                                    email.setText("");
                                    pass1.setText("");
                                    pass2.setText("");
                                }
                            }
                        }
                    }
                }
            }
        });
        return view;
    }

    private boolean validate(EditText email)
    {
        String input = email.getText().toString();
        if(Patterns.EMAIL_ADDRESS.matcher(input).matches())
        {
            return true;
        }
        else
        {
            Toast.makeText(getActivity(), "Invalid Email Address !", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}