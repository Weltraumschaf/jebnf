package de.weltraumschaf.jebnf.ast.builder;

import static de.weltraumschaf.jebnf.TestHelper.getInstance;
import static de.weltraumschaf.jebnf.ast.builder.SyntaxBuilder.syntax;
import de.weltraumschaf.jebnf.ast.nodes.Syntax;
import de.weltraumschaf.jebnf.ast.visitor.Xml;
import java.io.IOException;
import java.net.URISyntaxException;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Tests for all syntax builder classes:
 * {@link SyntaxBuilder}, {@link RuleBuilder} and {@link GenericBuilder}.
 *
 * Tests generating AST by using the internal DSL interface of the builder.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class SyntaxBuilderTest {

    @Test public void testBuilder() throws IOException, URISyntaxException {
        final Syntax syntax = syntax("EBNF defined in itself.")
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

        final String xml   = getInstance().createStringFromFixture("ast/visitor/syntax.xml");
        final Xml visitor  = new Xml();
        syntax.accept(visitor);
        assertEquals(xml, visitor.getXmlString());
    }

}
