package algorithm.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "route_segments")
@Data
public class RouteSegment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer id;

    @Column
    private double distance;

    @OneToOne
    private Hub target;

    @ManyToOne(fetch = FetchType.EAGER)
    private Hub master;

    public RouteSegment() {
    }

    public RouteSegment(Hub target, double distance) {
        this.distance = distance;
        this.target = target;
    }
}