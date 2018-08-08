package org.efrey;

import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.List;

@Mojo(name = "hello")
public class PrinterMojo extends AbstractMojo {

    private final ClassLoader classLoader = PrinterMojo.class.getClassLoader();

    @Parameter(property = "clazz", required = true)
    private String clazz;

    @Parameter(property = "groupings")
    private String[] groupings;

    @Parameter(defaultValue = "${project}")
    private MavenProject project;

    private EnumHandler enumHandler;

    public void execute()
            throws MojoExecutionException {
        // Init
        getLog().info("groupings:" + Arrays.toString(groupings));
        Configuration configuration = Configuration.Builder()
                .addGroupings(groupings)
                .build();
        enumHandler = new EnumHandler(configuration);


        try {
            List runtimeClasspathElements = project.getRuntimeClasspathElements();
            URL[] runtimeUrls = new URL[runtimeClasspathElements.size()];
            for (int i = 0; i < runtimeClasspathElements.size(); i++) {
                String element = (String) runtimeClasspathElements.get(i);
                runtimeUrls[i] = new File(element).toURI().toURL();
            }
            URLClassLoader newLoader = new URLClassLoader(runtimeUrls,
                    Thread.currentThread().getContextClassLoader());

            Class aClass = newLoader.loadClass(clazz);
            getLog().info(aClass.toString());

            if (aClass.isEnum()) {
                enumHandler.generateTables(aClass);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (DependencyResolutionRequiredException e) {
            e.printStackTrace();
        }

    }

}
