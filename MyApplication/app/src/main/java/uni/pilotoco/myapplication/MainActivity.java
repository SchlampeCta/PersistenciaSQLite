package uni.pilotoco.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    DataSQLite myDB;
    EditText usuario, fecha, descripcion;
    Button guardar;
    Button btnVerDatos;

    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB = new DataSQLite(this);
        usuario = findViewById(R.id.editTextUsuario);
        fecha = findViewById(R.id.editTextFecha);
        descripcion = findViewById(R.id.editTextDescripcion);
        guardar = findViewById(R.id.btnGuardar);
        btnVerDatos = findViewById(R.id.btnVerDatos);
        AddData();
        viewAll();

    }

    public void AddData(){
        guardar.setOnClickListener(
            new View.OnClickListener(){
                @Override
                public void onClick (View view) {
                   boolean insertar= myDB.insertData(usuario.getText().toString(),
                                                     fecha.getText().toString(),
                                                     descripcion.getText().toString());
                   if (insertar==true){
                       Toast.makeText(MainActivity.this, "Datos insertados", Toast.LENGTH_LONG).show();
                   }else
                       Toast.makeText(MainActivity.this, "No se insertaron los datos", Toast.LENGTH_LONG).show();

                }
            }
        );

    }

    public void viewAll(){
        btnVerDatos.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDB.getAllData();
                        if (res.getCount()==0){
                            showMessage("Error", "No se encontro");
                            return;
                        }else {
                            StringBuffer buffer= new StringBuffer();
                            while (res.moveToNext()){
                                buffer.append("Id"+res.getString(0)+ "\n");
                                buffer.append("Usuario"+res.getString(1)+ "\n");
                                buffer.append("Fecha"+res.getString(2)+ "\n");
                                buffer.append("Descripcion"+res.getString(3)+ "\n");

                            }
                            showMessage("Datos",buffer.toString());
                        }
                    }
                }
        );
    }

    public void showMessage (String tittle, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(tittle);
        builder.setMessage(message);
        builder.show();

    }

}