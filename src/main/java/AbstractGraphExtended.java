import java.io.*;
import java.util.*;

/**
 * Created by GozdeDogan on 15.05.2017.
 */
public abstract class AbstractGraphExtended extends AbstractGraph implements Graph{
    /**
     * Construct a graph with the specified number of vertices
     * and the directed flag. If the directed flag is true,
     * this is a directed graph.
     *
     * @param numV     The number of vertices
     * @param directed The directed flag
     */
    public AbstractGraphExtended(int numV, boolean directed) {
        super(numV, directed);
    }

    /**
     * edgeLimit parametresi ile 0 arasinda random bir sayi secer.
     * Bu sayi kadar grapha yeni edge ekler.
     * Eklenilen edgelerin source ve destination vertexleri de random olarak secilir.
     * @param edgeLimit limit, eklenebilecek max edge sayisi da denilebilir.
     * @return eklenilen edge sayisi return edilir.
     */
    public int addRandomEdgesToGraph (int edgeLimit){
        int numOfInsertedEdges = 0;
        Random rand = new Random();

        numOfInsertedEdges = rand.nextInt(edgeLimit) % edgeLimit + 1;

        int insertedEdge = 0;
        int source, dest, weight = 1;
        Random rand2 = new Random();
        do {
            source = rand2.nextInt(getNumV()-1) % getNumV();
            dest = rand2.nextInt(getNumV()-1) % getNumV();

            if(!isEdge(source, dest)) {
                System.out.println("new Edge: (" + source + " ," + dest + ")");
                insert(new Edge(source, dest, weight));
                insertedEdge++;
                numOfInsertedEdges--;
            }
        }while(numOfInsertedEdges > 0);

        return insertedEdge;
    }

    /**
     * aramaya baslanilacak vertexten baslayarak getNumV() ye kadar
     * ve 0 dan baslanilarak start vertex'e kadar  elemanlarin eklendigi bir array olusturuldu.
     * Bu array ile butun vertexler (unconnected olsalar bile) arama islemine katilabilecek.
     * @param start aramaya baslanilacak vertex
     * @return vertexlerin parentlarinin tutuldugu array (vertexlere nereden ulasildigi)
     */
    public int [] breadthFirstSearch (int start){
        int [] visited = new int[getNumV()];
        int[] order = new int[getNumV()];
        int index = 0;
        int[] parent = new int[getNumV()];

        // parent arrayi, visited arrayi ve order arrayi set edildi
        for(int i = start; i<getNumV(); i++){
            parent[i] = -1;
            visited[i] = -1;
            order[index] = i;
            index++;
        }
        //eger baslanilan vertex sifirdan farkli bir vertex ise bu vertex'e kadar ki vertexler de order arrayine eklendi.
        if(index<getNumV()-1)
            for(int i=0; i<start; i++){
                order[index] = i;
                index++;
            }

        // her vertex uzerinden breadthFirsSearch yapildi.
        for(int i = 0; i<getNumV(); i++) {
            if(visited[i] == -1)
                breadthFirstSearch(order[i], visited, parent);
        }
        return parent;
    }

    /**
     * Kitaptan alinan kod parcasi
     * Gerekli sekilde duzenlendi.
     * @param start aramanin baslanilacagi vertex
     * @param visited aranmis vertexlerin isaretlendigi array
     * @param parent vertexlere hangi vertexlerden ulasildigini tutan array
     */
    private void breadthFirstSearch (int start, int[] visited, int[] parent){
        Queue< Integer > theQueue = new LinkedList< Integer >();
        boolean[] identified = new boolean[getNumV()];
        identified[start] = true;
        theQueue.offer(start);
        while (!theQueue.isEmpty()) {
            int current = theQueue.remove();
            visited[current] = 1; //ziyaret edilmis vertexler queuedan cikarilan vertexlerdir
            Iterator < Edge > itr = edgeIterator(current);
            while (itr.hasNext()) {
                Edge edge = itr.next();
                int neighbor = edge.getDest();
                if (!identified[neighbor]) {
                    identified[neighbor] = true;
                    theQueue.offer(neighbor);
                    parent[neighbor] = current;
                }
            }
        }
    }

    /**
     * graph'ýn birbirinden bagimsiz kac tane graph oldugunu bulup, bu graplari olusturup, bir graph arrayinde tutar.
     * @return graph arrayi return eder.
     */
    public Graph [] getConnectedComponentUndirectedGraph () throws IOException {
        Graph[] graph = null;
        if(isDirected() == false) {
            graph = new AbstractGraphExtended[getNumV()];
            boolean[] visited = new boolean[getNumV()];

            for(int i=0; i<getNumV(); i++)
                visited[i] = false;

            int graphIndex = 0;
            String fileName = "";


            for(int i=0; i < getNumV(); i++) {

                if(this instanceof MatrixGraph)
                    fileName = "graphMATRIX_";
                else if(this instanceof ListGraph)
                    fileName = "graphLIST_";
                else
                    fileName = "graph_";

                fileName += graphIndex;
                fileName += ".txt";

                if(!visited[i])
                    graph[graphIndex++] = addGraph(fileName, i, visited);
            }
        }
        return graph;
    }

    /**
     *
     * @param fileName graph'in yazilacagi dosya adi
     * @param start eklemeye baslanilacak vertex
     * @param visited eklenmis vertexleri isaretleyen array
     * @return yeni graph'i return eder
     * @throws IOException dosyaya yazma islemi oldugu icin, dosyaya yazamazsa exception firlatir
     */
    private Graph addGraph(String fileName, int start, boolean[] visited) throws IOException {
        Queue< Integer > theQueue = new LinkedList< Integer >();

        int[][] childs = new int[getNumV()*getNumV()][getNumV()];
        for(int i =0; i< getNumV(); i++)
            for(int j =0; j< getNumV(); j++)
                childs[i][j] = 0;

        boolean[] identified = new boolean[getNumV()];

        identified[start] = true;
        theQueue.offer(start);

        // aralarinda edge olan vertexlerin bulunup arraye yazildigi kisim
        while (!theQueue.isEmpty()) {
            int current = theQueue.remove();
            visited[current] = true;
            Iterator < Edge > itr = edgeIterator(current);
            while (itr.hasNext()) {
                Edge edge = itr.next();
                int neighbor = edge.getDest();

                if(neighbor < current+1)
                    childs[current][neighbor] = 1;

                if (!identified[neighbor]) {
                    identified[neighbor] = true;
                    theQueue.offer(neighbor);
                }
            }
        }

        // arrayi (aralarinda edge olan vertexleri tutan array) gelen dosyaya yazar
        File file = new File(fileName);
        FileWriter fW = new FileWriter(file);
        BufferedWriter bW = new BufferedWriter(fW);
        String s = "";
        s += (int)Math.sqrt(childs.length);
        bW.write(s.toString());
        bW.write("\n");

        for(int i= 0; i<childs.length; i++){
            for(int j = 0; j < childs[i].length; j++){
                if(childs[i][j] == 1){
                    s = "";
                    s += i;
                    s += " ";
                    s += j;
                    s += "\n";
                    bW.write(s.toString());
                }
            }
        }
        bW.close();

        Graph graph;
        // dosyaya yazilan bu verilerden bir graph olusturulur.
        file = new File(fileName);
        Scanner scan = new Scanner(file);
        if(this instanceof MatrixGraph)
            graph = AbstractGraph.createGraph(scan, false, "Matrix");
        else if(this instanceof ListGraph)
            graph = AbstractGraph.createGraph(scan, false, "List");
        else
            graph = null;
        scan.close();

        //olusturulan graph return edilir.
        return graph;
    }



    /**
     * stack yapisi kullanilarak bipartite olup olmadigi kontrol edildi.
     * http://www.geeksforgeeks.org/bipartite-graph/ sitesinden yararlanildi.
     * @return bipartite ise true, degilse false return edilir.
     */
    public boolean isBipartiteUndirectedGraph (){
        int[] vertices = new int[getNumV()];
        for (int i = 0; i < getNumV(); ++i)
            vertices[i] = -1;

        vertices[0] = 1;

        Stack <Integer> q = new Stack<Integer>();
        q.push(0);

        while (!q.isEmpty()) {
            int current = q.pop();
            Iterator<Edge> iter = edgeIterator(current);
            while (iter.hasNext()) {
                Edge edge = iter.next();
                int neighbor = edge.getDest();
                if (vertices[neighbor] == -1) {
                    vertices[neighbor] = 1 - vertices[current];
                    q.push(neighbor);
                }
                else if (vertices[neighbor] == vertices[current])
                    return false;
            }
        }
        return true;
    }

    /**
     * breadFirstSearch algoritmasina benzer bi sekide parent-child iliskisini tutan
     * 2 boyutlu bir integer arrayi olusutruldu.
     * Ve bu olsuturulan array ilskiye gore dosyaya yazdirildi.
     * @param fileName yazilacak dosya
     */
    public void writeGraphToFile (String fileName) throws IOException {
        int[][] childs = new int[getNumV()*getNumV()][getNumV()];
        for(int i =0; i< getNumV(); i++)
            for(int j =0; j< getNumV(); j++)
                childs[i][j] = -1;

        boolean[] visited = new boolean[getNumV()];
        for (int i=0; i<getNumV(); i++)
            visited[i] = false;

        for(int vertex = 0; vertex<getNumV(); vertex++) {
            Queue<Integer> theQueue = new LinkedList<Integer>();
            theQueue.offer(vertex);
            while (!theQueue.isEmpty()) {
                int current = theQueue.remove();
                visited[current] = true;
                Iterator<Edge> itr = edgeIterator(current);
                while (itr.hasNext()) {
                    Edge edge = itr.next();
                    int neighbor = edge.getDest();

                    if(!visited[neighbor])
                        theQueue.offer(neighbor);

                    childs[current][neighbor] = 1;
                }
            }
        }

        File file = new File(fileName);
        FileWriter fW = new FileWriter(file);
        BufferedWriter bW = new BufferedWriter(fW);
        String s = "";
        s += getNumV();
        bW.write(s.toString());
        bW.write("\n");

        for(int i= 0; i<getNumV(); i++){
            for(int j = 0; j < getNumV(); j++){
                if(childs[i][j] == 1){
                    s = "";
                    s += i;
                    s += " ";
                    s += j;
                    s += "\n";
                    bW.write(s.toString());
                }
            }
        }
        bW.close();
    }
}
