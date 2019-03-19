
package testJunit;

import junit.framework.TestCase; 


public class testCase extends TestCase {
    Triangle Tri = new Triangle();
    String equilateral = "该三角形为等边三角形";
    String isosceles = "该三角形为等腰三角形";
    String scalene = "该三角形为不规则三角形";
    String nonTri = "这不是一个三角形";
    String res;

    public void testEquilateral(){
        res = Tri.check(1, 1, 1);
        assertEquals(equilateral, res);
    }

    public void testIsosceles(){
        res = Tri.check(1, 1, 2);
        assertEquals(isosceles, res);
    }

    public void testScalene(){
        res = Tri.check(2, 3, 4);
        assertEquals(scalene, res);
    }

    public void testNonTri(){
        res = Tri.check(1, 2, 3);
        assertEquals(nonTri, res);
    }

}