package com.ufla.gpsthreadapputils.models;

import com.ufla.gpsthreadapputils.utils.RoutesValidator;

import java.util.List;
import java.util.Map;

public class Region extends RoutesValidator {
    // Atributos privados da classe Region
    private String name;       // Nome da região
    private double latitude;   // Latitude geográfica da região
    private double longitude;  // Longitude geográfica da região
    private int user;          // Identificador do usuário associado à região
    private long timestamp;    // Timestamp da criação ou modificação da região

    /**
     * Construtor da classe Region.
     * Inicializa uma nova instância de Region com os valores específicos para nome, coordenadas, usuário e timestamp.
     *
     * @param name Nome da região.
     * @param latitude Latitude geográfica da região.
     * @param longitude Longitude geográfica da região.
     * @param user Identificador do usuário responsável pela região.
     * @param timestamp Timestamp da criação ou última modificação da região.
     */
    public Region(String name, double latitude, double longitude, int user, long timestamp) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.user = user;
        this.timestamp = timestamp;
    }


    public Region() {
    }

    public String getName() {
        return this.name;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public int getUser() {
        return this.user;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Verifica se a região atual está dentro do raio mínimo de distância de qualquer marcador existente ou suas sub-regiões.
     * @param regions Mapa de regiões principais para suas listas de sub-regiões.
     * @return boolean Retorna true se a região estiver dentro do raio mínimo de alguma região ou sub-região existente, caso contrário, retorna false.
     */
    public boolean isWithinRadiusOfExistingMarker(Map<Region, List<Region>> regions) {
        // Itera sobre cada região principal no mapa
        for (Region marker : regions.keySet()) {
            // Calcula a distância entre a região atual e a região principal
            double distance = this.calculateDistanceBetweenPoints(marker);
            // Verifica se a distância é menor ou igual à distância mínima permitida
            if (distance <= this.getMinimalDistance()) {
                return true;  // Retorna verdadeiro se a região estiver dentro do raio mínimo
            }
            // Itera sobre todas as sub-regiões da região principal
            for(Region subMarker : regions.get(marker)) {
                // Calcula a distância entre a região atual e a sub-região
                double distanceSubRegions = this.calculateDistanceBetweenPoints(subMarker);
                // Verifica se a distância é menor ou igual à distância mínima permitida
                if (distanceSubRegions <= this.getMinimalDistance()) {
                    return true;  // Retorna verdadeiro se a região estiver dentro do raio mínimo
                }
            }
        }
        // Retorna falso se nenhuma região ou sub-região estiver dentro do raio mínimo
        return false;
    }


    /**
     * Calcula a distância entre esta região e outra região especificada usando a fórmula de Haversine.
     * A fórmula de Haversine calcula a distância entre dois pontos em uma esfera dada suas latitudes e longitudes expressas em valores radianos.
     *
     * @param point1 A outra região para a qual a distância será calculada.
     * @return double Distância entre os dois pontos em metros.
     */
    public double calculateDistanceBetweenPoints(Region point1) {
        final int R = 6371000; // Raio médio da Terra em metros

        // Converte as coordenadas de latitude e longitude de ambos os pontos para radianos
        double lat1 = Math.toRadians(point1.getLatitude());
        double lon1 = Math.toRadians(point1.getLongitude());
        double lat2 = Math.toRadians(this.getLatitude());
        double lon2 = Math.toRadians(this.getLongitude());

        // Calcula a diferença de latitude e longitude
        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        // Aplica a fórmula de Haversine para calcular a distância entre os pontos
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Calcula a distância total em metros
        double distance = R * c;
        return distance;
    }


    protected int getMinimalDistance() {
        return 30;
    }

}
