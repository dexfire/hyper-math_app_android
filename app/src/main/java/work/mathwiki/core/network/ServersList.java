package work.mathwiki.core.network;

import java.util.ArrayList;

public class ServersList {
    public static ArrayList<Server> sServersList;
    public class Server{
        public Server(String baseUrl,String path){
            this.baseUrl = baseUrl;
            this.path = path;
        }
        public String baseUrl;
        public String path;
    }
    public ServersList(){
        sServersList = new ArrayList<>();
        sServersList.add(new Server("https://github.com","/dexfire/MathWiki/releases"));
        sServersList.add(new Server("http://mathwiki.work","/"));
    }
}
