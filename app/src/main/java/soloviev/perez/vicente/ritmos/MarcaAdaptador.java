package soloviev.perez.vicente.ritmos;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MarcaAdaptador extends ArrayAdapter<Marca> {
    private Context context;
    private ArrayList<Marca> arrayMarcas;

    //constructor, call on creation
    public MarcaAdaptador(Context context, int resource, ArrayList<Marca> objects) {
        super(context, resource, objects);

        this.context = context;
        this.arrayMarcas = objects;
    }

    //called when rendering the list
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public View getView(final int position, View convertView, final ViewGroup parent) {

        //get the property we are displaying
        final Marca marca = arrayMarcas.get(position);

        //get the inflater and inflate the XML layout for each item
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.elemento_lista, null);

        TextView distancia = (TextView) view.findViewById(R.id.distancia);
        TextView tiempo = (TextView) view.findViewById(R.id.tiempo);
        TextView descripcion = (TextView) view.findViewById(R.id.descripcion);

        Button botonDeporte = (Button) view.findViewById(R.id.dep);
        Button botonEliminar = (Button) view.findViewById(R.id.eliminar);

        botonEliminar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                AccesoFichero af = new AccesoFichero();

                try {
                    af.eliminarMarca(marca);        //eliminar de fichero
                    arrayMarcas.remove(position);   //eliminar de array
                    notifyDataSetChanged();

                    String text = "Marca eliminada";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

        botonDeporte.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String text = marca.getTipo().toString();
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

        //set values

        if (marca.getTipo().equals(Deporte.carrera)) {
            botonDeporte.setBackground(context.getResources().getDrawable(R.drawable.runner));
        } else {
            botonDeporte.setBackground(context.getResources().getDrawable(R.drawable.swimmer));
        }

        distancia.setText(marca.getDistancia() + " m.");
        tiempo.setText(marca.getTiempo());
        if (marca.getDescripcion() != null) descripcion.setText(marca.getDescripcion());

        return view;
    }
}
