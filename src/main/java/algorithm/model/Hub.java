package algorithm.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hubs")
@Data
@NoArgsConstructor
public class Hub implements Comparable<Hub> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "hub_relations",
            joinColumns = {@JoinColumn(name = "hub_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "route_segment_id", referencedColumnName = "id")})
    private List<RouteSegment> closestRelations;

    @Transient
    private double minDistance = Double.POSITIVE_INFINITY;

    @Transient
    private Hub previous;

    public Hub(String name) {
        this.name = name;
        closestRelations = new ArrayList<>();
    }

    public void setRelation(RouteSegment routeSegment) {
        routeSegment.setMaster(this);
        closestRelations.add(routeSegment);
    }

    public String toString() {
        return name;
    }

    public int compareTo(Hub other) {
        return Double.compare(minDistance, other.minDistance);
    }
}
