package com.example.app_sitios_turisticos;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.ImageFormat;
import android.os.Bundle;

import com.example.app_sitios_turisticos.databinding.ActivityDetalleBinding;
import com.example.app_sitios_turisticos.databinding.ActivityMainBinding;

public class ActivityDetalle extends AppCompatActivity {

    public static final String SITIO_TURISTICO_KEY="sitioturistico";
    public static final String BITMAP_KEY="bitmap";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetalleBinding binding = ActivityDetalleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle extras = getIntent().getExtras();
        Sitioturistico sitio = extras.getParcelable(SITIO_TURISTICO_KEY);
        binding.setSitioturistico(sitio);

        Bitmap bitmap=extras.getParcelable(BITMAP_KEY);
        if (bitmap!=null){
            binding.imgSitio.setImageBitmap(bitmap);
        }

    }
}