/*
 * LICENSE
 *
 * "THE BEER-WARE LICENSE" (Revision 42):
 * "Sven Strittmatter" <ich(at)weltraumschaf(dot)de> wrote this file.
 * As long as you retain this notice you can do whatever you want with
 * this stuff. If we meet some day, and you think this stuff is worth it,
 * you can buy me a beer in return.
 *
 */

package de.weltraumschaf.jebnf.parser;

import de.weltraumschaf.jebnf.ast.Node;
import de.weltraumschaf.jebnf.ast.nodes.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Parses a stream of EBNF tokens and generate a abstract syntax tree.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class EbnfParser implements Parser {

    /**
     * Assignment tokens.
     *
     * TODO: Consider move into CharacterHelper.
     */
    private static final List<String> ASSIGN = Arrays.asList("=", ":", ":==");

    /**
     * Rule termination tokens.
     *
     * TODO: Consider move into CharacterHelper.
     */
    private static final List<String> END_OF_RULE = Arrays.asList(".", ";");

    /**
     * All other operator tokens.
     *
     * TODO: Consider using List<Character>.
     * TODO: Consider move into CharacterHelper.
     */
    private static final String[] OPERATOR = {".", "=", "|", ")", "]", "}"};

    /**
     * Used to receive the tokens.
     */
    private final Scanner scanner;

    /**
     * The abstract syntax tree.
     */
    private final Syntax ast;

    /**
     * Initialized with a scanner which produced the token stream.
     *
     * @param scanner Provides the token stream.
     */
    public EbnfParser(final Scanner scanner) {
        super();
        this.scanner = scanner;
        this.ast     = Syntax.newInstance();
    }

    /**
     * Parses the EBNF tokens and returns a syntax AST.
     *
     * On semantic syntax errors a SyntaxError will be thrown.
     *
     * @throws SyntaxException If the parser encounter bad syntax.
     * @throws IOException     On IO errors of the parsed source.
     * @return Return the parsed syntax tree.
     */
    @Override
    public Syntax parse() throws SyntaxException, IOException {
        scanner.nextToken();

        if (scanner.getCurrentToken().isType(TokenType.LITERAL)) {
            ast.setAttribute("title", scanner.getCurrentToken().getValue(true));
            scanner.nextToken();
        }

        if (!assertToken(scanner.getCurrentToken(), TokenType.L_BRACE, "{")) {
            raiseError("Syntax must start with '{'");
        }

        scanner.nextToken();

        while (scanner.hasNextToken()) {
            if (scanner.getCurrentToken().isType(TokenType.IDENTIFIER)) {
                final Node rules = parseRule();
                ast.addChild(rules);
                scanner.nextToken();
            } else if (scanner.getCurrentToken().isType(TokenType.COMMENT)) {
                final Comment comment = Comment.newInstance(ast,
                                                            scanner.getCurrentToken().getValue());
                ast.addChild(comment);
                scanner.nextToken();
            } else {
                break;
            }
        }

        if (!assertToken(scanner.getCurrentToken(), TokenType.R_BRACE, "}")) {
            raiseError(String.format("Syntax must end with '}' but saw '%s'",
                                     scanner.getCurrentToken()));
        }

        scanner.nextToken();

        if (scanner.hasNextToken()) {
            if (scanner.getCurrentToken().isType(TokenType.LITERAL)) {
                ast.setAttribute("meta", scanner.getCurrentToken().getValue(true));
            } else {
                raiseError("Literal expected as syntax comment");
            }
        }

        return ast;
    }

    /**
     * Parses an EBNF production: rule = identifier ( "=" | ":==" | ":" ) expression ( "." | ";" ) .
     *
     * @throws SyntaxException If the parser encounter bad syntax.
     * @throws IOException     On IO errors of the parsed source.
     * @return Return parsed rule node. Type of node is {@link NodeType#RULE}.
     */
    private Node parseRule() throws SyntaxException, IOException {
        if (!scanner.getCurrentToken().isType(TokenType.IDENTIFIER)) {
            raiseError("Production must start with an identifier");
        }

        final Rule rule = Rule.newInstance(ast, scanner.getCurrentToken().getValue());
        scanner.nextToken();

        if (scanner.getCurrentToken().isType(TokenType.COMMENT)) {
            final Comment comment = Comment.newInstance(rule, scanner.getCurrentToken().getValue());
            rule.addChild(comment);
            scanner.nextToken();
        }

        if (!assertTokens(scanner.getCurrentToken(), TokenType.ASIGN, ASSIGN)) {
            raiseError("Identifier must be followed by '='");
        }

        scanner.nextToken();
        final Node expressions = parseExpression(rule);
        rule.addChild(expressions);

        if (scanner.getCurrentToken().isType(TokenType.COMMENT)) {
            final Comment comment = Comment.newInstance(rule, scanner.getCurrentToken().getValue());
            rule.addChild(comment);
            scanner.nextToken();
        }

        if (!assertTokens(scanner.getCurrentToken(), TokenType.END_OF_RULE, END_OF_RULE)) {
            scanner.backtrackToken(2);
            raiseError("Rule must end with '.' or ';'",
                       scanner.getCurrentToken().getPosition(true));
        }

        return rule;
    }

    /**
     * Parses an EBNF expression: expression = term { "|" term } .
     *
     * @param parent Parent node.
     * @throws SyntaxException If the parser encounter bad syntax.
     * @throws IOException     On IO errors of the parsed source.
     * @return Return parsed expression node.
     */
    private Node parseExpression(final Node parent) throws SyntaxException, IOException {
        final Choice choiceNode = Choice.newInstance(parent);
        Node term = parseTerm(choiceNode);
        choiceNode.addChild(term);
        boolean multipleTerms = false;

        while (assertToken(scanner.getCurrentToken(), TokenType.CHOICE, "|")) {
            scanner.nextToken();
            term = parseTerm(choiceNode);
            choiceNode.addChild(term);
            multipleTerms = true;
        }

        return multipleTerms ? choiceNode : term;
    }

    /**
     * Parses an EBNF term: term = factor { factor } .
     *
     * @param parent Parent node.
     * @throws SyntaxException If the parser encounter bad syntax.
     * @throws IOException     On IO errors of the parsed source.
     * @return Return parsed term node.
     */
    private Node parseTerm(final Node parent) throws SyntaxException, IOException {
        final Sequence sequenceNode = Sequence.newInstance(parent);
        Node factor = parseFactor(sequenceNode);
        sequenceNode.addChild(factor);
        scanner.nextToken();
        boolean multipleFactors = false;

        while (scanner.getCurrentToken().isNotEquals(OPERATOR)) {
            factor = parseFactor(sequenceNode);
            sequenceNode.addChild(factor);
            scanner.nextToken();
            multipleFactors = true;
        }

        return multipleFactors ? sequenceNode : factor;
    }

    /**
     * Parses an EBNF factor:
     * factor = identifier
     *       | literal
     *       | "[" expression "]"
     *       | "(" expression ")"
     *       | "{" expression "}" .
     *
     * @param parent Parent node.
     * @throws SyntaxException If the parser encounter bad syntax.
     * @throws IOException     On IO errors of the parsed source.
     * @return Return parsed factor node.
     */
    private Node parseFactor(final Node parent) throws SyntaxException, IOException {
        if (scanner.getCurrentToken().isType(TokenType.IDENTIFIER)) {
            return Identifier.newInstance(parent, scanner.getCurrentToken().getValue());
        }

        if (scanner.getCurrentToken().isType(TokenType.LITERAL)) {
            /*if (assertToken(scanner.peekToken(), TokenType.OPERATOR, ".")) {
                echo "range";
                range = dom.createElement(Type.RANGE);
                range.setAttribute("from", scanner.currentToken().getValue(true));
                scanner.nextToken(); // Omit ".." literal.
                scanner.nextToken();
                range.setAttribute("to", scanner.currentToken().getValue(true));
                return range;
            }*/

            return Terminal.newInstance(parent, scanner.getCurrentToken().getValue(true));
        }

        if (scanner.getCurrentToken().isType(TokenType.COMMENT)) {
            return Comment.newInstance(parent, scanner.getCurrentToken().getValue());
        }

        if (assertToken(scanner.getCurrentToken(), TokenType.L_PAREN, "(")) {
            scanner.nextToken();
            final Node expression = parseExpression(parent);

            if (!assertToken(scanner.getCurrentToken(), TokenType.R_PAREN, ")")) {
                raiseError("Group must end with ')'");
            }

            return expression;
        }

        if (assertToken(scanner.getCurrentToken(), TokenType.L_BRACK, "[")) {
            scanner.nextToken();
            final Node expression = parseExpression(parent);
            final Option option = Option.newInstance(parent);
            option.addChild(expression);

            if (!assertToken(scanner.getCurrentToken(), TokenType.R_BRACK, "]")) {
                raiseError("Option must end with ']'");
            }

            return option;
        }

        if (assertToken(scanner.getCurrentToken(), TokenType.L_BRACE, "{")) {
            scanner.nextToken();
            final Node expression = parseExpression(parent);
            final Loop loop       = Loop.newInstance(parent);
            loop.addChild(expression);

            if (!assertToken(scanner.getCurrentToken(), TokenType.R_BRACE, "}")) {
                raiseError("Loop must end with '}'");
            }

            return loop;
        }

        raiseError("Factor expected");
        return null;
    }

    /**
     * Checks whether a token is of a type and is equal to a string literal or not.
     *
     * @param token Token to assert.
     * @param type  Token type to assert against.
     * @param value Token value to assert against.
     * @return Return true if the passed token is of passed type and has passed string as value, unless false.
     */
    protected boolean assertToken(final Token token, final TokenType type, final String value) {
        return token.getType() == type && token.getValue().equals(value);
    }

    /**
     * Checks whether a token is of a type and is equal to a array of string literal or not.
     *
     * @param token  Token to assert.
     * @param type   type to assert against.
     * @param values Array of strings.
     * @return Return true if the passed token is of passed type and has one of the passed
     *        strings as value, unless false.
     */
    protected  boolean assertTokens(final Token token, final TokenType type, final List<String> values) {
        for (String value : values) {
            if (assertToken(token, type, value)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Raises an error on the scanner's current token position.
     *
     * @param msg Describes the raised error.
     * @throws SyntaxException Throws always an exception.
     */
    protected void raiseError(final String msg) throws SyntaxException {
        raiseError(msg, scanner.getCurrentToken().getPosition());
    }

    /**
     * Helper to raise syntax errors.
     *
     * If no position is passed the one of the current token is used.
     *
     * @param msg  Describes the raised error.
     * @param pos The position where the error occurred in the source.
     * @throws SyntaxException Throws always an exception.
     */
    protected void raiseError(final String msg, final Position pos) throws SyntaxException {
        throw new SyntaxException(msg, pos);
    }

}
