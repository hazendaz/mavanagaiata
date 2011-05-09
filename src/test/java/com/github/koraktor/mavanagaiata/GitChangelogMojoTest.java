/**
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the new BSD License.
 *
 * Copyright (c) 2011, Sebastian Staudt
 */

package com.github.koraktor.mavanagaiata;

import java.io.IOException;

import org.junit.Test;

public class GitChangelogMojoTest extends AbstractGitOutputMojoTest<GitChangelogMojo> {

    @Test
    public void testError() {
        super.testError("Unable to generate changelog from Git");
    }

    @Test
    public void testCustomization() throws Exception {
        this.mojo.commitPrefix = "- ";
        this.mojo.dateFormat   = "dd.MM.yyyy";
        this.mojo.header       = "History\\n-------\\n";
        this.mojo.tagPrefix    = "\nTag ";
        this.mojo.execute();

        assertEquals("History", this.reader.readLine());
        assertEquals("-------", this.reader.readLine());
        assertEquals("", this.reader.readLine());
        assertEquals("- Snapshot for version 3.0.0", this.reader.readLine());
        assertEquals("- Added project name", this.reader.readLine());
        assertEquals("", this.reader.readLine());
        assertEquals("Tag 2.0.0 - 03.05.2011", this.reader.readLine());
        assertEquals("", this.reader.readLine());
        assertEquals("- Version bump to 2.0.0", this.reader.readLine());
        assertEquals("- Snapshot for version 2.0.0", this.reader.readLine());
        assertEquals("", this.reader.readLine());
        assertEquals("Tag 1.0.0 - 03.05.2011", this.reader.readLine());
        assertEquals("", this.reader.readLine());
        assertEquals("- Initial commit", reader.readLine());
        assertFalse(this.reader.ready());
    }

    @Test
    public void testSkipTagged() throws Exception {
        this.mojo.skipTagged = true;
        this.mojo.execute();

        assertEquals("Changelog", this.reader.readLine());
        assertEquals("=========", this.reader.readLine());
        assertEquals("", this.reader.readLine());
        assertEquals(" * Snapshot for version 3.0.0", this.reader.readLine());
        assertEquals(" * Added project name", this.reader.readLine());
        assertEquals("", this.reader.readLine());
        assertEquals("Version 2.0.0 - 05/03/2011 07:18 AM +0200", this.reader.readLine());
        assertEquals("", this.reader.readLine());
        assertEquals(" * Snapshot for version 2.0.0", this.reader.readLine());
        assertEquals("", this.reader.readLine());
        assertEquals("Version 1.0.0 - 05/03/2011 07:18 AM +0200", this.reader.readLine());
        assertEquals("", this.reader.readLine());
        assertFalse(this.reader.ready());
    }

    protected void assertOutput() throws IOException {
        assertEquals("Changelog", this.reader.readLine());
        assertEquals("=========", this.reader.readLine());
        assertEquals("", this.reader.readLine());
        assertEquals(" * Snapshot for version 3.0.0", this.reader.readLine());
        assertEquals(" * Added project name", this.reader.readLine());
        assertEquals("", this.reader.readLine());
        assertEquals("Version 2.0.0 - 05/03/2011 07:18 AM +0200", this.reader.readLine());
        assertEquals("", this.reader.readLine());
        assertEquals(" * Version bump to 2.0.0", this.reader.readLine());
        assertEquals(" * Snapshot for version 2.0.0", this.reader.readLine());
        assertEquals("", this.reader.readLine());
        assertEquals("Version 1.0.0 - 05/03/2011 07:18 AM +0200", this.reader.readLine());
        assertEquals("", this.reader.readLine());
        assertEquals(" * Initial commit", reader.readLine());
        assertFalse(this.reader.ready());
    }

}
