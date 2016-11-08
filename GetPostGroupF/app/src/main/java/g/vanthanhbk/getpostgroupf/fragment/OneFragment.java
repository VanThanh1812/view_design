package g.vanthanhbk.getpostgroupf.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import g.vanthanhbk.getpostgroupf.R;

/**
 * Created by vanthanhbk on 05/10/2016.
 */
public class OneFragment extends android.support.v4.app.Fragment{
    public OneFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_one,container,false);
    }
}
