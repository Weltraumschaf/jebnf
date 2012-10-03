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
import de.weltraumschaf.jebnf.ast.nodes.SyntaxNode;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Unit test for XmlVisitor.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class XmlVisitorTest {

    @Test public void testCreateOpenTag() {
        final Map<String, String> fix1 = Maps.newHashMap();
        fix1.put("bar", "1"); // NOPMD
        fix1.put("baz", "<\">&");
        final Map<String, String> fix2 = Maps.newHashMap();
        fix2.put("bar", "1");
        fix2.put("baz", "2");

        assertEquals("<foo>", XmlVisitor.createOpenTag("foo")); // NOPMD
        assertEquals("<foo baz=\"&lt;&quot;&gt;&amp;\" bar=\"1\">", XmlVisitor.createOpenTag("foo", fix1));
        assertEquals("<foo baz=\"2\" bar=\"1\">", XmlVisitor.createOpenTag("foo", fix2));
        assertEquals("<foo baz=\"2\" bar=\"1\"/>", XmlVisitor.createOpenTag("foo", fix2, false));
    }

    @Test public void testCloseOpenTag() {
        assertEquals("</foo>", XmlVisitor.createCloseTag("foo"));
        assertEquals("</fooBar>", XmlVisitor.createCloseTag("fooBar"));
    }

    @Test public void testExtractAttributes() {
        final Map<String, String> fix = Maps.newHashMap();
        fix.put("meta", "foo");
        fix.put("title", "bar");

        final SyntaxNode syntax = SyntaxNode.newInstance("bar", "foo");
        assertEquals(fix, syntax.getAttributes());

        final LoopNode loop = LoopNode.newInstance();
        assertEquals(new HashMap<String, String>(), loop.getAttributes());
    }

    @Test public void testGenerateXml() throws URISyntaxException, IOException {
        XmlVisitor visitor = new XmlVisitor();
        assertEquals(
            "<?xml version=\"1.0\" encoding=\"utf-8\"?>",
            visitor.getResult()
        );

        SyntaxNode syntax = SyntaxNode.newInstance("xis/ebnf v2.0 http://wiki.karmin.ch/ebnf/ gpl3",
                                           "EBNF defined in itself.");
        visitor = new XmlVisitor();
        syntax.accept(visitor);
        assertEquals("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n"
                   + "<syntax title=\"xis/ebnf v2.0 http://wiki.karmin.ch/ebnf/ "
                   + "gpl3\" meta=\"EBNF defined in itself.\"/>",
            visitor.getResult()
        );

        syntax = syntax("EBNF defined in itself.", "xis/ebnf v2.0 http://wiki.karmin.ch/ebnf/ gpl3")
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

        visitor = new XmlVisitor();
        syntax.accept(visitor);

        final String xml = getInstance().createStringFromFixture("ast/visitor/syntax.xml");
        assertEquals(xml, visitor.getResult());
    }

}
