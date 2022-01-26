package intro;

public interface IOrderedPair <T> {

	public T getFirst();

    public T getSecond();
    
    public void setFirst (T first);
    
    public void setSecond (T second);
    
    public void swap();
    
    public IOrderedPair <T> copy();
}
