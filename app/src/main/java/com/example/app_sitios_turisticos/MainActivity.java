package com.example.app_sitios_turisticos;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.os.Bundle;
import android.provider.MediaStore;

import com.example.app_sitios_turisticos.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    Bitmap bitmap;

    ActivityResultLauncher<Intent> activityResultLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnGuardar.setOnClickListener(view -> {
        String nombre = binding.txtNombre.getText().toString();
            String ubicacion = binding.txtUbicacion.getText().toString();
            String informacion = binding.txtInformacion.getText().toString();
            float valoracion = binding.rtbValoracion.getRating();

            abrirActividaddetalle(nombre,ubicacion,informacion,valoracion);
        });
        activity_launcher();
        binding.imgSitio.setOnClickListener(view -> {
            abrircamara();
        });

    }

    private void abrircamara() {
        Intent camaraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //startActivity(camaraIntent);
       // startActivityForResult(camaraIntent, 1000);
        activityResultLauncher.launch((camaraIntent));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode== Activity.RESULT_OK && requestCode==1000){
            if (data != null){
                bitmap =data.getExtras().getParcelable("data");
                binding.imgSitio.setImageBitmap(bitmap);
            }
        }
    }

    private void abrirActividaddetalle(String nom, String ubi, String inf, float valor){
        Intent intents = new Intent(this,ActivityDetalle.class);
        Sitioturistico sitio = new Sitioturistico(nom,ubi,inf,valor);

        intents.putExtra(ActivityDetalle.SITIO_TURISTICO_KEY,sitio);
        intents.putExtra(ActivityDetalle.BITMAP_KEY,bitmap);
        startActivity(intents);
    }

    public void activity_launcher(){
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK) {
                    if (result.getData() != null) {
                        bitmap = result.getData().getExtras().getParcelable("data");
                        binding.imgSitio.setImageBitmap(bitmap);
                    }
                }
            }

        });

    }
}