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

import com.google.common.collect.Maps;
import static de.weltraumschaf.jebnf.TestHelper.getInstance;
import static de.weltraumschaf.jebnf.ast.builder.SyntaxBuilder.syntax;
import de.weltraumschaf.jebnf.ast.nodes.LoopNode;
import de.weltraumschaf.jebnf.ast.nodes.NodeAttribute;
import de.weltraumschaf.jebnf.ast.nodes.SyntaxNode;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Unit test for XmlVisitor.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class XmlVisitorTest {

    private final XmlVisitor sut = new XmlVisitor();

    private enum Attributes implements NodeAttribute { BAR, BAZ; }

    @Test public void testCreateOpenTag() {
        final Map<NodeAttribute, String> attributes1 = new TreeMap<NodeAttribute, String>();
        attributes1.put(Attributes.BAR, "1"); // NOPMD
        attributes1.put(Attributes.BAZ, "<\">&");
        final Map<NodeAttribute, String> attributes2 = new TreeMap<NodeAttribute, String>();
        attributes2.put(Attributes.BAR, "1");
        attributes2.put(Attributes.BAZ, "2");

        assertEquals("<foo>", XmlVisitor.createOpenTag("foo")); // NOPMD
        assertEquals("<foo bar=\"1\" baz=\"&lt;&quot;&gt;&amp;\">", XmlVisitor.createOpenTag("foo", attributes1));
        assertEquals("<foo bar=\"1\" baz=\"2\">", XmlVisitor.createOpenTag("foo", attributes2));
        assertEquals("<foo bar=\"1\" baz=\"2\"/>", XmlVisitor.createOpenTag("foo", attributes2, false));
    }

    @Test public void testCloseOpenTag() {
        assertEquals("</foo>", XmlVisitor.createCloseTag("foo"));
        assertEquals("</fooBar>", XmlVisitor.createCloseTag("fooBar"));
    }

    @Test public void testExtractAttributes() {
        final Map<NodeAttribute, String> fix = Maps.newHashMap();
        fix.put(SyntaxNode.Attributes.META, "foo");
        fix.put(SyntaxNode.Attributes.TITLE, "bar");

        final SyntaxNode syntax = SyntaxNode.newInstance("bar", "foo");
        assertEquals(fix, syntax.getAttributes());

        final LoopNode loop = LoopNode.newInstance();
        assertEquals(new HashMap<NodeAttribute, String>(), loop.getAttributes());
    }

    @Test public void testGenerateEmptyXml() throws URISyntaxException, IOException {
        assertEquals(
            "<?xml version=\"1.0\" encoding=\"utf-8\"?>",
            sut.getResult()
        );
    }

    @Test public void testGenerateEmptySyntaxXml() throws URISyntaxException, IOException {
        final SyntaxNode syntax = SyntaxNode.newInstance("EBNF defined in itself.", "xis/ebnf v2.0 http://wiki.karmin.ch/ebnf/ gpl3");
        syntax.accept(sut);
        assertEquals("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n"
                    + "<syntax "
                    + "meta=\"xis/ebnf v2.0 http://wiki.karmin.ch/ebnf/ gpl3\" "
                    + "title=\"EBNF defined in itself.\""
                    + "/>",
                    sut.getResult());
    }

    @Test public void testGenerateXml() throws URISyntaxException, IOException {
        final SyntaxNode syntax = syntax("EBNF defined in itself.", "xis/ebnf v2.0 http://wiki.karmin.ch/ebnf/ gpl3")
            .rule("syntax")
                .sequence()
                    .option()
                        .identifier("title")
                    .end()
                    .terminal("{")
                    .loop()
                        .identifier("rule")
                    .end()
                    .terminal("}")
                    .option()
                        .identifier("comment")
                    .end()
                .end()
            .end()
            .rule("rule")
                .sequence()
                    .identifier("identifier")
                    .choice()
                        .terminal("=")
                        .terminal(":")
                        .terminal(":==")
                    .end()
                    .identifier("expression")
                    .choice()
                        .terminal(".")
                        .terminal(";")
                    .end()
                .end()
            .end()
            .rule("literal")
                .choice()
                    .sequence()
                        .terminal("'")
                        .identifier("character") // NOPMD
                        .loop()
                            .identifier("character")
                        .end()
                        .terminal("'")
                    .end()
                    .sequence()
                        .terminal("\"")
                        .identifier("character")
                        .loop()
                            .identifier("character")
                        .end()
                        .terminal("\"")
                    .end()
                .end()
            .end()
        .build();

        syntax.accept(sut);

        final String xml = getInstance().createStringFromFixture("ast/visitor/syntax.xml");
        assertEquals(xml, sut.getResult());
    }

}
