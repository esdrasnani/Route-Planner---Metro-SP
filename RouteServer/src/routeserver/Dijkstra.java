package routeserver;

import java.util.*;

public class Dijkstra {
    private static final int NO_PARENT = -1;
    private static final List<String> stations= Arrays.asList("Jabaquara","Conceição","São Judas","Saúde","Praça da Árvore","Santa Cruz","Vila Mariana","Ana Rosa","Paraíso","Vergueiro","São Joaquim","Japão-Liberdade","Sé","São Bento","Luz","Tiradentes","Armênia","Portuguesa-Tietê","Carandiru","Santana","Jardim São Paulo-Aryton Senna","Parada Inglesa","Tucuruvi","Jundaí","Várzea Paulista","Campo Limpo Paulista","Botujuru","Franciso Morato","Baltazar Fidélis","Franco da Rocha","Caleiras","Perus","Vila Aurora","Jaraguá","Vila Clarice","Pirituba","Piqueri","Lapa(Rubi)","Água Branca","Palmeiras Barra Funda","Amador Bueno","Santa Rita","Itapevi","Engenheiro Cardoso","Sagrado Cadorso","Jandira","Jardim Silveira","Jardim Belval","Barueri","Antonio João","Santa Teresina","Carapicuiba","General Miguel Costa","Quitaûna","Comandante Sampaio","Osasco","Presidente Altino","Imperatriz Leopoldina","Domingos de Moraes","Lapa(Diamante)","Júlio Prestes","São Paulo-Morumbi","Butantã","Pinheiros","Faria Lima","Fradique Coutinho","Oscar Freire","Paulista","Higienópolis-Mackenzie","República","Marechal Deodoro","Santa Cecília","Anhangabaú","Pedro II","Brás","Bresser-Mocca","Belém","Tatuapé","Carrão","Penha","Vila Matilde","Guilhermiana Esperança","Patriarca-Vila Ré","Arthur Alvim","Corinthians Itaquera","Grajaú","Primavera-Interlagos","Autódromo","Jurubatuba","Socorro","Santo Amaro","Granja Julieta","Morumbi","Berrini","Vila Olímpia","Cidade Jardim","Hebraica Rebouças","Cidade Universitária","Vila Lobos Jaguaré","Ceasa","Capão Redondo","Campo Limpo","Vila das Belezas","Giovanni Gronchi","Largo Treze","Adolfo Pinheiro","Alto da Boa Vista","Borba Gato","Brooklin","Campo Belo","Eucaliptos","Moema","AACD-Servidor","Hospital São Paulo","Chácara Klabin","Vila Madalena","Sumaré","Clínicas","Consolação","Trianon-Masp","Brigadeiro","Santos-Imigrantes","Alto do Ipiranga","Sacomã","Tamanduateí","Vila Prudente","Oratório","São Lucas","Camilo Haddad","Vila Tolstói","Vila União","Jardim Planalto","Sapopemba","Fazenda da Juta","São Mateus","Bio Grande da Serra","Ribeirão Pires","Guapituba","Mauá","Capuava","Santo André","Prefeito Saladino","Utinga","São Caetano do Sul","Ipiranga","Juventus Mooca","Dom Bosco","José Bonifácio","Guaianases","Antonio Gianetti Neto","Ferraz de Vasconcelos","Poá","Calmon Viana","Suzano","Jundiapeba","Braz Cubas","Mogi das Cruzes","Estudantes","Aracaré","Itaquaquecetuba","Engeheiro Manoel Feio","Jardim Romando","Itaim Paulista","Jardim Helena-Vila Mara","São Miguel Paulista","Comendador Ermelino","USP Leste","Engenheiro Goulart","Guarulhos-Cecap","Aeroporto-Guarulhos");
    static List<String> res = new ArrayList<String>();
    // Function that implements Dijkstra's
    // single source shortest path
    // algorithm for a graph represented
    // using adjacency matrix
    // representation
    static List<String> dijkstra(int[][] adjacencyMatrix, int startVertex, int endVertex) {
        res = new ArrayList<String>();
        int nVertices = adjacencyMatrix[0].length;

        // shortestDistances[i] will hold the
        // shortest distance from src to i
        int[] shortestDistances = new int[nVertices];

        // added[i] will true if vertex i is
        // included / in shortest path tree
        // or shortest distance from src to
        // i is finalized
        boolean[] added = new boolean[nVertices];

        // Initialize all distances as
        // INFINITE and added[] as false
        for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
            shortestDistances[vertexIndex] = Integer.MAX_VALUE;
            added[vertexIndex] = false;
        }

        // Distance of source vertex from
        // itself is always 0
        shortestDistances[startVertex] = 0;

        // Parent array to store shortest
        // path tree
        int[] parents = new int[nVertices];

        // The starting vertex does not
        // have a parent
        parents[startVertex] = NO_PARENT;

        // Find shortest path for all
        // vertices
        for (int i = 1; i < nVertices; i++) {

            // Pick the minimum distance vertex
            // from the set of vertices not yet
            // processed. nearestVertex is
            // always equal to startNode in
            // first iteration.
            int nearestVertex = -1;
            int shortestDistance = Integer.MAX_VALUE;
            for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
                if (!added[vertexIndex] && shortestDistances[vertexIndex] < shortestDistance) {
                    nearestVertex = vertexIndex;
                    shortestDistance = shortestDistances[vertexIndex];
                }
            }

            // Mark the picked vertex as
            // processed
            added[nearestVertex] = true;

            // Update dist value of the
            // adjacent vertices of the
            // picked vertex.
            for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
                int edgeDistance = adjacencyMatrix[nearestVertex][vertexIndex];

                if (edgeDistance > 0
                        && ((shortestDistance + edgeDistance) <
                        shortestDistances[vertexIndex])) {
                    parents[vertexIndex] = nearestVertex;
                    shortestDistances[vertexIndex] = shortestDistance +
                            edgeDistance;
                }
            }
        }

        printSolution(startVertex, shortestDistances, parents, endVertex);
        return res;
    }

    // A utility function to print
    // the constructed distances
    // array and shortest paths
    private static void printSolution(int startVertex, int[] distances, int[] parents, int endVertex) {
        int nVertices = distances.length;
        
        for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
            if (vertexIndex != startVertex && vertexIndex == endVertex) {
                printPath(vertexIndex, parents);
            }
        }
    }
    
    // Function to print shortest path
    // from source to currentVertex
    // using parents array
    private static void printPath(int currentVertex, int[] parents) {
        if (currentVertex == NO_PARENT) {
            return;
        }
        printPath(parents[currentVertex], parents);
        res.add(stations.get(currentVertex));
    }
}
