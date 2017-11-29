package edu.gcccd.csis;
import org.junit.*;
import org.junit.Test;


import java.math.BigInteger;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by mark.wiggins on 11/29/2017.
 */
public class Project2Test {
    private static BigInteger genBigInteger(final NodeList<Integer> nodeList){
        final StringBuilder sb = new StringBuilder();
        for (final int i : nodeList) {
            sb.append(i);
        }
        return  new BigInteger(sb.toString());
    }
    private  static  NodeList<Integer> genNodeList(final String s){
        final NodeList<Integer> nodeList = new NodeList<>();
        for (final char c : s.toCharArray()) {
            nodeList.append(Character.getNumericValue(c));
        }
        return nodeList;
    }
    @Test
    public void main() throws Exception {
    }

    @Test
    public void addition() throws Exception {
    final NodeList<Integer> n1 = Project2.generateNumber(30);
    final NodeList<Integer> n2 = Project2.generateNumber(30);

    final BigInteger N1 = genBigInteger(n1);
    final BigInteger N2 = genBigInteger(n2);

    final NodeList<Integer> n3 = Project2.addition(n1,n2);
    final BigInteger N3 = N1.add(N2);

    assertEquals(N3, genBigInteger(n3));
    }

    @Test
    public void addition1() throws Exception {
    NodeList<Integer> n1 = genNodeList("007");
    NodeList<Integer> n2 = genNodeList("10");

    NodeList<Integer> n3 = new Project2().addition(n1,n2);
    assertEquals(new BigInteger("17"),genBigInteger(n3));

    assertEquals(3,n3.getLength());

    n1 = new NodeList<>();
    n2 = new NodeList<>();
    n3 = new Project2().addition(n1,n2);

    n1 = genNodeList("0");
    n2 = genNodeList("0");
    n3 = new Project2().addition(n1,n2);
    assertEquals(1,n3.getLength());
    assertEquals(new BigInteger("0"),genBigInteger(n3));

    n2 = new NodeList<>();
    n3 = new Project2().addition(n1,n2);
    assertEquals(1,n3.getLength());
    assertEquals( new BigInteger("0"),genBigInteger(n3));

    n3 = new Project2().addition(n2,n1);
    assertEquals(1,n3.getLength());
    assertEquals(new BigInteger("0"), genBigInteger(n3));
    }

    @Test
    public void save() throws Exception {
    }

    @Test
    public void load() throws Exception {
    }

    @Test
    public void reverse() throws Exception {
    }

}