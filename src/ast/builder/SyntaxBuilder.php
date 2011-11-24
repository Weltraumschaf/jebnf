<?php

/**
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * @license http://www.gnu.org/licenses/ GNU General Public License
 * @author  Sven Strittmatter <ich@weltraumschaf.de>
 * @package ast
 */

namespace de\weltraumschaf\ebnf\ast\builder;

/**
 * @see Builder
 */
require_once __DIR__ . DIRECTORY_SEPARATOR . 'Builder.php';
/**
 * @see RuleBuilder
 */
require_once __DIR__ . DIRECTORY_SEPARATOR . 'RuleBuilder.php';
/**
 * @see Rule
 */
require_once dirname(__DIR__) . DIRECTORY_SEPARATOR . 'Rule.php';
/**
 * @see Syntax
 */
require_once dirname(__DIR__) . DIRECTORY_SEPARATOR . 'Syntax.php';

use de\weltraumschaf\ebnf\ast\Rule;
use de\weltraumschaf\ebnf\ast\Syntax;

/**
 *
 */
class SyntaxBuilder {
    /**
     * @var Syntax
     */
    private $syntax;

    public function syntaxt($title, $meta) {
        $this->syntax = new Syntax();
        $this->syntax->title = (string)$title;
        $this->syntax->meta = (string)$meta;
        return $this;
    }

    public function rule($name) {
        $rule = new Rule();
        $rule->name = (string) $name;
        $this->syntax->addChild($rule);
        return new RuleBuilder($rule, $this);
    }

    public function getAst() {
        return $this->syntax;
    }
}
