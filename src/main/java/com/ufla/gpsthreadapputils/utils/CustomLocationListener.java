package com.ufla.gpsthreadapputils.utils;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.TextView;

// Implementa um ouvinte de localização personalizado para tratar atualizações de localização.
public class CustomLocationListener implements LocationListener {
//
    // TextViews para exibir as coordenadas de latitude e longitude na interface do usuário.
    private TextView latitudeTextView;
    private TextView longitudeTextView;

    // Construtor que associa os TextViews da interface do usuário aos atributos da classe.
    public CustomLocationListener(TextView latitudeTextView, TextView longitudeTextView) {
        this.latitudeTextView = latitudeTextView;
        this.longitudeTextView = longitudeTextView;
    }

    // Método chamado quando a localização do dispositivo é atualizada.
    @Override
    public void onLocationChanged(Location location) {
        // Obtém as coordenadas de latitude e longitude da localização atualizada.
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        // Atualiza os TextViews com as novas coordenadas de latitude e longitude.
        latitudeTextView.setText("Latitude: " + latitude);
        longitudeTextView.setText("Longitude: " + longitude);
    }

    // Método chamado quando o status do provedor de localização muda.
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // Este método pode ser utilizado para responder a mudanças de status no provedor de localização.
    }

    // Método chamado quando um provedor de localização é ativado (por exemplo, GPS).
    @Override
    public void onProviderEnabled(String provider) {
        // Aqui pode-se adicionar lógica para lidar com a ativação do provedor de localização.
    }

    // Método chamado quando um provedor de localização é desativado.
    @Override
    public void onProviderDisabled(String provider) {
        // Aqui pode-se adicionar lógica para lidar com a desativação do provedor de localização.
    }
}
