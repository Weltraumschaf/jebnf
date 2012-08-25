# EBNF Parser and Image Generator for PHP/Java

This package contains classes for scanning and parsing [EBNF][WP-EBNF]
grammar files and generate images with railroad diagrams for
that grammar.

This repository contains a work in progress implementation in Java.

The original code I discovered [here][KARMIN]. But that PHP project seems
to be discontinued. So I decided to refactor and port the code
to [PHP5][EBNF-PHP5]. After that I decided to port it to Java for learning more Java.

## Install

You can install the EBNF package library and the command line tool via [Git][GIT] and
[Maven][MAVEN]:

### Get the repository:

    $ git clone git@github.com:Weltraumschaf/jebnf.git

### Build and instal:

    $ cd jebnf && mvn install

After successful installation you should be able to invoke the command line tool:

    $ ./bin/ebnf -h

## Usage

You can either use the shell script <kbd>bin/ebnf</kbd> for
generating images or XML from a grammar file:

    $ ./bin/ebnf -s mygrammar.ebnf
    $ ./bin/ebnf -s mygrammar.ebnf -o mygrammar.png
    $ ./bin/ebnf -s mygrammar.ebnf -o mygrammar.jpg -f jpg
    $ ./bin/ebnf -s mygrammar.ebnf -o mygrammar.gif -f gif
    $ ./bin/ebnf -s mygrammar.ebnf -o mygrammar.xml -f xml

Or you can use the classes for embedding the functionality in your code:

    [[TBD]]

## Short introduction to EBNF

> EBNF is a code that expresses the grammar of a computer language. An EBNF
> consists of terminal symbol and non-terminal production rules which are the
> restrictions governing how terminal symbols can be combined into a legal
> sequence. [Wikipedia][WP-EBNF]

### Describing EBNF with EBNF as an example:

    syntax     = [ title ] "{" { rule } "}" [ meta ] .
    rule       = identifier ( "=" | ":" | ":==" ) expression ( "." | ";" ) .
    expression = term { "|" term } .
    term       = factor { factor } .
    factor     = identifier
               | literal
               | range
               | comment
               | "[" expression "]"
               | "(" expression ")"
               | "{" expression "}" .
    identifier = character { character } .
    range      = character ".." character .
    title      = literal .
    meta       = literal .
    literal    = "'" character { character } "'"
               | '"' character { character } '"' .
    comment    = "(*" character { character } "*)" .
    character  = "a" .. "z"
               | "A" .. "Z"
               | "0" .. "9" .

### Table of symbols

Here is a list of symbols implemented in the package. There are a lot
of [variants of (E)BNFs][EBNF-VARIANTS] out in the wild with some more
or other symbols. This package implements only a reasonable subset.

<dl>
    <dt>definition</dt>
        <dd>= or : or :==</dd>
    <dt>termination</dt>
        <dd>; or .</dd>
    <dt>alternation</dt>
        <dd>|</dd>
    <dt>option</dt>
        <dd>[ ... ]</dd>
    <dt>repetition</dt>
        <dd>{ ... }</dd>
    <dt>grouping</dt>
        <dd>( ... )</dd>
    <dt>terminal string
        <dd>" ... " or ' ... '</dd>
    <dt>comment</dt>
        <dd>(* ... *)</dd>
</dl>

 ## Ideas for an IDE

The IDE has two panes:

1. Text editor for the EBNF syntax with highlighting. Highlighted tokens are identifier and terminals. Matched highlighting on same identifiers or corresponding braces. The editor has line numbers and signals syntax errors.
2. Graphic preview which renders the EBNF as railroad diagram.

The view mode may be switched between horizontal split, vertical split and tabbed view. In the tabbed view source and preview are tabs. The IDE provides export to XML, PNG, JPG, GIF, and PDF.

The IDE also provides a project view. It holds references to a project configuration file for each project and expands a tree view to files and directories added to the project.

Project file format (JSON):

    ./ebnf.project:

    {
        "name":  "The Project Name",
        "files": ["/foo/bar/baz.ebnf", "..."],
        "directories": ["/foo/snafu", "..."]
    }

[WP-EBNF]:       http://en.wikipedia.org/wiki/Extended_Backus%E2%80%93Naur_Form
[KARMIN]:        http://karmin.ch/ebnf/index
[EBNF-VARIANTS]: http://www.cs.man.ac.uk/~pjj/bnf/ebnf.html
[EBNF-PHP5]:     https://github.com/Weltraumschaf/ebnf
[MAVEN]:         http://maven.apache.org/
[GIT]:           http://git-scm.com/