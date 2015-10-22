package sw.es.model.mapper.interfaces;

hacerlo generico no con los weather....
public interface WeatherMapper<T> {
    Weather map(T t);
    T map(Weather weather);
}