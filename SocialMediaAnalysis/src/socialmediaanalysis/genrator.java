/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialmediaanalysis;

import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

/**
 *
 * @author Ahmed
 */
public class genrator {

    void star_gent() {
        Graph graph3 = new SingleGraph("Random");
        Generator gen = new RandomGenerator(2);
        gen.addSink(graph3);
        gen.begin();
        for (int i = 0; i < 100; i++) {
            gen.nextEvents();
        }
        gen.end();
        graph3.display();
    }

}
