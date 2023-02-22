package model;

public class City {
    long city_id;
    String cyti_name;

    public City(long city_id, String cyti_name) {
        this.city_id = city_id;
        this.cyti_name = cyti_name;
    }

    public long getCity_id() {
        return city_id;
    }

    public void setCity_id(long city_id) {
        this.city_id = city_id;
    }

    public String getCyti_name() {
        return cyti_name;
    }

    public void setCyti_name(String cyti_name) {
        this.cyti_name = cyti_name;
    }

    @Override
    public String toString() {
        return " город " + cyti_name + " с id = "+ city_id;
    }
}
