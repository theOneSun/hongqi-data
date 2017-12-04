package com.idata3d.hongqi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @author sunjian.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarketClassified
{
    private String id;
    private String marketName;
    private String group;
    private String brand;
    private String series;
    private String luxury;
    private String energy;
    private String carType;
    private String carSegment;
    private String carSubSegment;
    private String carSeriesCode;
    private String domesticImport;

    //新包装一个对象相当于复制对象
    public MarketClassified(MarketClassified marketClassified)
    {
        this.id = marketClassified.id;
        this.marketName = marketClassified.marketName;
        this.group = marketClassified.group;
        this.brand = marketClassified.brand;
        this.series = marketClassified.series;
        this.luxury = marketClassified.luxury;
        this.energy = marketClassified.energy;
        this.carType = marketClassified.carType;
        this.carSegment = marketClassified.carSegment;
        this.carSubSegment = marketClassified.carSubSegment;
        this.carSeriesCode = marketClassified.carSeriesCode;
        this.domesticImport = marketClassified.domesticImport;
    }

   /* @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof MarketClassified)) {
            return false;
        }
        MarketClassified marketClassified = (MarketClassified) o;
        return
                Objects.equals(id, marketClassified.id) &&
                Objects.equals(marketName, marketClassified.marketName) &&
                Objects.equals(group, marketClassified.group) &&
                Objects.equals(brand, marketClassified.brand) &&
                Objects.equals(series, marketClassified.series) &&
                Objects.equals(luxury, marketClassified.luxury) &&
                Objects.equals(energy, marketClassified.energy) &&
                Objects.equals(carType, marketClassified.carType) &&
                Objects.equals(carSegment, marketClassified.carSegment) &&
                Objects.equals(carSubSegment, marketClassified.carSubSegment) &&
                Objects.equals(carSeriesCode, marketClassified.carSeriesCode) &&
                Objects.equals(domesticImport, marketClassified.domesticImport)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, marketName, group,brand,series,luxury,energy,carType,carSegment,carSubSegment,carSeriesCode,domesticImport);
    }*/
}
