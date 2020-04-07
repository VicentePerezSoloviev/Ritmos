package soloviev.perez.vicente.ritmos;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class marcas extends AppCompatActivity {

    private FragmentTabHost tabHost;

    String fichero = "fichero_marcas.txt";

    private EditText distancia;
    private EditText tiempo_h;
    private EditText tiempo_m;
    private EditText tiempo_s;
    private EditText descripcion;

    private Spinner spinner;

    private ListView list;
    private TextView vista;

    CharSequence text;
    Toast toast;
    int duration = Toast.LENGTH_SHORT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marcas);

        /*Configuración pestañas superiores*/

        tabHost= (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(),android.R.id.tabcontent);
        tabHost.addTab(tabHost.newTabSpec("Mis marcas").setIndicator(getResources().getString(R.string.mejores_marcas)), misMarcas.class, null);
        tabHost.addTab(tabHost.newTabSpec("Nueva").setIndicator(getResources().getString(R.string.nueva_marca)), nuevaMarca.class, null);

        //actualizo nombre de actionBar para idioma

        setTitle(R.string.mejores_marcas);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // refresh your views here
        super.onConfigurationChanged(newConfig);
    }

    protected void guardar_marca(View view) {

        FileOutputStream fos;

        spinner = (Spinner) findViewById(R.id.deportes_spinner);
        String spin = String.valueOf(spinner.getSelectedItem());

        distancia = (EditText) findViewById(R.id.distancia_ed);

        tiempo_h = (EditText) findViewById(R.id.horas_ed);
        tiempo_m = (EditText) findViewById(R.id.min_ed);
        tiempo_s = (EditText) findViewById(R.id.sec_ed);

        descripcion = (EditText) findViewById(R.id.descripcion);


        Context context = getApplicationContext();

        //comprobamos que los campos estén cubiertos
        if (TextUtils.isEmpty(distancia.getText()) || TextUtils.isEmpty(tiempo_h.getText()) ||
                TextUtils.isEmpty(tiempo_m.getText()) || TextUtils.isEmpty(tiempo_s.getText())) {

            text = "Se deben cubrir los campos numéricos";
            toast = Toast.makeText(context, text, duration);
            toast.show();

            return;
        }

        String texto = spin + " " + distancia.getText().toString() + " " + tiempo_h.getText().toString()
                + ":" + tiempo_m.getText().toString() + ":" + tiempo_s.getText().toString() + " " + descripcion.getText().toString();

        if (!descripcion.getText().toString().isEmpty()) texto = texto + " " + "\n";
        else texto = texto + "\n";

        try {
            fos = openFileOutput(fichero, Context.MODE_APPEND);     //crea fichero si no existe
            fos.write(texto.getBytes());
        } catch (FileNotFoundException e) {
            Log.e("Ritmos", e.getMessage(),e);
        } catch (IOException e) {
            Log.e("Ritmos", e.getMessage(),e);
        }

        /*Limpiar campos y avisar de que ya se ha guardado*/
        limpiar(view);

        text = "Marca guardada!!";
        toast = Toast.makeText(context, text, duration);
        toast.show();

    }

    public void mostrar (View view) {
        AccesoFichero af = null;
        ArrayList<Marca> array = new ArrayList<>();

        try {
            af = new AccesoFichero();
            array = af.mostrar();

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
            text = "No existen marcas";
            Context context = getApplicationContext();
            toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        /*Mostrar marcas en pantalla*/
        list = (ListView) findViewById(R.id.list);
        ArrayAdapter<Marca> adapter = new MarcaAdaptador(getApplicationContext(), 0, array);
        list.setAdapter(adapter);
    }

    public void limpiar (View view) {
        distancia = (EditText) findViewById(R.id.distancia_ed);
        tiempo_h = (EditText) findViewById(R.id.horas_ed);
        tiempo_m = (EditText) findViewById(R.id.min_ed);
        tiempo_s = (EditText) findViewById(R.id.sec_ed);
        descripcion = (EditText) findViewById(R.id.descripcion);

        distancia.setText ("".toString());
        tiempo_h.setText ("".toString());
        tiempo_m.setText ("".toString());
        tiempo_s.setText ("".toString());
        descripcion.setText ("".toString());
    }


}
