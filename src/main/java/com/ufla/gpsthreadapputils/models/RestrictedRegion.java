package com.ufla.gpsthreadapputils.models;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RestrictedRegion extends Region {
    private Region mainRegion;
    private boolean restricted;

    public RestrictedRegion(Region mainRegion, boolean restricted) {
        super();
        this.mainRegion = mainRegion;
        this.restricted = restricted;
    }

    public RestrictedRegion() {
        super();
    }

    public Region getMainRegion() {
        return this.mainRegion;
    }

    public boolean getRestricted() {
        return this.restricted;
    }

    public void setMainRegion(Region mainRegion) {
        this.mainRegion = mainRegion;
    }

    public void setRestricted(boolean restricted) {
        this.restricted = restricted;
    }

    @Override
    protected int getMinimalDistance() {
        return 5;
    }

    @Override
    public boolean isWithinRadiusOfExistingMarker(Map<Region, List<Region>> regions) {
        // Primeiro verifica se há uma região pai associada e calcula a distância até ela
        if (!Objects.isNull(this.mainRegion)) {
            double distanceBetweenParent = this.calculateDistanceBetweenPoints(this.mainRegion);
            // Se a distância até a região pai for menor ou igual à distância mínima, retorna verdadeiro
            if (distanceBetweenParent <= this.getMinimalDistance()) {
                return true;
            }
        }

        // Itera sobre todas as regiões principais no mapa
        for (Region marker : regions.keySet()) {
            // Calcula a distância entre a região atual e cada região principal
            double distance = calculateDistanceBetweenPoints(marker);
            // Se a distância for menor ou igual à distância mínima, retorna verdadeiro
            if (distance <= this.getMinimalDistance()) {
                return true;
            }
            // Itera sobre todas as sub-regiões de cada região principal
            for (Region subRegion : regions.get(marker)) {
                double distanceBetweenSubMarker = calculateDistanceBetweenPoints(subRegion);
                // Se a distância até qualquer sub-região for menor ou igual à distância mínima, retorna verdadeiro
                if (distanceBetweenSubMarker <= this.getMinimalDistance()) {
                    return true;
                }
            }
        }

        // Retorna falso se nenhuma região ou sub-região estiver dentro do raio mínimo
        return false;
    }

}
