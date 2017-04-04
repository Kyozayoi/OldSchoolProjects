/*
Name: Anthony Shih
PID: A11295870
Login: cs12wei
*/

package hw6;
/*
It's my interface
*/
public interface dHeapInterface<T extends java.lang.Comparable<? super T>>
{
    public void add(T o);
    
    public T removeSmallest();
    
    public int size();
    
}
