/*******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *******************************************************************************/

package org.apache.ofbiz.base.start;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertThrows;

public class OfbizStartupUnitTests {
    @Test
    public void commandParserDoesNotAcceptMoreThanOneCommand() {
        // expectedException.expectMessage("an option from this group has already been selected");
        assertThrows(StartupException.class, () ->
                StartupCommandUtil.parseOfbizCommands("--help", "--status"));
    }

    @Test
    public void commandParserDoesNotAcceptPortoffsetWithoutArgument() throws StartupException {
        // expectedException.expectMessage("Missing argument for option");
        assertThrows(StartupException.class, () ->
                StartupCommandUtil.parseOfbizCommands("--portoffset"));
    }

    @Test
    public void commandParserDoesNotAcceptPortoffsetWithoutPositiveInteger() throws StartupException {
        // expectedException.expectMessage("you can only pass positive integers");

        assertThrows(StartupException.class, () ->
                StartupCommandUtil.parseOfbizCommands("--portoffset", "ThisMustBeInteger54321"));
    }

    @Test
    public void commandParserDoesNotAcceptArgumentForStatus() throws StartupException {
        // expectedException.expectMessage("unrecognized options / properties");

        assertThrows(StartupException.class, () ->
                StartupCommandUtil.parseOfbizCommands("--status", "thisArgNotAllowed"));
    }

    @Test
    public void commandParserDoesNotAcceptArgumentForStart() throws StartupException {
        // expectedException.expectMessage("unrecognized options / properties");

        assertThrows(StartupException.class, () ->
                StartupCommandUtil.parseOfbizCommands("--start", "thisArgNotAllowed"));
    }

    @Test
    public void commandParserDoesNotAcceptArgumentForShutdown() throws StartupException {
        // expectedException.expectMessage("unrecognized options / properties");

        assertThrows(StartupException.class, () ->
                StartupCommandUtil.parseOfbizCommands("--shutdown", "thisArgNotAllowed"));
    }

    @Test
    public void commandParserCombinesMultipleArgsIntoOneCommand() throws StartupException {
        String[] multiArgCommand = new String[]{
                "--load-data", "readers=seed,seed-initial",
                "--load-data", "delegator=default",
                "-l", "timeout=7200"};

        List<StartupCommand> startupCommands = StartupCommandUtil.parseOfbizCommands(multiArgCommand);

        assertThat(startupCommands.size(), equalTo(1));
        assertThat(startupCommands.get(0).getProperties().size(), equalTo(3));
    }
}
