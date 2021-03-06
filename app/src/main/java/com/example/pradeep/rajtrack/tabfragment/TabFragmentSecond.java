package com.example.pradeep.rajtrack.tabfragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pradeep.rajtrack.JsonRequestActivity;
import com.example.pradeep.rajtrack.R;
import com.example.pradeep.rajtrack.StringRequestActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TabFragmentSecond.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TabFragmentSecond#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabFragmentSecond extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView mDisplayUsn;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button timeTable;
    View view;
    private String usn="15bwsb3024";
    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TabFragmentSecond.
     */
    // TODO: Rename and change types and number of parameters
    public static TabFragmentSecond newInstance(String param1, String param2) {
        TabFragmentSecond fragment = new TabFragmentSecond();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public TabFragmentSecond() {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view=inflater.inflate(R.layout.fragment_tab_fragment_second, container, false);
        timeTable= (Button) view.findViewById(R.id.time_table);
        mDisplayUsn= (TextView) view.findViewById(R.id.d_second_usn);
        timeTable.setOnClickListener(this);

        String usn=getArguments().getString("usn");
        String fee=getArguments().getString("fee");
        String branch=getArguments().getString("branch");
        mDisplayUsn.setText(usn);
        Log.d("SecondFragment", usn + " : " + fee + " : " + branch + " : ");
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {

        Toast.makeText(getActivity(), "MAKE Text two!", Toast.LENGTH_SHORT).show();

        Intent i=new Intent(getActivity(), StringRequestActivity.class);
        i.putExtra("usn", usn);
        startActivity(i);
        //startActivity(new Intent(getActivity(), StringRequestActivity.class));

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
