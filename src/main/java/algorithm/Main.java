package algorithm;

import algorithm.model.Hub;
import algorithm.model.RouteSegment;
import algorithm.service.HubService;
import algorithm.service.RouteSegmentService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

import static algorithm.dijkstra.Dijkstra.computePaths;
import static algorithm.dijkstra.Dijkstra.getShortestPathTo;

@SpringBootApplication
public class Main {

    private static HubService hubService;
    private static RouteSegmentService routeSegmentService;

    @Autowired
    public Main(HubService hubService, RouteSegmentService routeSegmentService) {
        this.hubService = hubService;
        this.routeSegmentService = routeSegmentService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        //initAndSave();
        List<Hub> hubs = hubService.findAll();
        Hub Kharkiv = hubs.stream()
                .filter(hub -> hub.getName().equals("Kharkiv"))
                .findFirst().orElseThrow();
        Hub Kyiv = hubs.stream()
                .filter(hub -> hub.getName().equals("Kyiv"))
                .findFirst().orElseThrow();
        computePaths(Kharkiv); // run Dijkstra
        System.out.println("Distance to " + Kyiv + ": " + Kyiv.getMinDistance());
        List<Hub> path = getShortestPathTo(Kyiv);
        System.out.println("Path: " + path);

        free(hubs);
        Kharkiv.getClosestRelations().remove(0);

        computePaths(Kharkiv); // run Dijkstra
        System.out.println("Distance to " + Kyiv + ": " + Kyiv.getMinDistance());
        path = getShortestPathTo(Kyiv);
        System.out.println("Path: " + path);

        free(hubs);
        Kharkiv.getClosestRelations().remove(1);

        computePaths(Kharkiv); // run Dijkstra
        System.out.println("Distance to " + Kyiv + ": " + Kyiv.getMinDistance());
        path = getShortestPathTo(Kyiv);
        System.out.println("Path: " + path);

    }

    private static void free(List<Hub> path) {
        for (Hub hub : path) {
            hub.setPrevious(null);
            hub.setMinDistance(Double.POSITIVE_INFINITY);
        }
    }

    private static void initAndSave() {
        // define all the Hubs
        Hub Kharkiv = new Hub("Kharkiv");
        Hub Dnipro = new Hub("Dnipro");
        Hub KrivyRig = new Hub("KrivyRig");
        Hub Kropivnitsky = new Hub("Kropivnitsky");
        Hub Uman = new Hub("Uman");
        Hub Kyiv = new Hub("Kyiv");
        Hub Myrgorod = new Hub("Myrgorod");
        Hub Poltava = new Hub("Poltava");
        Hub Sumy = new Hub("Sumy");
        Hub Konotop = new Hub("Konotop");
        Hub BilaCerkva = new Hub("BilaCerkva");
        List<Hub> hubs = List.of(Kharkiv, Dnipro, KrivyRig, Kropivnitsky, Uman, Kyiv, Myrgorod, Poltava, Sumy, Konotop, BilaCerkva);

        hubService.saveAll(hubs);

        // define all the RouteSegments(Edges) and distances(weights)
        Kharkiv.setRelation(new RouteSegment(Poltava, 153));       //TODO 1st
        Kharkiv.setRelation(new RouteSegment(Dnipro, 214));        //TODO Third
        Kharkiv.setRelation(new RouteSegment(Sumy, 185));          //TODO 2

        Poltava.setRelation(new RouteSegment(Myrgorod, 101));      //TODO 1st
        Myrgorod.setRelation(new RouteSegment(Kyiv, 251));         //TODO 1st
        BilaCerkva.setRelation(new RouteSegment(Kyiv, 85));        //TODO Third
        Kyiv.setRelation(new RouteSegment(BilaCerkva, 85));        //TODO Third
        Konotop.setRelation(new RouteSegment(Kyiv, 244));          //TODO 2
        Sumy.setRelation(new RouteSegment(Konotop, 130));          //TODO 2
        Dnipro.setRelation(new RouteSegment(KrivyRig, 149));       //TODO Third
        KrivyRig.setRelation(new RouteSegment(Kropivnitsky, 119)); //TODO Third
        Kropivnitsky.setRelation(new RouteSegment(Uman, 160));     //TODO Third
        Uman.setRelation(new RouteSegment(BilaCerkva, 130));       //TODO Third
        saveAllRouteSegments(hubs);
        hubService.saveAll(hubs);
    }

    private static void saveAllRouteSegments(List<Hub> hubs) {
        List<RouteSegment> allSegments = new ArrayList<>();
        for (Hub hub : hubs) {
            allSegments.addAll(hub.getClosestRelations());
        }
        routeSegmentService.saveAll(allSegments);
    }
}
