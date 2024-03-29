package com.github.nhojpatrick.config.files.tests;

import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.github.nhojpatrick.config.files.FileUtil.safeDirectoryName;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileUtil_safeDirectoryNameTest {

    @ParameterizedTest
    @MethodSource("safeDirectoryName_invalid_src")
    void safeDirectoryName_invalid(final String input,
                                   final String expectedMessage) {
        final Executable testMethod = () -> safeDirectoryName(input);
        final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, testMethod);
        assertAll("Checking Exception",
                () -> assertThat(thrown.getMessage(), is(equalTo(expectedMessage))),
                () -> assertThat(thrown.getCause(), is(nullValue()))
        );
    }

    private static Stream<Arguments> safeDirectoryName_invalid_src() {
        return Stream.of(
                //
                Arguments.of(" ", "Invalid safeDirectoryName ' '"),
                Arguments.of("-", "Invalid safeDirectoryName '-'"),
                Arguments.of("_", "Invalid safeDirectoryName '_'"),
                //
                Arguments.of("  ", "Invalid safeDirectoryName '  '"),
                Arguments.of(" -", "Invalid safeDirectoryName ' -'"),
                Arguments.of(" _", "Invalid safeDirectoryName ' _'"),
                Arguments.of("- ", "Invalid safeDirectoryName '- '"),
                Arguments.of("--", "Invalid safeDirectoryName '--'"),
                Arguments.of("-_", "Invalid safeDirectoryName '-_'"),
                Arguments.of("_ ", "Invalid safeDirectoryName '_ '"),
                Arguments.of("_-", "Invalid safeDirectoryName '_-'"),
                Arguments.of("__", "Invalid safeDirectoryName '__'"),
                //
                Arguments.of("   ", "Invalid safeDirectoryName '   '"),
                Arguments.of("  -", "Invalid safeDirectoryName '  -'"),
                Arguments.of("  _", "Invalid safeDirectoryName '  _'"),
                Arguments.of(" - ", "Invalid safeDirectoryName ' - '"),
                Arguments.of(" --", "Invalid safeDirectoryName ' --'"),
                Arguments.of(" -_", "Invalid safeDirectoryName ' -_'"),
                Arguments.of(" _ ", "Invalid safeDirectoryName ' _ '"),
                Arguments.of(" _-", "Invalid safeDirectoryName ' _-'"),
                Arguments.of(" __", "Invalid safeDirectoryName ' __'"),
                Arguments.of("-  ", "Invalid safeDirectoryName '-  '"),
                Arguments.of("- -", "Invalid safeDirectoryName '- -'"),
                Arguments.of("- _", "Invalid safeDirectoryName '- _'"),
                Arguments.of("-- ", "Invalid safeDirectoryName '-- '"),
                Arguments.of("---", "Invalid safeDirectoryName '---'"),
                Arguments.of("--_", "Invalid safeDirectoryName '--_'"),
                Arguments.of("-_ ", "Invalid safeDirectoryName '-_ '"),
                Arguments.of("-_-", "Invalid safeDirectoryName '-_-'"),
                Arguments.of("-__", "Invalid safeDirectoryName '-__'"),
                Arguments.of("_  ", "Invalid safeDirectoryName '_  '"),
                Arguments.of("_ -", "Invalid safeDirectoryName '_ -'"),
                Arguments.of("_ _", "Invalid safeDirectoryName '_ _'"),
                Arguments.of("_- ", "Invalid safeDirectoryName '_- '"),
                Arguments.of("_--", "Invalid safeDirectoryName '_--'"),
                Arguments.of("_-_", "Invalid safeDirectoryName '_-_'"),
                Arguments.of("__ ", "Invalid safeDirectoryName '__ '"),
                Arguments.of("__-", "Invalid safeDirectoryName '__-'"),
                Arguments.of("___", "Invalid safeDirectoryName '___'"),
                //
                Arguments.of("!", "Invalid safeDirectoryName '!'"),
                Arguments.of("@", "Invalid safeDirectoryName '@'"),
                Arguments.of("£", "Invalid safeDirectoryName '£'"),
                Arguments.of("$", "Invalid safeDirectoryName '$'"),
                Arguments.of("%", "Invalid safeDirectoryName '%'"),
                Arguments.of("^", "Invalid safeDirectoryName '^'"),
                Arguments.of("&", "Invalid safeDirectoryName '&'"),
                Arguments.of("*", "Invalid safeDirectoryName '*'"),
                Arguments.of("(", "Invalid safeDirectoryName '('"),
                Arguments.of(")", "Invalid safeDirectoryName ')'"),
                Arguments.of("+", "Invalid safeDirectoryName '+'"),
                Arguments.of("=", "Invalid safeDirectoryName '='"),
                Arguments.of("[", "Invalid safeDirectoryName '['"),
                Arguments.of("{", "Invalid safeDirectoryName '{'"),
                Arguments.of("]", "Invalid safeDirectoryName ']'"),
                Arguments.of("}", "Invalid safeDirectoryName '}'"),
                Arguments.of("\"", "Invalid safeDirectoryName '\"'"),
                Arguments.of("'", "Invalid safeDirectoryName '''"),
                Arguments.of("\\", "Invalid safeDirectoryName '\\'"),
                Arguments.of("|", "Invalid safeDirectoryName '|'"),
                Arguments.of("/", "Invalid safeDirectoryName '/'"),
                Arguments.of("?", "Invalid safeDirectoryName '?'"),
                Arguments.of(">", "Invalid safeDirectoryName '>'"),
                Arguments.of(",", "Invalid safeDirectoryName ','"),
                Arguments.of("<", "Invalid safeDirectoryName '<'"),
                Arguments.of("`", "Invalid safeDirectoryName '`'"),
                Arguments.of("~", "Invalid safeDirectoryName '~'"),
                Arguments.of("!@£$%^&*()=+[{]}'\"\\|/?>,<`~", "Invalid safeDirectoryName '!@£$%^&*()=+[{]}'\"\\|/?>,<`~'"),
                //
                Arguments.of(null, "Invalid safeDirectoryName 'null'")
        );
    }

    @ParameterizedTest
    @MethodSource("safeDirectoryName_valid_src")
    void safeDirectoryName_valid(final String input,
                                 final String expected) {
        assertThat(safeDirectoryName(input), is(equalTo(expected)));
    }

    private static Stream<Arguments> safeDirectoryName_valid_src() {
        return Stream.of(
                //
                Arguments.of("qwerty ", "qwerty"),
                Arguments.of("qwerty-", "qwerty"),
                Arguments.of("qwerty_", "qwerty"),
                Arguments.of(" qwerty", "qwerty"),
                Arguments.of("-qwerty", "qwerty"),
                Arguments.of("_qwerty", "qwerty"),
                //
                Arguments.of(" qwerty ", "qwerty"),
                Arguments.of(" qwerty-", "qwerty"),
                Arguments.of(" qwerty_", "qwerty"),
                Arguments.of("-qwerty ", "qwerty"),
                Arguments.of("-qwerty-", "qwerty"),
                Arguments.of("-qwerty_", "qwerty"),
                Arguments.of("_qwerty ", "qwerty"),
                Arguments.of("_qwerty-", "qwerty"),
                Arguments.of("_qwerty_", "qwerty"),
                //
                Arguments.of("qwerty   ", "qwerty"),
                Arguments.of("qwerty  -", "qwerty"),
                Arguments.of("qwerty  _", "qwerty"),
                Arguments.of("qwerty - ", "qwerty"),
                Arguments.of("qwerty --", "qwerty"),
                Arguments.of("qwerty -_", "qwerty"),
                Arguments.of("qwerty _ ", "qwerty"),
                Arguments.of("qwerty _-", "qwerty"),
                Arguments.of("qwerty __", "qwerty"),
                Arguments.of("qwerty-  ", "qwerty"),
                Arguments.of("qwerty- -", "qwerty"),
                Arguments.of("qwerty- _", "qwerty"),
                Arguments.of("qwerty-- ", "qwerty"),
                Arguments.of("qwerty---", "qwerty"),
                Arguments.of("qwerty--_", "qwerty"),
                Arguments.of("qwerty-_ ", "qwerty"),
                Arguments.of("qwerty-_-", "qwerty"),
                Arguments.of("qwerty-__", "qwerty"),
                Arguments.of("qwerty_  ", "qwerty"),
                Arguments.of("qwerty_ -", "qwerty"),
                Arguments.of("qwerty_ _", "qwerty"),
                Arguments.of("qwerty_- ", "qwerty"),
                Arguments.of("qwerty_--", "qwerty"),
                Arguments.of("qwerty_-_", "qwerty"),
                Arguments.of("qwerty__ ", "qwerty"),
                Arguments.of("qwerty__-", "qwerty"),
                Arguments.of("qwerty___", "qwerty"),
                Arguments.of("   qwerty", "qwerty"),
                Arguments.of("  -qwerty", "qwerty"),
                Arguments.of("  _qwerty", "qwerty"),
                Arguments.of(" - qwerty", "qwerty"),
                Arguments.of(" --qwerty", "qwerty"),
                Arguments.of(" -_qwerty", "qwerty"),
                Arguments.of(" _ qwerty", "qwerty"),
                Arguments.of(" _-qwerty", "qwerty"),
                Arguments.of(" __qwerty", "qwerty"),
                Arguments.of("-  qwerty", "qwerty"),
                Arguments.of("- -qwerty", "qwerty"),
                Arguments.of("- _qwerty", "qwerty"),
                Arguments.of("-- qwerty", "qwerty"),
                Arguments.of("---qwerty", "qwerty"),
                Arguments.of("--_qwerty", "qwerty"),
                Arguments.of("-_ qwerty", "qwerty"),
                Arguments.of("-_-qwerty", "qwerty"),
                Arguments.of("-__qwerty", "qwerty"),
                Arguments.of("_  qwerty", "qwerty"),
                Arguments.of("_ -qwerty", "qwerty"),
                Arguments.of("_ _qwerty", "qwerty"),
                Arguments.of("_- qwerty", "qwerty"),
                Arguments.of("_--qwerty", "qwerty"),
                Arguments.of("_-_qwerty", "qwerty"),
                Arguments.of("__ qwerty", "qwerty"),
                Arguments.of("__-qwerty", "qwerty"),
                Arguments.of("___qwerty", "qwerty"),
                //
                Arguments.of("   qwerty   ", "qwerty"),
                Arguments.of("  -qwerty  -", "qwerty"),
                Arguments.of("  _qwerty  _", "qwerty"),
                Arguments.of(" - qwerty - ", "qwerty"),
                Arguments.of(" --qwerty --", "qwerty"),
                Arguments.of(" -_qwerty -_", "qwerty"),
                Arguments.of(" _ qwerty _ ", "qwerty"),
                Arguments.of(" _-qwerty _-", "qwerty"),
                Arguments.of(" __qwerty __", "qwerty"),
                Arguments.of("-  qwerty-  ", "qwerty"),
                Arguments.of("- -qwerty- -", "qwerty"),
                Arguments.of("- _qwerty- _", "qwerty"),
                Arguments.of("-- qwerty-- ", "qwerty"),
                Arguments.of("---qwerty---", "qwerty"),
                Arguments.of("--_qwerty--_", "qwerty"),
                Arguments.of("-_ qwerty-_ ", "qwerty"),
                Arguments.of("-_-qwerty-_-", "qwerty"),
                Arguments.of("-__qwerty-__", "qwerty"),
                Arguments.of("_  qwerty_  ", "qwerty"),
                Arguments.of("_ -qwerty_ -", "qwerty"),
                Arguments.of("_ _qwerty_ _", "qwerty"),
                Arguments.of("_- qwerty_- ", "qwerty"),
                Arguments.of("_--qwerty_--", "qwerty"),
                Arguments.of("_-_qwerty_-_", "qwerty"),
                Arguments.of("__ qwerty__ ", "qwerty"),
                Arguments.of("__-qwerty__-", "qwerty"),
                Arguments.of("___qwerty___", "qwerty"),
                //
                Arguments.of(" -_ -_ -_qwerty -_ -_ -_", "qwerty"),
                //
                Arguments.of("qwe rty", "qwe_rty"),
                Arguments.of("qwe  rty", "qwe_rty"),
                Arguments.of("qwe   rty", "qwe_rty"),
                Arguments.of("abc def ghi", "abc_def_ghi"),
                Arguments.of("abc  def  ghi", "abc_def_ghi"),
                Arguments.of("abc   def   ghi", "abc_def_ghi"),
                //
                Arguments.of("qaz.wsx", "qaz.wsx"),
                Arguments.of("qaz..wsx", "qaz.wsx"),
                Arguments.of("qaz...wsx", "qaz.wsx"),
                Arguments.of("edc.rfv.tgb", "edc.rfv.tgb"),
                Arguments.of("edc..rfv..tgb", "edc.rfv.tgb"),
                Arguments.of("edc...rfv...tgb", "edc.rfv.tgb"),
                //
                Arguments.of("zaq-xsw", "zaq-xsw"),
                Arguments.of("zaq--xsw", "zaq-xsw"),
                Arguments.of("zaq---xsw", "zaq-xsw"),
                Arguments.of("cde-vfr-bgt", "cde-vfr-bgt"),
                Arguments.of("cde--vfr--bgt", "cde-vfr-bgt"),
                Arguments.of("cde---vfr---bgt", "cde-vfr-bgt"),
                //
                Arguments.of("zxc_vbn", "zxc_vbn"),
                Arguments.of("zxc__vbn", "zxc_vbn"),
                Arguments.of("zxc___vbn", "zxc_vbn"),
                Arguments.of("asd_fgh_jkl", "asd_fgh_jkl"),
                Arguments.of("asd__fgh__jkl", "asd_fgh_jkl"),
                Arguments.of("asd___fgh___jkl", "asd_fgh_jkl"),
                //
                Arguments.of("qwe!rty", "qwe_rty"),
                Arguments.of("qwe@rty", "qwe_rty"),
                Arguments.of("qwe£rty", "qwe_rty"),
                Arguments.of("qwe$rty", "qwe_rty"),
                Arguments.of("qwe%rty", "qwe_rty"),
                Arguments.of("qwe^rty", "qwe_rty"),
                Arguments.of("qwe&rty", "qwe_rty"),
                Arguments.of("qwe*rty", "qwe_rty"),
                Arguments.of("qwe(rty", "qwe_rty"),
                Arguments.of("qwe)rty", "qwe_rty"),
                Arguments.of("qwe+rty", "qwe_rty"),
                Arguments.of("qwe=rty", "qwe_rty"),
                Arguments.of("qwe[rty", "qwe_rty"),
                Arguments.of("qwe{rty", "qwe_rty"),
                Arguments.of("qwe]rty", "qwe_rty"),
                Arguments.of("qwe}rty", "qwe_rty"),
                Arguments.of("qwe'rty", "qwe_rty"),
                Arguments.of("qwe\"rty", "qwe_rty"),
                Arguments.of("qwe\\rty", "qwe_rty"),
                Arguments.of("qwe|rty", "qwe_rty"),
                Arguments.of("qwe/rty", "qwe_rty"),
                Arguments.of("qwe?rty", "qwe_rty"),
                Arguments.of("qwe>rty", "qwe_rty"),
                Arguments.of("qwe,rty", "qwe_rty"),
                Arguments.of("qwe<rty", "qwe_rty"),
                Arguments.of("qwe`rty", "qwe_rty"),
                Arguments.of("qwe~rty", "qwe_rty"),
                Arguments.of("qwe!@£$%^&*()=+[{]}'\"\\|/?>,<`~rty", "qwe_rty"),
                Arguments.of("abc!def!ghi", "abc_def_ghi"),
                Arguments.of("abc@def@ghi", "abc_def_ghi"),
                Arguments.of("abc£def£ghi", "abc_def_ghi"),
                Arguments.of("abc$def$ghi", "abc_def_ghi"),
                Arguments.of("abc%def%ghi", "abc_def_ghi"),
                Arguments.of("abc^def^ghi", "abc_def_ghi"),
                Arguments.of("abc&def&ghi", "abc_def_ghi"),
                Arguments.of("abc*def*ghi", "abc_def_ghi"),
                Arguments.of("abc(def(ghi", "abc_def_ghi"),
                Arguments.of("abc)def)ghi", "abc_def_ghi"),
                Arguments.of("abc+def+ghi", "abc_def_ghi"),
                Arguments.of("abc=def=ghi", "abc_def_ghi"),
                Arguments.of("abc[def[ghi", "abc_def_ghi"),
                Arguments.of("abc{def{ghi", "abc_def_ghi"),
                Arguments.of("abc]def]ghi", "abc_def_ghi"),
                Arguments.of("abc}def}ghi", "abc_def_ghi"),
                Arguments.of("abc'def'ghi", "abc_def_ghi"),
                Arguments.of("abc\"def\"ghi", "abc_def_ghi"),
                Arguments.of("abc\\def\\ghi", "abc_def_ghi"),
                Arguments.of("abc|def|ghi", "abc_def_ghi"),
                Arguments.of("abc/def/ghi", "abc_def_ghi"),
                Arguments.of("abc?def?ghi", "abc_def_ghi"),
                Arguments.of("abc>def>ghi", "abc_def_ghi"),
                Arguments.of("abc,def,ghi", "abc_def_ghi"),
                Arguments.of("abc<def<ghi", "abc_def_ghi"),
                Arguments.of("abc`def`ghi", "abc_def_ghi"),
                Arguments.of("abc~def~ghi", "abc_def_ghi"),
                Arguments.of("abc!@£$%^&*()=+[{]}'\"\\|/?>,<`~def!@£$%^&*()=+[{]}'\"\\|/?>,<`~ghi", "abc_def_ghi"),
                //
                Arguments.of("qwerty", "qwerty")
        );
    }

}
