/*
 * LICENSE
 *
 * "THE BEER-WARE LICENSE" (Revision 43):
 * "Sven Strittmatter" <weltraumschaf(at)googlemail(dot)com> wrote this file.
 * As long as you retain this notice you can do whatever you want with
 * this stuff. If we meet some day, and you think this stuff is worth it,
 * you can buy me a non alcohol free beer in return.
 *
 * Copyright (C) 2012 "Sven Strittmatter" <weltraumschaf(at)googlemail(dot)com>
 */
package de.weltraumschaf.jebnf.ast.visitor;

import de.weltraumschaf.jebnf.ast.CompositeNode;
import de.weltraumschaf.jebnf.ast.Node;
import de.weltraumschaf.jebnf.ast.Visitable;
import de.weltraumschaf.jebnf.ast.nodes.NodeAttribute;
import java.util.Map;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * {@link Visitor} which generates a XML string from the visited AST tree.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class XmlVisitor implements Visitor<String> {

    /**
     * Default XML version.
     */
    public static final String  DEFAULT_VERSION  = "1.0";

    /**
     * Default XML encoding.
     */
    public static final String  DEFAULT_ENCODING = "utf-8";

    /**
     * Default indentation spaces width.
     */
    public static final int DEFAULT_INDENTATION  = 4;

    /**
     * Buffers the constructed XML string.
     */
    private final StringBuilder xmlString = new StringBuilder();

    /**
     * Level to indent the tags.
     */
    private int indentationLevel;

    /**
     * Initialize object with {@link XmlVisitor#DEFAULT_ENCODING} and {@link XmlVisitor#DEFAULT_VERSION}.
     */
    public XmlVisitor() {
        this(DEFAULT_ENCODING);
    }

    /**
     * Initialize object with {@link XmlVisitor#DEFAULT_VERSION}.
     *
     * @param encoding XML encoding attribute.
     */
    public XmlVisitor(final String encoding) {
        this(encoding, DEFAULT_VERSION);
    }

    /**
     * Initializes the {@link Visitor} with XML version and encoding.
     *
     * @param version  Optional XML version. Default is {@link XmlVisitor#DEFAULT_VERSION}.
     * @param encoding Optional XML encoding. Default is {@link XmlVisitor#DEFAULT_ENCODING}.
     */
    public XmlVisitor(final String encoding, final String version) {
        append(String.format("<?xml version=\"%s\" encoding=\"%s\"?>", version, encoding));
    }

    /**
     * Creates opening tag.
     *
     * @param name The tag name.
     * @return      The tag string.
     */
    public static String createOpenTag(final String name) {
        return createOpenTag(name, null);
    }

    /**
     * Creates opening tag with attributes.
     *
     * @param name The tag name.
     * @param attr Map of attributes.
     * @return      The tag string.
     */
    public static String createOpenTag(final String name, final Map<NodeAttribute, String> attr) {
        return createOpenTag(name, attr, true);
    }

    /**
     * Creates a opening tag string by name.
     *
     * @param name The tag name.
     * @param attributes Optional tag attributes.
     * @param block Whether the tag is in line or block.
     * @return Returns formatted tag string.
     */
    public static String createOpenTag(final String name, final Map<NodeAttribute, String> attributes, final boolean block) {
        final StringBuilder tag = new StringBuilder();
        tag.append('<').append(name);

        if (null != attributes && !attributes.isEmpty()) {
            for (Map.Entry<NodeAttribute, String> attribute : attributes.entrySet()) {
                tag.append(String.format(" %s=\"%s\"",
                           attribute.getKey().toString().toLowerCase(),
                           StringEscapeUtils.escapeXml(attribute.getValue())));
            }
        }

        if (!block) {
            tag.append('/');
        }

        return tag.append('>').toString();
    }

    /**
     * Creates a closing tag.
     *
     * @param name The tag name.
     *
     * @return Returns formatted tag string.
     */
    public static String createCloseTag(final String name) {
        return String.format("</%s>", name);
    }

    /**
     * Generates an indentation string depending on the actual indentation level.
     *
     * @return Returns string with white spaces.
     */
    private String indent() {
        return StringUtils.repeat(' ', indentationLevel * DEFAULT_INDENTATION);
    }

    /**
     * Appends a string to the xml buffer string.
     *
     * @param string Generated XML string to append.
     */
    private void append(final String string) {
        xmlString.append(string);
    }

    /**
     * {@link Visitor} method to visit a node.
     *
     * Generates opening tags from visited node.
     *
     * @param visitable Visited node.
     */
    @Override
    public void visit(final Visitable visitable) {
        boolean block = false;

        if (visitable instanceof CompositeNode) {
            final CompositeNode composite = (CompositeNode) visitable;

            if (composite.hasChildren()) {
                block = true;
            }
        }

        append("\n");
        append(indent());
        final Node node = (Node) visitable;
        append(createOpenTag(node.getNodeName(), node.getAttributes(), block));


        if (visitable instanceof CompositeNode) {
            final CompositeNode composite = (CompositeNode) visitable;

            if (composite.hasChildren()) {
                indentationLevel++;
            }
        }
    }

    /**
     * Not used.
     *
     * @param visitable Visited node.
     *
     */
    @Override
    public void beforeVisit(final Visitable visitable) {
        // Nothing to do here.
    }

    /**
     * Generates closing tags for composite nodes.
     *
     * @param visitable Visited node.
     *
     */
    @Override
    public void afterVisit(final Visitable visitable) {
        if (visitable instanceof CompositeNode) {
            final CompositeNode composite = (CompositeNode) visitable;

            if (composite.hasChildren()) {
                indentationLevel--;
                append("\n");
                append(indent());
                final Node node = (Node) composite;
                append(createCloseTag(node.getNodeName()));
            }
        }
    }

    /**
     * Returns the actual buffered XML string.
     *
     * @return Returns XML string.
     */
    @Override
    public String getResult() {
        return xmlString.toString();
    }

}
