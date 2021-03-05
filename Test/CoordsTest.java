import JFigure.com.Coords;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Vector;

public class CoordsTest {

    @Test
    public void finding_Coords_Instance_In_Vector(){
        //given
        Vector<Coords> coordsVector=new Vector<Coords>();
        coordsVector.add(new Coords(23,4));
        coordsVector.add(new Coords(11,3));
        coordsVector.add(new Coords(1,1));
        coordsVector.add(new Coords(5,5));
        //when
        boolean firstResult=coordsVector.contains(new Coords(11,3));
        boolean secondResult=coordsVector.contains(new Coords(1,1));
        boolean thirdResult=coordsVector.contains(new Coords(3,11));
        //then
        Assertions.assertTrue(firstResult);
        Assertions.assertTrue(secondResult);
        Assertions.assertFalse(thirdResult);

    }
}
