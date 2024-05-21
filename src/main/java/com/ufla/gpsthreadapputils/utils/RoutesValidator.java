package com.ufla.gpsthreadapputils.utils;

import com.ufla.gpsthreadapputils.models.Region;

import java.util.List;

public class RoutesValidator {

    // Verifica se a posição de um novo marcador está dentro de um raio de 30 metros de qualquer marcador existente.
    public static boolean isWithinRadiusOfExistingMarker(Region newMarkerPosition, List<Region> regions) {
        for (Region marker : regions) {
            // Calcula a distância entre o novo marcador e cada marcador existente.
            double distance = calculateDistanceBetweenPoints(marker, newMarkerPosition);
            // Se a distância for menor ou igual a 30 metros, retorna verdadeiro.
            if (distance <= 30) {
                return true;
            }
        }
        // Se nenhum marcador existente estiver dentro do raio, retorna falso.
        return false;
    }
//
    // Calcula a distância entre dois pontos geográficos usando a fórmula de Haversine.
    private static double calculateDistanceBetweenPoints(Region point1, Region point2) {
        final int R = 6371000; // Raio da Terra em metros
        // Converte as coordenadas de latitude e longitude de graus para radianos.
        double lat1 = Math.toRadians(point1.getLatitude());
        double lon1 = Math.toRadians(point1.getLongitude());
        double lat2 = Math.toRadians(point2.getLatitude());
        double lon2 = Math.toRadians(point2.getLongitude());
        // Calcula a diferença das coordenadas.
        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;
        // Aplica a fórmula de Haversine para calcular a distância entre os pontos.
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        // Calcula a distância total em metros.
        double distance = R * c;
        return distance;
    }
}

