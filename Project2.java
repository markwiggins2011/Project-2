package edu.gcccd.csis;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Project2 {

    static NodeList<Integer> generateNumber(final int maxLength) {
        final NodeList<Integer> nodeList = new NodeList<>();
        final int len = 1 + new Random().nextInt(maxLength);
        for (int i = 0; i < len; i++) {
            nodeList.append(new Random().nextInt(10));
        }
        System.out.print("Generated Number: ");
        print(nodeList);
        return nodeList;
    }

    /**
     * Prints a very long number to System.out
     *
     * @param nodeList NodeList<Integer>
     */
    static void print(final NodeList<Integer> nodeList) {
        for (final Integer i : nodeList) {
            System.out.print(i);
        }
        System.out.println();
    }

    public static void main(final String[] args) throws IOException {
        final int L = 30;

        final NodeList<Integer> n1 = generateNumber(L); // (head 1st) e.g. 3457
        final NodeList<Integer> n2 = generateNumber(L); // (head 1st) e.g. 682

        final Project2 project = new Project2();

        print(project.addition(n1, n2)); //  n1+n2, e.g. 4139

        final NodeList<NodeList<Integer>> listOfLists = new NodeList<>();
        for (int i = 0; i < L; i++) {
            listOfLists.append(generateNumber(L));
        }
        project.save(project.addition(listOfLists.iterator()), "result.bin");
        print(project.load("result.bin"));
    }

    /**
     * Add two very long numbers
     *
     * @param nodeList1 NodeList&lt;Integer&gt;
     * @param nodeList2 NodeList&lt;Integer&gt;
     * @return nodeList representing the sum (add) of nodeList1 and nodeList2, without leading '0'
     */
    public static NodeList<Integer> addition(NodeList<Integer> nodeList1, NodeList<Integer> nodeList2) {
        NodeList<Integer> tempList = new NodeList<>();
        int overflow = 0;
        Iterator<Integer> rev1 = reverse(nodeList1.iterator()).iterator();
        Iterator<Integer> rev2 = reverse(nodeList2.iterator()).iterator();

        while (rev1.hasNext() || rev2.hasNext())
        {
            int t1 = (rev1.hasNext() ? rev1.next() : 0);
            int t2 = (rev2.hasNext() ? rev2.next() : 0);

            tempList.append((t1 + t2 + overflow) % 10);
            overflow = (t1 + t2 + overflow) / 10;
        }

        while (overflow > 0) // while instead of if for impossible edge cases-- i.e. overflow is 572
        {
            tempList.append(overflow % 10);
            overflow /= 10;
        }

        return reverse(tempList.iterator());
    }


    /**
     * Add very long numbers
     *
     * @param iterator NodeList&lt;Integer&gt;
     * @return nodeList representing the sum (add) of all very long numbers, without leading '0'
     */
    public NodeList<Integer> addition(Iterator<NodeList<Integer>> iterator) {
        NodeList<Integer> tempList = new NodeList<>();
        while (iterator.hasNext())
        {
            tempList = addition(tempList, iterator.next());
        }
        return tempList;
    }

    /**
     * Saves a very large number as a file
     *
     * @param nodeList NodeList&lt;Integer&gt;
     * @param fileName String
     */
    public void save(NodeList<Integer> nodeList, String fileName) throws IOException {
        File sText = new File(fileName);
        FileOutputStream is = new FileOutputStream(sText);
        OutputStreamWriter osw = new OutputStreamWriter(is);
        Writer w = new BufferedWriter(osw);
        for (Integer i : nodeList) {
            w.write(i.toString());
        }
        w.close();
    }

    /**
     * Loads a very large number from a file
     *
     * @param fileName String
     * @return NodeList&lt;Integer&gt;
     */
    public NodeList<Integer> load(final String fileName) throws IOException {
        {
                NodeList<Integer> list = new NodeList<>();
                String line;
                BufferedReader in;

                in = new BufferedReader(new FileReader(fileName));
                line = in.readLine();
                for (int i = 0; i < line.length(); i++)
                {
                    list.append(Character.getNumericValue(line.charAt(i)));
                }
                in.close();
            return list;
        }
    }

    public static NodeList<Integer> reverse(Iterator<Integer> iter)
    {
        NodeList<Integer> temp;
        if (iter.hasNext()) {
            final int i = iter.next();
            temp = reverse(iter);
            temp.append(i);
            return temp;
        }
        else
        {
            temp = new NodeList<>();
            return temp;
        }
    }
}