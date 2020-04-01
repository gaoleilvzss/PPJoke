package com.vinsuan.ppjoke.ui.my;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.vinsuan.libnavannotation.FragmentDestination;
import com.vinsuan.ppjoke.R;

@FragmentDestination(pageUrl = "main/tabs/my",asStarter = false)
public class MyFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_my, container, false);
        Log.d("jet","oncreate");
        return root;
    }
}
