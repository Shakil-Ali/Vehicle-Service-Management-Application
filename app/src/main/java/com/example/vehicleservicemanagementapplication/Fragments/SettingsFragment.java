package com.example.vehicleservicemanagementapplication.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.vehicleservicemanagementapplication.Helpers.NightMode;
import com.example.vehicleservicemanagementapplication.R;

// https://www.youtube.com/watch?v=G0dnFpdE5rE

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SettingsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment
{

    // Variable Initialisations
    Button ccButton;

    // The fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    // Required empty public constructor
    public SettingsFragment()
    {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */


    // Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2)
    {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;

    // end of Settings fragment method
    }


    // Main method
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // Conditional if to check of getArguments function returns a non-null value
        if (getArguments() != null)
        {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    // end of main method
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Final view variable
        final View fragmentView = inflater.inflate(R.layout.fragment_settings, container, false);
        // Assign variable to element
        ccButton = fragmentView.findViewById(R.id.ccBtn);
        // On click method for dark mode
        ccButton.setOnClickListener(new View.OnClickListener()
        {
            // On click method
            @Override
            public void onClick(View v)
            {
                // Initialise an intent - store night mode activity
                Intent intent1 = new Intent(getActivity(), NightMode.class);
                // Start the stored activity
                startActivity(intent1);

            // end of on click method
            }
        });

        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_settings, container, false);
        return fragmentView;

    // end of on create method with 3 parameters
    }


    // Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri)
    {
        // Conditional to check if mListerner is not null
        if (mListener != null)
        {
            mListener.onFragmentInteraction(uri);
        }

    // end of on button pressed method
    }


    // Method onAttach
    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);

    // end of onAttach method
    }


    // Method onDetach
    @Override
    public void onDetach()
    {
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


    public interface OnFragmentInteractionListener
    {
        // Update argument type and name
        void onFragmentInteraction(Uri uri);
    }



// end of class
}
