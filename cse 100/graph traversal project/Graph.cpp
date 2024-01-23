#include "Graph.h"
#include <fstream>
#include <sstream>
#include <queue>
#include <algorithm>
#include <limits>
#include <iostream> // load cout  // load string class
#include <utility>  // load pair class
map<string, map<string, double>> graph;

vector<tuple<double,string,string>> edge_list(){
    //For each edge (x,y,w) between nodes x and y with weight w increasing order of edge weight:
    vector<tuple<double,string,string>> edges;
   map<tuple<double,string,string>, tuple<double,string,string>> visited;
    for(auto it = graph.begin(); it != graph.end(); ++it){
        for(auto it2 = it->second.begin(); it2 != it->second.end(); ++it2){
            tuple<double,string,string> edge = make_tuple(it2->second, it->first, it2->first);
            tuple<double,string,string> temp = make_tuple(it2->second, it2->first, it->first);
            if(visited.find(edge) == visited.end()){
                edges.push_back(edge);
                visited[temp] = edge;
            }
        }
    }
    sort(edges.begin(), edges.end());


    return edges;
}


Graph::Graph(const char* const & edgelist_csv_fn) {
    ifstream my_file(edgelist_csv_fn);      // open the file
    string line;         // helper var to store current line
    while(getline(my_file, line)) {  // read one line from the file
        istringstream ss(line);      // create istringstream of current line
        string first, second, third; // helper vars
        getline(ss, first, ',');     // store first column in "first"
        getline(ss, second, ',');    // store second column in "second"
        getline(ss, third, '\n');    // store third column column in "third"
        graph[first][second] = stod(third); // store the edge in the graph
        graph[second][first] = stod(third); 
    }

    my_file.close();                 // close file when done
}

unsigned int Graph::num_nodes() {
    return graph.size();
}



vector<string> Graph::nodes() {
    vector<string> nodes;
    for (auto it = graph.begin(); it != graph.end(); ++it){
        nodes.push_back(it->first);
    }
    return nodes;
}

unsigned int Graph::num_edges() {
    unsigned int num_edges = 0;
    for (auto it = graph.begin(); it != graph.end(); ++it){
        num_edges= num_edges + it->second.size();
    }
    return num_edges/2;
}

unsigned int Graph::num_neighbors(string const & node_label) {
    return graph[node_label].size();
}

double Graph::edge_weight(string const & u_label, string const & v_label) {
    if (graph[u_label].find(v_label) != graph[u_label].end()){
        return graph[u_label][v_label];
    }
    else{
        return -1;
    }
}

vector<string> Graph::neighbors(string const & node_label) {
    vector<string> neighbors;
    for (auto it = graph[node_label].begin(); it != graph[node_label].end(); ++it){
        neighbors.push_back(it->first);
    }
    return neighbors;
}


vector<string> Graph::shortest_path_unweighted(string const & start_label, string const & end_label) {
    vector<string> path;
    //edge cases
    if(start_label == end_label){
        path.push_back(start_label);
        return path;
    }
    else if(graph.find(start_label) == graph.end() || graph.find(end_label) == graph.end()){
        return path;
    }
    else if(graph[start_label].size()==0 || graph[end_label].size()==0){
        return path;
        }
    //BFS
    else{
        //BFS
        map<string, bool> visited;
        map<string, string> parent;
        queue<string> q;
        q.push(start_label);
        visited[start_label] = true;
        while(!q.empty()){
            string curr = q.front();
            q.pop();
            for(auto it = graph[curr].begin(); it != graph[curr].end(); ++it){
                if(!visited[it->first]){
                    visited[it->first] = true;
                    q.push(it->first);
                    parent[it->first] = curr;
                }
            }
        }
        if(parent.find(end_label) == parent.end()){
            return path;
        }
        string curr = end_label;
        while(curr != start_label){
            path.push_back(curr);
            curr = parent[curr];
        }
        path.push_back(start_label);
        reverse(path.begin(), path.end());
        return path;
    }
}

vector<tuple<string,string,double>> Graph::shortest_path_weighted(string const & start_label, string const & end_label) {
    // TODO
    vector<tuple<string,string,double>> path;
    //edge cases
    if(start_label == end_label){
        tuple<string,string,double> node = make_tuple(start_label, start_label, -1);
        path.push_back(node);
        return path;
    }
    else if(graph.find(start_label) == graph.end() || graph.find(end_label) == graph.end()){
        return path;
    }
    else if(graph[start_label].size()==0 || graph[end_label].size()==0){
        return path;
    }
    else{
       
        //dijkstra's algorithm
        map<string, double> dist;
        map<string, string> prev;
        map<string, bool> visited;

         auto comp = [](const pair<double, string>& a, const pair<double, string>& b) {
            return a.first > b.first;
        };
        priority_queue<pair<double, string>, vector<pair<double, string>>, decltype(comp)> pq(comp);

        for(auto it = graph.begin(); it != graph.end(); ++it){
            dist[it->first] = numeric_limits<double>::infinity();
            visited[it->first] = false;
        }

        dist[start_label] = 0;
        pq.push(make_pair(0, start_label));
        while (!pq.empty()) {

            string v = pq.top().second;
            double weight = pq.top().first;
            pq.pop();
            if (!visited[v]) {
                visited[v] = true;
            for(auto it = graph[v].begin(); it != graph[v].end(); ++it){
                string curr=it->first;
            if (!visited[curr]) {
                    double c= dist[v]+it->second;//c = v.dist + d
                    if (c<dist[curr]){
                        dist[curr]=c;
                        prev[curr]=v;
                        pq.push(make_pair(dist[curr], curr));
                    }
            }
            }
            }
        }

        if(prev.find(end_label) == prev.end()){
            return path;
        }
        string curr = end_label;
        while(curr != start_label){
                tuple<string,string,double> node = make_tuple(prev[curr],curr,graph[prev[curr]][curr]);
                path.push_back(node);
                curr = prev[curr];
            }
            reverse(path.begin(), path.end());
            return path;
    
        }
        
    }


vector<vector<string>> Graph::connected_components(double const & threshold) {
    
    vector<vector<string>> components;
    map<string, bool> visited;
    //edges cases
    if(threshold < 0){
        return components;
    }
    if(Graph::num_nodes() == 0){
        return components;
    }
    if(Graph::num_edges() == 0){
        vector<string> component=Graph::nodes();
        for(int i=0; i<component.size(); i++){
            vector<string> temp;
            temp.push_back(component[i]);
            components.push_back(temp);
        }
        return components;
    }

    for(auto it = graph.begin(); it != graph.end(); ++it){
        visited[it->first] = false;
    }

    for(auto it = graph.begin(); it != graph.end(); ++it){

        if(!visited[it->first]){

            vector<string> component;
            queue<string> q;
            q.push(it->first);
            visited[it->first] = true;

            while(!q.empty()){

                string curr = q.front();
                q.pop();
                component.push_back(curr);

                for (auto it3 = graph[curr].begin(); it3 != graph[curr].end(); ++it3){
                    if((!visited[it3->first]) && (it3->second <= threshold)){
                         q.push(it3->first);
                         visited[it3->first] = true;
                    }
                }
            }
            components.push_back(component);
        }    
}
return components;
}


double Graph::smallest_connecting_threshold( string const & start_label, string const & end_label ) {
    //edge cases
    if(start_label==end_label){
        return 0;
    }
    if(graph.find(start_label) == graph.end() || graph.find(end_label) == graph.end()){
        return -1;
    }
    if(graph[start_label].size()==0 || graph[end_label].size()==0){
        return -1;
    }

    map<string, string> parent;
    map<string, int> s;
    for(auto it = graph.begin(); it != graph.end(); ++it){
        parent[it->first] = it->first;
        s[it->first] = 1;
    }

    vector<tuple<double,string,string>> edges_l= edge_list();
        for(int i=0; i<edges_l.size(); i++){
        string u = get<1>(edges_l[i]) ;
        string v = get<2>(edges_l[i]) ;
        double w = get<0>(edges_l[i]) ;
    
        string curr_start=u;
        while(parent[curr_start] != curr_start){
            curr_start = parent[curr_start];
        }

        string curr_end=v;
        while(parent[curr_end] != curr_end){
            curr_end = parent[curr_end];
        }

        if(s[curr_start]>s[curr_end]){
            parent[curr_end] = curr_start;
            s[curr_start] = s[curr_end] + s[curr_start];
        }
        else{
            parent[curr_start] = curr_end;
            s[curr_end] = s[curr_start] + s[curr_end];
        }

    
         curr_start=start_label;
        while(parent[curr_start] != curr_start){
            curr_start = parent[curr_start];
        }

         curr_end=end_label;
        while(parent[curr_end] != curr_end){
            curr_end = parent[curr_end];
        }
   
        if(curr_start == curr_end){
            return w;
        }

        }
         return -1.0;
    }
   