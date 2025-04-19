package com.atilio.procedural.mics;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.atilio.procedural.entities.BinaryTreeNode;
import com.atilio.procedural.entities.SubMatrix;

class TreeToStreamTest {
    @Test
    void getFilenameTest() {
        String filename = "../serdata";
        String result = TreeToStream.getFileName(filename);
        assertNotNull(result);
        assertNotEquals("", result);

    }

    @Test
    void loadFromStreamTest() {
        String folderame = "../serdata";
        String filename = TreeToStream.getFileName(folderame);
        BinaryTreeNode<SubMatrix> result = TreeToStream.loadFromStream(filename);
        assertNotNull(result);
    }

    @Test
    void loadFromStreamNoFile() {
        String folderame = "serdata";
        String filename = TreeToStream.getFileName(folderame);
        BinaryTreeNode<SubMatrix> result = TreeToStream.loadFromStream(filename);
        assertNull(result);
    }

}
