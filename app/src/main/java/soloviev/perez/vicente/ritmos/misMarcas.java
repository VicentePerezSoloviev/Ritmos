package soloviev.perez.vicente.ritmos;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class misMarcas extends Fragment{

    private Spinner spinner;
    private ListView list;
    private Activity rootView;

    private TextView vista;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.mismarcas, container, false);
        View rootview = inflater.inflate(R.layout.mismarcas, container, false);
        return rootview;
    }

    public void OnCreate (Bundle saved) {
        super.onCreate(saved);
    }


}



