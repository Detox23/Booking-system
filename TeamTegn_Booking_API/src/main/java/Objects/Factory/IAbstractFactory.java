package Objects.Factory;

public interface IAbstractFactory<T> {
    T create(String type);
}
