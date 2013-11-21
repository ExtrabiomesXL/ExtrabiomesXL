package extrabiomes.lib;

public class Vector3
{
    public Vector3()
    {
        xyz[0] = 0;
        xyz[1] = 0;
        xyz[2] = 0;
    }
    
    public Vector3(int x, int y, int z)
    {
        xyz[0] = x;
        xyz[1] = y;
        xyz[2] = z;
    }
    
    public Vector3(int[] array)
    {
        if (array.length != 3)
            throw new RuntimeException("Must create vector with 3 element array");
        
        xyz[0] = array[0];
        xyz[1] = array[1];
        xyz[2] = array[2];
    }
    
    public int[] array()
    {
        return (int[]) xyz.clone();
    }
    
    public boolean equals(Object obj)
    {
        if (obj instanceof Vector3)
        {
            Vector3 rhs = (Vector3) obj;
            
            return xyz[0] == rhs.xyz[0] &&
                    xyz[1] == rhs.xyz[1] &&
                    xyz[2] == rhs.xyz[2];
        }
        else
        {
            return false;
        }
        
    }
    
    public int x()
    {
        return xyz[0];
    }
    
    public int y()
    {
        return xyz[1];
    }
    
    public int z()
    {
        return xyz[2];
    }
    
    public String toString()
    {
        return "( " + xyz[0] + " " + xyz[1] + " " + xyz[2] + " )";
    }
    
    int xyz[] = new int[3];
}