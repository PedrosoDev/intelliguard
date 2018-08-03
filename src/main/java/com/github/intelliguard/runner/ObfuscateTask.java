/*
 * Copyright 2009 Ronnie Kolehmainen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.intelliguard.runner;

import com.github.intelliguard.ant.YProject;
import com.github.intelliguard.ant.YProjectHelper;
import com.github.intelliguard.facet.GuardFacet;
import com.github.intelliguard.generator.YGuardGenerator;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.jetbrains.annotations.NotNull;

import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: Ronnie
 * Date: 2009-nov-02
 * Time: 18:37:23
 */
public class ObfuscateTask implements Runnable
{
    private RunProgress runProgress;
    private GuardFacet guardFacet;

    public ObfuscateTask(@NotNull final RunProgress runProgress, @NotNull final GuardFacet guardFacet)
    {
        this.runProgress = runProgress;
        this.guardFacet = guardFacet;
    }

    public void run()
    {

        final String buildXml = YGuardGenerator.generateBuildXml(guardFacet);
        final Project project = new YProject(runProgress);
        final ProjectHelper projectHelper = new YProjectHelper();
        projectHelper.parse(project, new ByteArrayInputStream(buildXml.getBytes()));
        project.init();

        try
        {
            project.executeTarget(YGuardGenerator.YGUARD_TARGET_NAME);
        }
        catch (BuildException e)
        {
            runProgress.markError(e.getMessage());
        }

        /* // DEBUG

        File outFile = new File(guardFacet.getConfiguration().outFile);
        outFile = new File(outFile.getParent(), "yguard.xml");

        try (
            FileOutputStream fos = new FileOutputStream(outFile)
        ){
            PrintWriter writer = new PrintWriter(fos);
            writer.print(buildXml);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

    }
}
