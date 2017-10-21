package widget.small.com.smallwidget.bean;

import greendao.bean.Weight;

/**
 * Description:
 * <p>
 * Author: Kosmos
 * Time: 2017/4/26 002615:37
 * Email:ZeroProject@foxmail.com
 * Events:
 */
public class WeightDto {
    private Weight weight;
    private Weight weightOld;
    private boolean isMonthSame;
    private boolean isYearSame;
    private boolean isDaySame;

    public Weight getWeight() {
        return weight;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }

    public Weight getWeightOld() {
        return weightOld;
    }

    public void setWeightOld(Weight weightOld) {
        this.weightOld = weightOld;
    }

    public boolean isYearSame() {
        if (weightOld == null) {
            isYearSame = false;
        } else {
            if (weight.getYear() == weightOld.getYear()) {
                isYearSame = true;
            } else {
                isYearSame = false;
            }
        }
        return isYearSame;
    }

    public void setYearSame(boolean yearSame) {
        isYearSame = yearSame;
    }

    public boolean isMonthSame() {
        if (weightOld == null) {
            isMonthSame = false;
        } else {
            if (weight.getYear() == weightOld.getYear() && weight.getMonth() == weightOld.getMonth()) {
                isMonthSame = true;
            } else {
                isMonthSame = false;
            }
        }

        return isMonthSame;
    }

    public void setMonthSame(boolean monthSame) {
        isMonthSame = monthSame;
    }

    public boolean isDaySame() {
        if (weightOld == null) {
            isDaySame = false;
        } else {
            if (weight.getYear() == weightOld.getYear() && weight.getMonth() == weightOld.getMonth() && weight.getDay() == weightOld.getDay()) {
                isMonthSame = true;
            } else {
                isMonthSame = false;
            }
        }

        return isDaySame;
    }

    public void setDaySame(boolean daySame) {
        isDaySame = daySame;
    }
}
