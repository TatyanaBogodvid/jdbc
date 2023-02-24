package model;

public class City {
    private long city_id;
    private String cityName;

    public City(long city_id, String cyti_name) {
        this.city_id = city_id;
        this.cityName = cyti_name;
    }

    public long getCity_id() {
        return city_id;
    }

    public void setCity_id(long city_id) {
        this.city_id = city_id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return " город " + cityName + " с id = "+ city_id;
    }
}
