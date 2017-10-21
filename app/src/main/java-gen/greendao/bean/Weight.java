package greendao.bean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "WEIGHT".
 */
public class Weight {

    private Long id;
    private String name;
    private Float value;
    private Integer unit;
    private String time;
    private Integer year;
    private Integer month;
    private Integer day;
    private Integer hour;
    private Integer min;
    private String hour_str;
    private Boolean defaultVaule;

    public Weight() {
    }

    public Weight(Long id) {
        this.id = id;
    }

    public Weight(Long id, String name, Float value, Integer unit, String time, Integer year, Integer month, Integer day, Integer hour, Integer min, String hour_str, Boolean defaultVaule) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.unit = unit;
        this.time = time;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.min = min;
        this.hour_str = hour_str;
        this.defaultVaule = defaultVaule;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public String getHour_str() {
        return hour_str;
    }

    public void setHour_str(String hour_str) {
        this.hour_str = hour_str;
    }

    public Boolean getDefaultVaule() {
        return defaultVaule;
    }

    public void setDefaultVaule(Boolean defaultVaule) {
        this.defaultVaule = defaultVaule;
    }

}
