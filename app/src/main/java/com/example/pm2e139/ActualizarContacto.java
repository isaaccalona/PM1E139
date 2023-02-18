package com.example.pm2e139;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.pm2e139.configuracion.Operaciones;
import com.example.pm2e139.configuracion.SQLiteconexion;
import com.example.pm2e139.transacciones.Pais;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActualizarContacto extends AppCompatActivity {

    SQLiteconexion conexion = new SQLiteconexion(this,Operaciones.NameDatabase,null,1);

    Button atrasb,editarb;
    EditText codigocontacto, nombrecontacto, telefonocontacto, notacontacto;
    Spinner paisc;
    ArrayList<String> lista_paises;
    ArrayList<Pais> lista;

    int codigoPaisSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_contacto);
        atrasb = (Button) findViewById(R.id.acbtnatras);
        codigocontacto = (EditText)findViewById(R.id.txtActCodigo);
        nombrecontacto = (EditText)findViewById(R.id.txtActN);
        telefonocontacto = (EditText)findViewById(R.id.ntelefono);
        notacontacto = (EditText)findViewById(R.id.NotaN);
        paisc = (Spinner)findViewById(R.id.cmbActSeleccionarPais);
        editarb = (Button) findViewById(R.id.acbtnEdit);


        codigocontacto.setText(getIntent().getStringExtra("codigo"));
        nombrecontacto.setText(getIntent().getStringExtra("nombreContacto"));
        telefonocontacto.setText(getIntent().getStringExtra("numeroContacto"));
        notacontacto.setText(getIntent().getStringExtra("notaContacto"));

        ObtenerListaPaises();

        ArrayAdapter<CharSequence> adp = new ArrayAdapter(this, android.R.layout.simple_spinner_item,lista_paises);
        paisc.setAdapter(adp);

        paisc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                String cadena = adapterView.getSelectedItem().toString();

                //Quitar los caracteres del combobox para obtener solo el codigo del pais
                codigoPaisSeleccionado = Integer.valueOf(extraerNumeros(cadena).toString().replace("]","").replace("[",""));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        atrasb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ListaContactos.class);
                startActivity(intent);
            }
        });

        editarb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditarContacto();
            }
        });
    }

    List<Integer> extraerNumeros(String cadena) {
        List<Integer> todosLosNumeros = new ArrayList<Integer>();
        Matcher encuentrador = Pattern.compile("\\d+").matcher(cadena);
        while (encuentrador.find()) {
            todosLosNumeros.add(Integer.parseInt(encuentrador.group()));
        }
        return todosLosNumeros;
    }


    private void ObtenerListaPaises() {
        Pais pais = null;
        lista = new ArrayList<Pais>();
        SQLiteDatabase db = conexion.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Operaciones.tblPaises,null);

        while (cursor.moveToNext())
        {
            pais = new Pais();

            pais.setCodigo(cursor.getString(0));
            pais.setNombrePais(cursor.getString(1));

            lista.add(pais);
        }

        cursor.close();

        fillCombo();

    }

    private void fillCombo() {
        lista_paises = new ArrayList<String>();

        for (int i=0; i<lista.size();i++)
        {
            lista_paises.add(lista.get(i).getNombrePais()+" ( "+lista.get(i).getCodigo()+" )");
        }
    }

    private void EditarContacto() {

        SQLiteDatabase db = conexion.getWritableDatabase();

        String ObjCodigo = codigocontacto.getText().toString();

        ContentValues valores = new ContentValues();

        valores.put(Operaciones.nombreCompleto, nombrecontacto.getText().toString());
        valores.put(Operaciones.telefono, telefonocontacto.getText().toString());
        valores.put(Operaciones.notaContacto, notacontacto.getText().toString());
        valores.put(Operaciones.pais, codigoPaisSeleccionado);

        try {
            db.update(Operaciones.contactostabla,valores, Operaciones.id +" = "+ ObjCodigo, null);
            db.close();
            Toast.makeText(getApplicationContext(),"Se actualizo correctamente", Toast.LENGTH_SHORT).show();


            Intent intent = new Intent(this, ListaContactos.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();


        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"No se actualizo", Toast.LENGTH_SHORT).show();
        }

    }
}