package com.ufla.gpsthreadapputils.models;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SubRegion extends Region {
    private Region mainRegion;


    public SubRegion(Region mainRegion) {
        super();
        this.mainRegion = mainRegion;
    }

    public SubRegion() {
        super();
    }

    public Region getMainRegion() {
        return this.mainRegion;
    }

    public void setMainRegion(Region mainRegion) {
        this.mainRegion = mainRegion;
    }

    @Override
    protected int getMinimalDistance() {
        return 5;
    }

    @Override
    /**
     * Verifica se a região atual está dentro do raio mínimo de distância de qualquer outra região ou sub-região existente,
     * ou de uma região principal especificada.
     *
     * @param regions Mapa contendo regiões e suas respectivas sub-regiões.
     * @return boolean Retorna true se a região atual estiver dentro do raio mínimo de qualquer região ou sub-região existente,
     *         ou de uma região principal, caso contrário retorna false.
     */
    public boolean isWithinRadiusOfExistingMarker(Map<Region, List<Region>> regions) {
        // Verifica inicialmente se há uma região principal associada e calcula a distância até ela.
        if (!Objects.isNull(this.mainRegion)) {
            double distanceBetweenParent = this.calculateDistanceBetweenPoints(this.mainRegion);
            // Retorna verdadeiro se a distância até a região principal for menor ou igual à distância mínima permitida.
            if (distanceBetweenParent <= this.getMinimalDistance()) {
                return true;
            }
        }

        // Itera sobre todas as regiões principais do mapa.
        for (Region marker : regions.keySet()) {
            double distance = this.calculateDistanceBetweenPoints(marker);
            // Verifica se a distância entre a região atual e a região principal é menor ou igual à distância mínima.
            if (distance <= this.getMinimalDistance()) {
                return true;
            }

            // Itera sobre todas as sub-regiões associadas à região principal.
            for (Region subRegion : regions.get(marker)) {
                double distanceBetweenSubMarker = this.calculateDistanceBetweenPoints(subRegion);
                // Verifica se a distância entre a região atual e cada sub-região é menor ou igual à distância mínima.
                if (distanceBetweenSubMarker <= this.getMinimalDistance()) {
                    return true;
                }
            }
        }

        // Retorna falso se não encontrar nenhuma região ou sub-região dentro do raio mínimo.
        return false;
    }

}
