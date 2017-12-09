/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alg;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 *
 * @author Honza
 */
public class Main {

    static int NODES;
    static int EDGES;
    static int node1;
    static int node2;
    static Node[] poleNodu;
    static Collection<Integer> potencialLists = new TreeSet<>();
    static Collection<Integer> vysledek = new TreeSet<>();
    LinkedList<TempNode> LinkedList = new LinkedList<>();

    public boolean pushToLinkedList(TempNode tempNode) {
        for (TempNode tn : LinkedList) {
            if(tempNode.index == tn.index){
                if (tempNode.hloubka == tn.hloubka) {
                    vysledek.add(tempNode.index);
                    LinkedList.remove(tn);
                    return true;
                }else{
                    return false;
                }
            }
        }
        LinkedList.add(tempNode);
        return true;
    }

    public void createNewLinkedList() {
        LinkedList.clear();
        vysledek.clear();
        TempNode firstNode = new TempNode();
        firstNode.hloubka = 0;
        firstNode.index = potencialLists.remove(0)? 1:0;
        LinkedList.add(firstNode);
    }

    public boolean sucheTheBeste() {
        int edges = 0;
        int nHlouka = 0;
        TempNode current;
        while (true) {
            current = LinkedList.removeFirst();
            if (current.hloubka == nHlouka) {
                edges += poleNodu[current.index].nodeEdgesIndex;
            }else{
                if ((edges % 2) == 0) {
                    edges = poleNodu[current.index].nodeEdgesIndex;
                    nHlouka = current.hloubka;
                } else {
                    return false;
                }
            }
            if (nHlouka == 0) {
                vysledek.add(current.index);
            }
            for (int i = 0; i < poleNodu[current.index].nodeEdgesIndex; i++) {
                TempNode child = new TempNode();
                child.parent = current.index;
                child.hloubka = current.hloubka + 1;
                child.index = poleNodu[current.index].nodeArrey.get(i);
                if (child.index != current.parent) {
                    if (!pushToLinkedList(child)) {
                        return false;
                    }
                }
            }
            if (LinkedList.isEmpty()) {
                break;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        Main main = new Main();
        
        InputReader ir = new InputReader(new FileInputStream(args[0]));
        NODES = ir.nextInt();
        EDGES = ir.nextInt();

        poleNodu = new Node[NODES + 1];
        for (int i = 1; i < NODES + 1; i++) {
            Node node = new Node();
            poleNodu[i] = node;
        }

        for (int i = 0; i < EDGES; i++) {
            node1 = ir.nextInt();
            node2 = ir.nextInt();

            poleNodu[node1].nodeArrey.add(node2);
            poleNodu[node1].nodeEdgesIndex += 1;

            poleNodu[node2].nodeArrey.add(node1);
            poleNodu[node2].nodeEdgesIndex += 1;
        }

        for (int i = 1; i < poleNodu.length; i++) {
            if (poleNodu[i].nodeEdgesIndex == 2) {
                potencialLists.add(i);
            }
        }
        main.createNewLinkedList();
        
        while(true){
            if(!main.sucheTheBeste()){
                main.createNewLinkedList();
            }else{
                break;
            }
        }
        Collections.sort(vysledek);
        int size = vysledek.size();
        if(size>100) size = 100;
        int idx = 0;
        for (int i : vysledek) {
            System.out.print(i+" ");
            idx++;
            if(idx==100) break;
        }
        System.out.println("");
        long end = System.currentTimeMillis() - start;
        System.out.println();
        System.out.println(end);

    }

}
