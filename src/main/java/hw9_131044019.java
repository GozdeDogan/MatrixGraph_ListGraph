import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by GozdeDogan on 15.05.2017.
 */
public class hw9_131044019 {
    public static void main(String[] args) throws IOException {
    ////////////////////////////////////// TEST UNDIRECTED GRAPH //////////////////////////////////////////
        System.out.println("TESTING DIRECTED GRAPH:");

    ////////////////////////////////////////////// INITIALIZE ////////////////////////////////////////////
        File file1 = new File("graph1.txt");
        Scanner scan = new Scanner(file1);
        MatrixGraph graph1 = (MatrixGraph) AbstractGraph.createGraph(scan, true, "Matrix");
        scan.close();

        file1 = new File("graph2.txt");
        scan = new Scanner(file1);
        ListGraph graph2 = (ListGraph) AbstractGraph.createGraph(scan, true, "List");
        scan.close();
    ////////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////// TEST writeGraphToFile //////////////////////////////////////
        System.out.println("TESTING writeGraphToFile:");
        graph1.writeGraphToFile("result1.txt");
        graph2.writeGraphToFile("result2.txt");
        System.out.println("result1.txt ve result2.txt dosyalarini inceleyiniz!");
    ///////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////// TEST breadFirstSearch /////////////////////////////////////////
        System.out.println("\n\nTESTING breadFirstSearch For MatrixGraph: [(parent, child),.....]");
        int[] parent1 = graph1.breadthFirstSearch(0);
        System.out.println(writeParents(parent1, graph1.getNumV()).toString());

        System.out.println("\nTESTING breadFirstSearch For ListGraph:");
        int[] parent2 = graph2.breadthFirstSearch(0);
        System.out.println(writeParents(parent2, graph2.getNumV()).toString());
    /////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////// TEST addRandomEdgesToGraph //////////////////////////////////////
        System.out.println("\n\nTESTING addRandomEdgesToGraph For MatrixGraph:");
        int numOfInsertedEdges = graph1.addRandomEdgesToGraph(5);
        System.out.println("numOfInsertedEdges: " + numOfInsertedEdges + "\nMatrixGraph:");
        System.out.println(graph1.toString());

        System.out.println("\nTESTING addRandomEdgesToGraph For ListGraph:");
        numOfInsertedEdges = graph2.addRandomEdgesToGraph(3);
        System.out.println("numOfInsertedEdges: " + numOfInsertedEdges + "\nListGraph:");
        System.out.println(graph2.toString());
    /////////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////// TEST breadFirstSearch AFTER ADD EDGE //////////////////////////////////
        System.out.println("\n\nTESTING breadFirstSearch For MatrixGraph after add edge: [(parent, child),.....]");
        parent1 = graph1.breadthFirstSearch(0);
        System.out.println(writeParents(parent1, graph1.getNumV()).toString());

        System.out.println("\nTESTING breadFirstSearch For ListGraph after add edge:");
        parent2 = graph2.breadthFirstSearch(0);
        System.out.println(writeParents(parent2, graph2.getNumV()).toString());
    //////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////// TEST isBipartiteUndirectedGraph //////////////////////////////////
        System.out.println("\n\nTESTING isBipartiteUndirectedGraph For MatrixGraph:");
        System.out.println("Graph: " + graph1.toString());
        System.out.println("isBipartiteUndirectedGraph: " + graph1.isBipartiteUndirectedGraph());

        System.out.println("\nTESTING isBipartiteUndirectedGraph For ListGraph:");
        System.out.println("Graph: " + graph2.toString());
        System.out.println("isBipartiteUndirectedGraph: " + graph2.isBipartiteUndirectedGraph());
    ////////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////// TEST getConnectedComponentUndirectedGraph ////////////////////////////
        Graph[] graphs;

        System.out.println("\n\nTESTING getConnectedComponentUndirectedGraph For MatrixGraph:");
        System.out.println("Graph: " + graph1.toString());
        graphs = graph1.getConnectedComponentUndirectedGraph();
        System.out.println("getConnectedComponentUndirectedGraph: ");
        if(graphs != null)
            printGraph(graphs);
        else
            System.out.println("null");

        System.out.println("\nTESTING getConnectedComponentUndirectedGraph For ListGraph:");
        System.out.println("Graph: " + graph2.toString());
        graphs = graph1.getConnectedComponentUndirectedGraph();
        System.out.println("getConnectedComponentUndirectedGraph: ");
        if(graphs != null)
            printGraph(graphs);
        else
            System.out.println("null");
    //////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////


    //////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////// TEST UNDIRECTED GRAPH ////////////////////////////////////
        System.out.println("\n\n\n\nTESTING UNDIRECTED GRAPHS:");

    ///////////////////////////////////// INITIALIZE ////////////////////////////////////////////////
        file1 = new File("graph3.txt");
        scan = new Scanner(file1);
        MatrixGraph graph3 = (MatrixGraph) AbstractGraph.createGraph(scan, false, "Matrix");
        scan.close();

        file1 = new File("graph4.txt");
        scan = new Scanner(file1);
        ListGraph graph4 = (ListGraph) AbstractGraph.createGraph(scan, false, "List");
        scan.close();
    ///////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////// TEST writeGraphToFile /////////////////////////////////////////////
        System.out.println("TESTING writeGraphToFile:");
        graph3.writeGraphToFile("result3.txt");
        graph4.writeGraphToFile("result4.txt");
        System.out.println("result3.txt ve result4.txt dosyalarini inceleyiniz!");
    ///////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////// TEST getConnectedComponentUndirectedGraph ////////////////////////////////
        System.out.println("\n\nTESTING getConnectedComponentUndirectedGraph For MatrixGraph:");
        graphs = graph3.getConnectedComponentUndirectedGraph();
        System.out.println("getConnectedComponentUndirectedGraph: ");
        if(graphs != null)
            printGraph(graphs);
        else
            System.out.println("null");

        System.out.println("\nTESTING getConnectedComponentUndirectedGraph For ListGraph:");
        System.out.println("Graph: " + graph4.toString());
        graphs = graph4.getConnectedComponentUndirectedGraph();
        System.out.println("getConnectedComponentUndirectedGraph: ");
        if(graphs != null)
            printGraph(graphs);
        else
            System.out.println("null");
    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////// TEST breadFirstSearch ///////////////////////////////////////
        System.out.println("\n\nTESTING breadFirstSearch For MatrixGraph: [(parent, child),.....]");
        parent1 = graph3.breadthFirstSearch(0);
        System.out.println(writeParents(parent1, graph3.getNumV()).toString());

        System.out.println("\nTESTING breadFirstSearch For ListGraph:");
        parent2 = graph4.breadthFirstSearch(0);
        System.out.println(writeParents(parent2, graph4.getNumV()).toString());
        ////////////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////// TEST addRandomEdgesToGraph ////////////////////////////////////////
        System.out.println("\n\nTESTING addRandomEdgesToGraph For MatrixGraph:");
        numOfInsertedEdges = graph3.addRandomEdgesToGraph(5);
        System.out.println("numOfInsertedEdges: " + numOfInsertedEdges + "\nMatrixGraph:");
        System.out.println(graph3.toString());

        System.out.println("\nTESTING addRandomEdgesToGraph For ListGraph:");
        numOfInsertedEdges = graph4.addRandomEdgesToGraph(3);
        System.out.println("numOfInsertedEdges: " + numOfInsertedEdges + "\nListGraph:");
        System.out.println(graph4.toString());
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////// TEST breadFirstSearch AFTER ADD EDGE /////////////////////////////////////
        System.out.println("\n\nTESTING breadFirstSearch For MatrixGraph after add edge: [(parent, child),.....]");
        parent1 = graph3.breadthFirstSearch(0);
        System.out.println(writeParents(parent1, graph3.getNumV()).toString());

        System.out.println("\nTESTING breadFirstSearch For ListGraph after add edge:");
        parent2 = graph4.breadthFirstSearch(0);
        System.out.println(writeParents(parent2, graph4.getNumV()).toString());
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////// TEST isBipartiteUndirectedGraph ////////////////////////////////////////////
        System.out.println("\n\nTESTING isBipartiteUndirectedGraph For MatrixGraph:");
        System.out.println("Graph: " + graph3.toString());
        System.out.println("isBipartiteUndirectedGraph: " + graph3.isBipartiteUndirectedGraph());

        System.out.println("\nTESTING isBipartiteUndirectedGraph For ListGraph:");
        System.out.println("Graph: " + graph4.toString());
        System.out.println("isBipartiteUndirectedGraph: " + graph4.isBipartiteUndirectedGraph());
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    }

    /**
     * breadthFirstSearch icin yazildi.
     * breadthFirstSearch isleminden sonra parent arrayini gosterebilmek adina
     * @param parent yazdirilacak array
     * @param numOfVertex yazdirilacak arrayin eleman sayisi
     * @return string return edilir. Array stringe cevrilir ve return edilir.
     */
    private static String writeParents(int[] parent, int numOfVertex){
        String s = "[";
        for(int i= 0; i < parent.length; i++){
            s += "(";
            s += parent[i];
            s += ", ";
            s += i;
            s += ")";
            if(i != numOfVertex - 1)
                s += ", ";
            else
                i = parent.length;
        }
        s += "]";
        return s.toString();
    }

    /**
     * getConnectedComponentUndirectedGraph icin yazildi.
     * metodun cagirilmasi sonucu olusan graph arrayini yazdirabilmek icin.
     * @param graphs yazdirilacak graph arrayi
     */
    private static void printGraph(Graph[] graphs){
        for(int i=0; i<graphs.length; i++){
            if(graphs[i] != null)
                System.out.println(i + ".graph:\n" + graphs[i].toString());
        }
    }
}
