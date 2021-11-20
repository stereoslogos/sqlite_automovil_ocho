package com.example.sqlite_automovil_ocho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText jetplaca,jetmarca,jetmodelo,jetvalor;
    Button jbtguardar,jbtconsultar,jbtlimpiar,jbteliminar,jbtanular;
    long resp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        jetplaca = findViewById(R.id.etplaca);
        jetmarca = findViewById(R.id.etmarca);
        jetmodelo = findViewById(R.id.etmodelo);
        jetvalor = findViewById(R.id.etvalor);
        jbtguardar = findViewById(R.id.btguardar);
        jbtconsultar = findViewById(R.id.btconsultar);
        jbtanular = findViewById(R.id.btanular);
        jbteliminar = findViewById(R.id.bteliminar);
        jbtlimpiar = findViewById(R.id.btlimpiar);
    }

    public void limpiar_campos(){
        jetplaca.setText("");
        jetvalor.setText("");
        jetmodelo.setText("");
        jetmarca.setText("");
        jetplaca.requestFocus();
    }

    public void guardar(View view){
        String placa,marca,modelo,valor;

        placa = jetplaca.getText().toString();
        marca = jetmarca.getText().toString();
        modelo = jetmodelo.getText().toString();
        valor = jetvalor.getText().toString();

        if (placa.isEmpty() || marca.isEmpty() || modelo.isEmpty() || valor.isEmpty()){
            Toast.makeText(this, "Todos los datos son requeridos", Toast.LENGTH_SHORT).show();
            jetplaca.requestFocus();
        }
        else{
            AdminSqliteOpenHelper admin = new AdminSqliteOpenHelper(this,"concesionario.db",null,1);
            SQLiteDatabase db = admin.getWritableDatabase();
            ContentValues dato=new ContentValues();
            dato.put("placa",placa);
            dato.put("marca",marca);
            dato.put("modelo",modelo);
            dato.put("valor",valor);
            resp=db.insert("TblAutomovil",null,dato);

            if (resp > 0){
                Toast.makeText(this, "Registro guardado", Toast.LENGTH_SHORT).show();
                limpiar_campos();
            }
            else
                Toast.makeText(this, "Error guardando registro", Toast.LENGTH_SHORT).show();
            db.close();
        }
    }

    public void consultar(View view){
        String placa;
        placa=jetplaca.getText().toString();
        if (placa.isEmpty()){
            Toast.makeText(this, "La placa es requerida para la consulta", Toast.LENGTH_SHORT).show();
            jetplaca.requestFocus();
        }
        else{
            AdminSqliteOpenHelper admin=new AdminSqliteOpenHelper(this,"concesionario.db",null,1);
            SQLiteDatabase db=admin.getReadableDatabase();

            db.close();
        }
    }

    public void limpiar(View view){
        limpiar_campos();
    }
}