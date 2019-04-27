package com.example.christy0514.homework5;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;



public class Fragment2 extends Fragment {
    private View view;
    private Button button;
    private Button button1;
    private EditText edName;
    private EditText edPhone;
    private EditText edText;
    private MyDBHelper helper;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Fragment2() {
        // Required empty public constructor
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
        view = inflater.inflate(R.layout.fragment_fragment2, container, false);
        button = (Button) view.findViewById(R.id.button5);
        button1 = (Button) view.findViewById(R.id.button4);
        helper = new MyDBHelper(getActivity());
        edName = (EditText) view.findViewById(R.id.editText);
        edPhone = (EditText) view.findViewById(R.id.editText2);
        edText = (EditText) view.findViewById(R.id.editText3);

        button.setOnClickListener(new View.OnClickListener() {                //完成
            @Override
            public void onClick(View v) {
                String name = edName.getText().toString();
                String phone = edPhone.getText().toString();
                String text = edText.getText().toString();
                Contact contact = new Contact(name,phone,text);
                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
                long rowId  = helper.insert(contact);
                /*if (rowId != -1){
                    Toast.makeText(getActivity(),"success",Toast.LENGTH_SHORT).show();
                }*/
                Log.d("ADD",rowId+"");
            }
        });
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {                                       //取消
                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
